import styled from "styled-components/macro";

export default function CounselingCenterCard({ counselingCenter }) {
  return (
    <Wrapper>
      <h2>{counselingCenter.name}</h2>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  padding: 4px;
  margin: 10px 5px;
  transition: 0.3s;
  box-shadow: 0px 8px 5px -5px rgba(0, 0, 0, 0.1);
  font-size: 12px;
  background-color: #ffff;
  border-radius: 12px;

  &:hover {
    box-shadow: 0px 15px 9px -7px rgba(0, 0, 0, 0.1);
    transform: scale(1.01, 1.01);
  }
`;
