import { useParams } from "react-router-dom";
import { useMutation, useQuery, useQueryClient } from "react-query";
import { loadCounselingCenterById } from "../service/CounselingCenterService";
import LoadingSpinner from "../components/LoadingSpinner";
import AuthContext from "../context/AuthContext";
import { useContext } from "react";
import { addReview } from "../service/ReviewService";
import styled from "styled-components/macro";
import ReviewForm from "../components/ReviewForm";

export default function CreateReviewForm() {
  const queryClient = useQueryClient();
  const { id } = useParams();
  const { userData, token } = useContext(AuthContext);
  const { isLoading, isError, data, error } = useQuery(["details", id], () =>
    loadCounselingCenterById(id)
  );

  const initialState = {
    counselingCenterId: id,
    counselingCenterName: data?.name,
    author: userData.sub,
    title: "",
    rating: 0,
    comment: "",
  };

  const sendReview = useMutation((review) => addReview(token, review), {
    onSuccess: () => {
      queryClient.invalidateQueries("myReviews");
    },
  });

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  return (
    <Wrapper>
      <h2>Dein Erfahrungsbericht zu {data.name}</h2>
      <ReviewForm
        buttonLabel={"absenden"}
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
