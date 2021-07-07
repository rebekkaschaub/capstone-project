import { useParams } from "react-router-dom";
import { useMutation, useQuery, useQueryClient } from "react-query";
import LoadingSpinner from "../components/LoadingSpinner";
import AuthContext from "../context/AuthContext";
import { useContext } from "react";
import { loadReviewByReviewId, updateReview } from "../service/ReviewService";
import styled from "styled-components/macro";
import ReviewForm from "../components/ReviewForm";

export default function UpdateReviewForm() {
  const queryClient = useQueryClient();
  const { id, reviewId } = useParams();
  const { userData, token } = useContext(AuthContext);

  const { isLoading, isError, data, error } = useQuery(
    ["reviewById", reviewId],
    () => loadReviewByReviewId(token, reviewId)
  );

  const initialState = {
    counselingCenterId: id,
    counselingCenterName: data?.counselingCenterName,
    author: userData.sub,
    title: data?.title,
    rating: data?.rating,
    comment: data?.comment,
  };

  const sendReview = useMutation(
    (review) => {
      return updateReview(token, reviewId, review);
    },
    {
      onSuccess: () => {
        queryClient.invalidateQueries("myReviews");
      },
    }
  );

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  return (
    <Wrapper>
      <h2>Erfahrungsbericht zu {data?.counselingCenterName} ändern</h2>
      <ReviewForm
        buttonLabel={"ändern"}
        initialState={initialState}
        sendReview={sendReview}
      />
    </Wrapper>
  );
}

const Wrapper = styled.div`
  padding-top: 10px;
  display: grid;
  grid-template-rows: min-content 1fr;
  row-gap: 5px;
  justify-items: center;
  height: 100%;

  h2 {
    margin: 0;
    font-size: 20px;
  }
`;
