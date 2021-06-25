import styled from "styled-components/macro";
import logo from "../pictures/logo.png";

export default function Menu({ open }) {
  return (
    <StyledMenu open={open}>
      <a href="/">Sympathise</a>
      <a href="/">Notfallhilfe</a>
      <a href="/search">Beratungsstelle finden</a>
      <a href="/">Gemerkt</a>
      <a href="/">Login</a>
      <a href="/">Links</a>
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
