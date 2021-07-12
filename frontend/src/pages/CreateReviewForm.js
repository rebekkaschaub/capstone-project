import { useHistory, useParams } from "react-router-dom";
import { useMutation, useQuery, useQueryClient } from "react-query";
import { loadCounselingCenterById } from "../service/CounselingCenterService";
import LoadingSpinner from "../commons/LoadingSpinner";
import AuthContext from "../context/AuthContext";
import { useContext } from "react";
import { addReview } from "../service/ReviewService";
import styled from "styled-components/macro";
import ReviewForm from "../components/Reviews/ReviewForm";
import backIcon from "../images/backIcon.png";
import Headline from "../commons/Headline";

export default function CreateReviewForm() {
  const history = useHistory();
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
        <h3>Dein Erfahrungsbericht zu {data.name}</h3>
      </Headline>
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
