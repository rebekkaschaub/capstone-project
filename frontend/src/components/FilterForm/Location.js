import styled from "styled-components/macro";

export default function Location({ handleChange }) {
  return (
    <Wrapper>
      <InputField>
        <p>PLZ</p>
        <input type="text" id="postalCode" onChange={handleChange} />
      </InputField>
      <InputField>
        <p>Ort</p>
        <input type="text" id="city" onChange={handleChange} />
      </InputField>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  
  .location {
    width: 50%;
    
    input {
      width: 90%;
    }
`;

const InputField = styled.label`
  width: 50%;

  input {
    width: 90%;
    border: 1px solid #1c3648;
    border-radius: 4px;
    background-color: #fff;
    padding: 6px 2px;
    font-size: 14px;
  }
`;
