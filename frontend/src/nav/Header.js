import BurgerMenuIcon from "./BurgerMenuIcon";
import Menu from "./Menu";
import styled from "styled-components/macro";
import { useState } from "react";

export default function Header() {
  const [open, setOpen] = useState(false);

  return (
    <Wrapper>
      <div>
        <BurgerMenuIcon open={open} setOpen={setOpen} />
        <h2>Soulmat</h2>
      </div>
      <Menu open={open} setOpen={setOpen} />
    </Wrapper>
  );
}

const Wrapper = styled.div`
  margin: 10px;
  padding: 0 0.5rem;
  border-radius: 13px;

  div {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  h2 {
    font-family: "Mrs Saint Delafield", cursive;
    margin: 0;
  }
`;
