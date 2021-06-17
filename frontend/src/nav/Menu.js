import styled from "styled-components/macro";

export default function Menu({ open }) {
  return (
    <StyledMenu open={open}>
      <h1>Soulmat</h1>
      <a href="/">Notfallhilfe</a>
      <a href="/counseling">Beratungsstelle finden</a>
      <a href="/">Gemerkt</a>
      <a href="/">Login</a>
      <a href="/">Links</a>
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
  padding: 2rem;
  position: absolute;
  top: 0;
  left: 0;
  transition: transform 0.3s ease-in-out;

  a {
    font-size: 1rem;
    text-transform: uppercase;
    padding: 1rem 0;
    letter-spacing: 0.5rem;
    color: #0d0c1d;
    text-decoration: none;
    transition: color 0.3s linear;
  }

  h1 {
    margin: 0;
    font-family: "Mrs Saint Delafield", cursive;
  }
`;
