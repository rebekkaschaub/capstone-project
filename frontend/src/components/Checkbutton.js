import styled from "styled-components/macro";

export default styled.div`
  color: #fff;
  margin: 5px;
  background-color: #c1c0b9;
  border-radius: 4px;
  overflow: auto;
  float: left;

  &:hover {
    background-color: #808080;
  }

  label {
    float: left;
    min-width: 120px;
  }

  label span {
    font-size: 14px;
    text-align: center;
    padding: 9px;
    display: block;
  }

  label input {
    position: absolute;
    top: -20px;
  }

  input:checked + span {
    background-color: #1c3648;
    color: #fff;
  }
`;
