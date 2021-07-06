import { useParams } from "react-router-dom";
import { useMutation, useQuery } from "react-query";
import { loadCounselingCenterById } from "../service/CounselingCenterService";
import LoadingSpinner from "../components/LoadingSpinner";
import AuthContext from "../context/AuthContext";
import { useContext } from "react";
import { updateReview } from "../service/ReviewService";
import styled from "styled-components/macro";
import ReviewForm from "../components/ReviewForm";

export default function UpdateReviewForm() {
  const { id, reviewId } = useParams();
  const { userData, token } = useContext(AuthContext);
  const { isLoading, isError, data, error } = useQuery(["details", id], () =>
    loadCounselingCenterById(id)
  );

  const initialState = {
    counselingCenterId: id,
    counselingCenterName: data?.name,
    author: userData.sub,
    title: "testtitle",
    rating: 5,
    comment: "testcomment",
  };

  const sendReview = useMutation((review) => {
    return updateReview(token, reviewId, review);
  });

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  return (
    <Wrapper>
      <h2>Erfahrungsbericht zu {data.name} ändern</h2>
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
