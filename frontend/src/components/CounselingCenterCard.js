import styled from "styled-components/macro";
import { useHistory } from "react-router-dom";

export default function CounselingCenterCard({ counselingCenter }) {
  const history = useHistory();
  const handleClick = () =>
    history.push(`/counseling/${counselingCenter.id}/details`);

  return (
    <Wrapper onClick={handleClick}>
      <h3>{counselingCenter.name}</h3>
    </Wrapper>
  );
}

const Wrapper = styled.button`
  padding: 4px;
  margin: 8px 0;
  transition: 0.3s;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
  font-size: small;
  background-color: #f7f6e7;
  border: none;
  border-radius: 12px;
  width: 100%;

  &:hover {
    box-shadow: 0 15px 9px -7px rgba(0, 0, 0, 0.1);
    transform: scale(1.01, 1.01);
  }
`;
