import styled from "styled-components/macro";

export default styled.section`
  display: flex;
  transition: 0.3s;
  box-shadow: 0 13px 10px -7px rgba(0, 0, 0, 0.1);
  font-size: small;
  background-color: #f7f6e7;
  border: none;
  border-radius: 12px;
  width: 100%;
  max-width: 800px;
  overflow: hidden;
  padding-right: 10px;
  margin: 10px 0;
  img {
    width: 130px;
    height: 190px;
    margin-right: 10px;
  }
  h2 {
    margin-bottom: 0;
    font-size: 16px;
  }

  &:hover {
    box-shadow: 0 15px 9px -7px rgba(0, 0, 0, 0.1);
    transform: scale(1.01, 1.01);
  }

  div {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }

  .react-icons {
    align-self: flex-end;
  }
`;
