import styled from "styled-components/macro";
import logo from "../pictures/logo.png";
import { NavLink } from "react-router-dom";

export default function Menu({ open, setOpen }) {
  return (
    <StyledMenu open={open}>
      <NavLink to="/" onClick={() => setOpen(!open)}>
        Sympathise
      </NavLink>
      <NavLink to="/" onClick={() => setOpen(!open)}>
        Notfallhilfe
      </NavLink>
      <NavLink to="/search" onClick={() => setOpen(!open)}>
        Beratungsstelle finden
      </NavLink>
      <NavLink NavLink to="/" onClick={() => setOpen(!open)}>
        Gemerkt
      </NavLink>
      <NavLink NavLink to="/" onClick={() => setOpen(!open)}>
        Login
      </NavLink>
      <NavLink NavLink to="/" onClick={() => setOpen(!open)}>
        Links
      </NavLink>
      <img
        src={logo}
        alt="sympathise logo: handwritten s on a blue background"
      />
    </StyledMenu>
  );
}

const StyledMenu = styled.nav`
  display: flex;
  flex-direction: column;
  justify-content: center;
  background: #effffa;
  transform: ${({ open }) => (open ? "translateX(0)" : "translateX(-100%)")};
  height: 100vh;
  text-align: left;
  padding: 1rem;
  position: absolute;
  top: 0;
  left: 0;
  transition: transform 0.3s ease-in-out;
  z-index: 9;

  a {
    color: #1c3648;
    text-decoration: none;
    //transition: color 0.3s linear;
  }

  a:not(:first-child) {
    font-size: 1rem;
    text-transform: uppercase;
    padding: 1rem 0;
    letter-spacing: 0.5rem;
  }

  a:first-child {
    margin: 0;
    font-family: "Mrs Saint Delafield", cursive;
    font-size: 2rem;
    font-weight: bolder;
  }

  img {
    height: 50px;
    width: 50px;
    align-self: center;
    margin-top: 3em;
  }
`;
