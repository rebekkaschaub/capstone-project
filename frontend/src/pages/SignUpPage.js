import styled from "styled-components/macro";
import logo from "../images/logo.png";
import { useState } from "react";
import { useMutation } from "react-query";
import {
  signUpWithCredentials,
  validatePasswords,
} from "../service/SignUpService";
import { useHistory } from "react-router-dom";

const initialState = {
  username: "",
  password1: "",
  password2: "",
};

export default function SignUpPage() {
  const history = useHistory();
  const [signUpData, setSignUpData] = useState(initialState);
  const [error, setError] = useState();

  const handleChange = (event) => {
    setSignUpData({ ...signUpData, [event.target.name]: event.target.value });
  };

  const signUp = useMutation((credentials) => {
    return signUpWithCredentials(credentials);
  });

  const handleSubmit = (event) => {
    event.preventDefault();
    try {
      validatePasswords(signUpData.password1, signUpData.password2);
      const credentials = {
        username: signUpData.username,
        password: signUpData.password1,
      };
      signUp.mutate(credentials, {
        onSuccess: () => {
          history.push("/login");
        },
      });
    } catch (e) {
      setError(e.message);
      setSignUpData({ ...signUpData, password1: "", password2: "" });
    }
  };

  return (
    <Wrapper>
      <img
        src={logo}
        alt="sympathise logo: handwritten s on a blue background"
      />
      <h2>Registrieren</h2>
      <Form onSubmit={handleSubmit}>
        <label>
          <p>Username</p>
          <input
            type="text"
            name="username"
            onChange={handleChange}
            value={signUpData.username}
            required
          />
        </label>
        <label>
          <p>Passwort</p>
          <input
            type="password"
            name="password1"
            onChange={handleChange}
            value={signUpData.password1}
            required
          />
        </label>
        <label>
          <p>Passwort</p>
          <input
            type="password"
            name="password2"
            onChange={handleChange}
            value={signUpData.password2}
            required
          />
        </label>
        {error && <div>{error}</div>}
        {signUp.isError && <div>An error occurred: {signUp.error.message}</div>}
        <button
          disabled={
            !signUpData.password1 ||
            !signUpData.password1 ||
            signUpData.username.length < 3
          }
        >
          {signUp.isLoading ? <>Loading</> : <>Registrieren</>}
        </button>
      </Form>

      <Note>
        <p>Hinweis:</p>
        <p>
          Passwort muss mindestens 8 Zeichen lang sein, 1 Zahl, 1 Großbuchstaben
          und 1 Kleinbuchstaben enthalten
        </p>
      </Note>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  padding-top: 20px;
  display: grid;
  grid-template-rows: min-content min-content 1fr min-content;
  row-gap: 10px;
  justify-items: center;
  height: 100%;

  img {
    height: 80px;
    width: 80px;
  }
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  width: 100%;
  height: 100%;

  label {
    width: 95%;
  }

  p {
    margin-bottom: 4px;
    font-weight: bold;
    font-size: 17px;
  }

  input {
    border: 1px solid #1c3648;
    border-radius: 4px;
    background-color: #fff;
    padding: 12px 6px;
    font-size: 15px;
    width: 100%;
  }

  button {
    width: 100%;
    padding: 8px 0;
    font-size: 18px;
    color: #fff;
    background-color: #1c3648;
    border: none;
    border-radius: 4px;
  }

  button:disabled {
    background-color: darkgray;
  }
`;

const Note = styled.section`
  font-size: 10px;

  p {
    margin: 0;
  }
`;
