import Burger from "./Burger";
import Menu from "./Menu";
import styled from "styled-components/macro";
import { useState } from "react";

export default function Header() {
  const [open, setOpen] = useState(false);

  return (
    <Wrapper>
      <div>
        <Burger open={open} setOpen={setOpen} />
        <h2>Sympathise</h2>
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
    font-weight: lighter;
    text-transform: uppercase;
    margin: 0;
  }
`;
