import { useContext, useState } from "react";
import AuthContext from "../context/AuthContext";
import styled from "styled-components/macro";
import logo from "../images/logo.png";
import { useHistory } from "react-router-dom";

const initialState = {
  username: "",
  password: "",
};

export default function LoginPage() {
  const history = useHistory();
  const [credentials, setCredentials] = useState(initialState);
  const { login } = useContext(AuthContext);

  const handleChange = (event) => {
    setCredentials({ ...credentials, [event.target.name]: event.target.value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    login.mutate(credentials, {
      onSuccess: () => {
        history.push("/bookmarked");
      },
    });
  };

  return (
    <Wrapper>
      <img
        src={logo}
        alt="sympathise logo: handwritten s on a blue background"
      />
      <h2>Anmelden</h2>
      <Form onSubmit={handleSubmit}>
        <label>
          <p>Username</p>
          <input
            type="text"
            name="username"
            onChange={handleChange}
            value={credentials.username}
          />
        </label>
        <label>
          <p>Passwort</p>
          <input
            type="password"
            name="password"
            onChange={handleChange}
            value={credentials.password}
          />
        </label>
        {login.isError && <div>An error occurred: {login.error.message}</div>}
        <button disabled={!credentials.password || !credentials.username}>
          {login.isLoading ? <>Loading</> : <>Anmelden</>}
        </button>
      </Form>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  padding-top: 20px;
  display: grid;
  grid-template-rows: min-content min-content 1fr;
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
