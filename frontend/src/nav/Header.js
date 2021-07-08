import BurgerMenuIcon from "./BurgerMenuIcon";
import Menu from "./Menu";
import styled from "styled-components/macro";
import { useState } from "react";
import { NavLink } from "react-router-dom";

export default function Header() {
  const [open, setOpen] = useState(false);

  return (
    <Wrapper>
      <nav>
        <BurgerMenuIcon open={open} setOpen={setOpen} />
        <Menu open={open} setOpen={() => setOpen(!open)} />
      </nav>
      <h2>
        <NavLink to="/">Sympathise</NavLink>
      </h2>
    </Wrapper>
  );
}

const Wrapper = styled.header`
  margin-bottom: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;

  h2 {
    font-family: "Mrs Saint Delafield", cursive;
    margin: 0;

    a {
      color: #1c3648;
      text-decoration: none;
    }
  }
`;
