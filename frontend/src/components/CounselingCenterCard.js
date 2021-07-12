import styled from "styled-components/macro";
import { useHistory } from "react-router-dom";
import AuthContext from "../context/AuthContext";
import { useContext } from "react";
import ReviewStats from "./Reviews/ReviewStats";
import { IoBookmarkSharp } from "react-icons/io5";

export default function CounselingCenterCard({ counselingCenter }) {
  const history = useHistory();
  const { userData } = useContext(AuthContext);

  const handleClick = () =>
    history.push(`/counseling/${counselingCenter.id}/details`);

  return (
    <Wrapper onClick={handleClick}>
      <h3>{counselingCenter.name}</h3>

      {userData && (
        <>
          <IoBookmarkSharp
            size={30}
            color={
              counselingCenter.bookmarkedBy.includes(userData.sub)
                ? "#1c3648"
                : "#c1c0b9"
            }
          />

          <ReviewStatsStyled id={counselingCenter.id} />
        </>
      )}
    </Wrapper>
  );
}

const Wrapper = styled.button`
  padding: 0 8px;
  margin: 8px 0;
  transition: 0.3s;
  box-shadow: 0 13px 10px -7px rgba(0, 0, 0, 0.1);
  font-size: small;
  background-color: #f7f6e7;
  border: none;
  border-radius: 12px;
  width: 100%;
  display: grid;
  grid-template-columns: 1fr min-content;
  grid-template-rows: 1fr min-content;

  img {
    width: 30px;
    height: 30px;
    justify-self: center;
  }

  &:hover {
    box-shadow: 0 15px 9px -7px rgba(0, 0, 0, 0.1);
    transform: scale(1.01, 1.01);
  }
`;

const ReviewStatsStyled = styled(ReviewStats)`
  margin: 0 0 5px 10px;
`;
