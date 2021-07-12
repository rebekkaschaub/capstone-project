import { useHistory, useParams } from "react-router-dom";
import { useMutation, useQuery, useQueryClient } from "react-query";
import LoadingSpinner from "../commons/LoadingSpinner";
import AuthContext from "../context/AuthContext";
import { useContext } from "react";
import { loadReviewByReviewId, updateReview } from "../service/ReviewService";
import styled from "styled-components/macro";
import ReviewForm from "../components/Reviews/ReviewForm";
import backIcon from "../images/backIcon.png";
import Headline from "../commons/Headline";

export default function UpdateReviewForm() {
  const history = useHistory();
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

  const handleClick = () => history.goBack();

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  return (
    <Wrapper>
      <Headline onClick={handleClick}>
        <img src={backIcon} alt="Back Icon" />
        <h3>Erfahrungsbericht zu {data?.counselingCenterName} ändern</h3>
      </Headline>
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
