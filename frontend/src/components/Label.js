import styled from "styled-components/macro";

export default function Label({ text }) {
  return <Item>{text}</Item>;
}

const Item = styled.div`
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  font-size: 9px;
  display: inline-block;
  background: crimson;
  color: white;
  border-radius: 13px;
  padding: 4px;
  margin: 2px;
`;
