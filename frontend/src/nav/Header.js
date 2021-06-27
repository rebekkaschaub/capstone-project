import BurgerMenuIcon from "./BurgerMenuIcon";
import Menu from "./Menu";
import styled from "styled-components/macro";
import { useState } from "react";
import { NavLink } from "react-router-dom";

export default function Header() {
  const [open, setOpen] = useState(false);

  return (
    <Wrapper>
      <div>
        <BurgerMenuIcon open={open} setOpen={setOpen} />
        <h2>
          <NavLink to="/">Sympathise</NavLink>
        </h2>
      </div>
      <Menu open={open} setOpen={setOpen} />
    </Wrapper>
  );
}

const Wrapper = styled.div`
  margin-bottom: 10px;

  div {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  h2 {
    font-family: "Mrs Saint Delafield", cursive;
    margin: 0;

    a {
      color: #1c3648;
      text-decoration: none;
    }
  }
`;
