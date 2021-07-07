import axios from "axios";

export function signUpWithCredentials(credentials) {
  return axios.post("/auth/signup", credentials).then((res) => res.data);
}

export function validatePasswords(password1, password2) {
  if (!passwordsAreMatching(password1, password2)) {
    throw new Error("Passwörter stimmen nicht überein!");
  }
  if (!passwordIsLongEnough(password1)) {
    throw new Error("Passwort muss mindestens 8 Zeichen lang sein.");
  }
  if (!passwordContainsLowerCase(password1)) {
    throw new Error(
      "Passwort muss mindestens einen Kleinbuchstaben enthalten."
    );
  }
  if (!passwordContainsUpperCase(password1)) {
    throw new Error("Passwort muss mindest einen Großbuchstaben enthalten.");
  }
  if (!passwordContainsNumber(password1)) {
    throw new Error("Passwort muss midestens eine Zahl enthalten.");
  }
}

const passwordsAreMatching = (password1, password2) => password1 === password2;
const passwordIsLongEnough = (password1) => password1.length >= 8;
const passwordContainsLowerCase = (password1) => /[a-z]/.test(password1);
const passwordContainsUpperCase = (password1) => /[A-Z]/.test(password1);
const passwordContainsNumber = (password1) => /\d/.test(password1);
