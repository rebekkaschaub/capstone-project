import { useContext, useState } from "react";
import AuthContext from "../context/AuthContext";
import styled from "styled-components/macro";

const initialState = {
  username: "",
  password: "",
};

export default function LoginPage() {
  const [credentials, setCredentials] = useState(initialState);
  const { login } = useContext(AuthContext);

  const handleChange = (event) => {
    setCredentials({ ...credentials, [event.target.name]: event.target.value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    login(credentials);
  };

  return (
    <Wrapper>
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <label>
          <input
            type="text"
            placeholder="username"
            name="username"
            onChange={handleChange}
            value={credentials.username}
          />
        </label>
        <label>
          <input
            type="password"
            placeholder="password"
            name="password"
            onChange={handleChange}
            value={credentials.password}
          />
        </label>
        <button>Login</button>
      </form>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;
