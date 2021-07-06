import { useHistory, useParams } from "react-router-dom";
import { useMutation, useQuery } from "react-query";
import { loadCounselingCenterById } from "../service/CounselingCenterService";
import LoadingSpinner from "../components/LoadingSpinner";
import AuthContext from "../context/AuthContext";
import { useContext, useState } from "react";
import { updateReview } from "../service/ReviewService";
import styled from "styled-components/macro";
import StarRating from "../components/StarRating";

export default function UpdateReviewForm() {
  const history = useHistory();
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

  const [review, setReview] = useState(initialState);

  const sendReview = useMutation(() => {
    return updateReview(token, reviewId, review);
  });

  function handleChange(event) {
    setReview({ ...review, [event.target.name]: event.target.value });
  }

  function handleSubmit(event) {
    event.preventDefault();
    sendReview.mutate(review);
    history.push("/me/reviews");
  }

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  return (
    <Wrapper>
      <h2>Dein Erfahrungsbericht zu {data.name}</h2>
      <Form onSubmit={handleSubmit}>
        <section>
          <p>Gesamtbewertung</p>
          <StarRating onChange={handleChange} />
        </section>
        <label>
          <p>Titel</p>
          <input
            type="text"
            name="title"
            value={review.title}
            onChange={handleChange}
            placeholder="max. 100 Zeichen"
            maxLength="100"
            required
          />
        </label>
        <label>
          <p>Erfahrungsbericht</p>
          <textarea
            name="comment"
            value={review.comment}
            onChange={handleChange}
            placeholder="max. 500 Zeichen"
            maxLength="500"
            rows={"10"}
            required
          />
        </label>
        <button>Bewertung ändern</button>
      </Form>
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

const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  width: 100%;
  height: 100%;

  section {
    width: 100%;
  }

  label {
    width: 100%;
  }

  p {
    margin-bottom: 4px;
    font-weight: bold;
    font-size: 16px;
  }

  input,
  textarea {
    border: 1px solid #1c3648;
    border-radius: 4px;
    background-color: #fff;
    padding: 12px 6px;
    font-size: 14px;
    font-family: inherit;
    width: 100%;
  }

  button {
    width: 100%;
    padding: 8px 0;
    font-size: 18px;
    color: #fff;
    background-color: #1c3648;
    border: none;
    border-radius: 4px;
  }

  button:disabled {
    background-color: darkgray;
  }
`;
