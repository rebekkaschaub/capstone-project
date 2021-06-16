import styled from "styled-components/macro";
import { Link } from "react-router-dom";

export default function CounselingCenterCard({ counselingCenter }) {
  return (
    <Link to={`/counseling/${counselingCenter.id}/details`}>
      <Wrapper>
        <h3>{counselingCenter.name}</h3>
      </Wrapper>
    </Link>
  );
}

const Wrapper = styled.div`
  padding: 4px;
  margin: 10px 0;
  transition: 0.3s;
  box-shadow: -2px -2px 5px 2px rgba(0, 0, 0, 0.1);
  font-size: small;
  background-color: #ffff;
  border-radius: 12px;
  text-decoration: none;

  &:hover {
    box-shadow: 0 15px 9px -7px rgba(0, 0, 0, 0.1);
    transform: scale(1.01, 1.01);
  }
`;
