import styled from "styled-components/macro";
import { useHistory } from "react-router-dom";
import bookmark_marked from "../images/bookmark_marked.png";
import AuthContext from "../context/AuthContext";
import { useContext } from "react";
import bookmark_unmarked from "../images/bookmark_unmarked.png";
import ReviewStats from "./ReviewStats";

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
          {counselingCenter.bookmarkedBy.includes(userData.sub) ? (
            <img src={bookmark_marked} alt="red bookmark icon" />
          ) : (
            <img src={bookmark_unmarked} alt="bookmark icon with blue border" />
          )}
          <ReviewStats id={counselingCenter.id} />
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

  > section {
    margin: 0 0 5px 10px;
  }

  &:hover {
    box-shadow: 0 15px 9px -7px rgba(0, 0, 0, 0.1);
    transform: scale(1.01, 1.01);
  }
`;
