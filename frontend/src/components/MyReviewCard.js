import styled from "styled-components/macro";
import { FaStar } from "react-icons/fa";
import { MdDeleteForever, MdModeEdit } from "react-icons/md";
import StyledIconButton from "./StyledIconButton";
import { useMutation, useQueryClient } from "react-query";
import { removeReview } from "../service/ReviewService";
import AuthContext from "../context/AuthContext";
import { useContext } from "react";
import { useHistory } from "react-router-dom";

export default function MyReviewCard({ review }) {
  const queryClient = useQueryClient();
  const history = useHistory();
  const { token } = useContext(AuthContext);

  const deleteReview = useMutation(() => removeReview(token, review.reviewId), {
    onSuccess: () => queryClient.invalidateQueries("myReviews"),
  });

  function handleEditButtonClick() {
    history.push(`/review/${review.counselingCenterId}/${review.reviewId}`);
  }

  return (
    <Wrapper>
      <p>{review.counselingCenterName}</p>
      {[...Array(5)].map((star, index) => {
        const ratingValue = index + 1;
        return (
          <FaStar
            key={index}
            size={15}
            color={ratingValue <= review.rating ? "#FFC107" : "#c1c0b9"}
          />
        );
      })}
      <p>{review.comment}</p>
      <EditButtons>
        <StyledIconButton onClick={deleteReview.mutate}>
          <MdDeleteForever size={25} color={"#1C3648"} />
          <p>Löschen</p>
        </StyledIconButton>
        <StyledIconButton onClick={handleEditButtonClick}>
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

  p {
    font-size: 14px;
    width: 100%;
    overflow: hidden;
    display: inline-block;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin-bottom: 0;
  }

  p:first-child {
    font-size: 16px;
    font-weight: bold;
    margin: 5px 0;
    overflow: visible;
    text-overflow: clip;
    white-space: normal;
  }
`;

const EditButtons = styled.section`
  display: flex;
  justify-content: flex-end;
`;
