import { useParams } from "react-router-dom";
import { useMutation, useQuery } from "react-query";
import { loadCounselingCenterById } from "../service/CounselingCenterService";
import LoadingSpinner from "../components/LoadingSpinner";
import AuthContext from "../context/AuthContext";
import { useContext, useEffect} from "react";
import { loadReviewByReviewId, updateReview } from "../service/ReviewService";
import styled from "styled-components/macro";
import ReviewForm from "../components/ReviewForm";

export default function UpdateReviewForm() {
  const { id, reviewId } = useParams();
  const { userData, token } = useContext(AuthContext);
  const details = useQuery(["details", id], () => loadCounselingCenterById(id));
  const review = useQuery(["reviewById", reviewId], () =>
    loadReviewByReviewId(token, reviewId)
  );

  console.log(review.data);

  const initialState = {
    counselingCenterId: id,
    counselingCenterName: details.data?.name,
    author: userData.sub,
    title: review.data.title,
    rating: review.data.rating,
    comment: review.data.comment,
  };

  useEffect(() => {
    const reviewState = {
      ...initialState,
      title: review.data.title,
      rating: review.data.rating,
      comment: review.data.comment,
    };
    setInitialState(reviewState);
  }, [review]);

  const sendReview = useMutation((review) => {
    return updateReview(token, reviewId, review);
  });

  if (details.isLoading) {
    return <LoadingSpinner />;
  }

  if (details.isError) {
    return <span>Error: {details.error.message}</span>;
  }

  return (
    <Wrapper>
      <h2>Erfahrungsbericht zu {details.data.name} ändern</h2>
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
