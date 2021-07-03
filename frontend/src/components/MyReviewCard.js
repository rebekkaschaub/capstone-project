import styled from "styled-components/macro";
import { FaStar } from "react-icons/fa";
import { MdDeleteForever, MdModeEdit } from "react-icons/md";
import StyledIconButton from "./StyledIconButton";

export default function MyReviewCard({ review }) {
  return (
    <Wrapper>
      <h3>{review.counselingCenterName}</h3>
      {[...Array(5)].map((star, index) => {
        const ratingValue = index + 1;
        return (
          <FaStar
            size={15}
            color={ratingValue <= review.rating ? "#FFC107" : "#c1c0b9"}
          />
        );
      })}
      <p>{review.comment}</p>
      <EditButtons>
        <StyledIconButton>
          <MdDeleteForever size={25} color={"#1C3648"} />
          <p>Löschen</p>
        </StyledIconButton>
        <StyledIconButton>
          <MdModeEdit size={25} color={"#1C3648"} />
          <p>bearbeiten</p>
        </StyledIconButton>
      </EditButtons>
    </Wrapper>
  );
}

const Wrapper = styled.section`
  padding: 4px;
  margin: 8px 0;
  transition: 0.3s;
  box-shadow: 0 13px 10px -7px rgba(0, 0, 0, 0.1);
  font-size: small;
  background-color: #f7f6e7;
  border: none;
  border-radius: 12px;
  width: 100%;

  h3 {
    margin: 5px 0;
  }

  p {
    font-size: 14px;
    width: 100%;
    overflow: hidden;
    display: inline-block;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin-bottom: 0;
  }
`;

const EditButtons = styled.section`
  display: flex;
  justify-content: flex-end;
  (StyledIconButton) {
    margin: 0 10px;
  }
`;
