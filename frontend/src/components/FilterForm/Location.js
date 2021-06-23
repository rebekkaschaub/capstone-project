import styled from "styled-components/macro";

export default function Location({ handleChange }) {
  return (
    <Wrapper>
      {" "}
      <label className="location">
        <p>PLZ</p>
        <input type="text" id="postalCode" onChange={handleChange} />
      </label>
      <label className="location">
        <p>Ort</p>
        <input type="text" id="city" onChange={handleChange} />
      </label>
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
