import StarRating from "./StarRating";
import styled from "styled-components/macro";
import { useState } from "react";
import { useHistory } from "react-router-dom";

export default function ReviewForm({ buttonLabel, initialState, sendReview }) {
  const history = useHistory();
  const [review, setReview] = useState(initialState);

  function handleChange(event) {
    setReview({ ...review, [event.target.name]: event.target.value });
  }

  function handleSubmit(event) {
    event.preventDefault();
    sendReview.mutate(review);
    history.push("/me/reviews");
  }

  return (
    <Form onSubmit={handleSubmit}>
      <section>
        <p>Gesamtbewertung</p>
        <StarRating onChange={handleChange} value={review.rating} />
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
      <button>{buttonLabel}</button>
    </Form>
  );
}

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
