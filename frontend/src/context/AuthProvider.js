import { useState } from "react";
import axios from "axios";
import AuthContext from "./AuthContext";
import { useMutation } from "react-query";
import jwt_decode from "jwt-decode";

export default function AuthProvider({ children }) {
  const [token, setToken] = useState(null);
  const [userData, setUserData] = useState(null);

  const login = useMutation(
    (credentials) => {
      return axios.post("/auth/login", credentials).then((res) => res.data);
    },
    {
      onSuccess: (data) => {
        setToken(data);
        setUserData(jwt_decode(data.toString()));
      },
    }
  );

  return (
    <AuthContext.Provider value={{ token, login, userData }}>
      {children}
    </AuthContext.Provider>
  );
}
