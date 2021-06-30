import { useHistory } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import AuthContext from "./AuthContext";

export default function AuthProvider({ children }) {
  const history = useHistory();
  const [token, setToken] = useState();
  const login = (credentials) =>
    axios
      .post("/auth/login", credentials)
      .then((res) => res.data)
      .then(setToken)
      .then(() => history.push("/"))
      .catch((err) => console.error(err.message));

  return (
    <AuthContext.Provider value={{ token, login }}>
      {children}
    </AuthContext.Provider>
  );
}
