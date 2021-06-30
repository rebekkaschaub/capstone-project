import { useState } from "react";
import axios from "axios";
import AuthContext from "./AuthContext";
import { useMutation } from "react-query";

export default function AuthProvider({ children }) {
  const [token, setToken] = useState();

  const login = useMutation(
    (credentials) => {
      return axios.post("/auth/login", credentials).then((res) => res.data);
    },
    {
      onSuccess: (data) => {
        setToken(data);
      },
    }
  );

  return (
    <AuthContext.Provider value={{ token, login }}>
      {children}
    </AuthContext.Provider>
  );
}
