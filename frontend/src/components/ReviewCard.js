import styled from "styled-components/macro";

export default function ReviewCard({ review }) {
  return (
    <Wrapper>
      <p>{review.title}</p>
      <p>{review.author}</p>
      <p>Stars: {review.rating}</p>
      <p>{review.comment}</p>
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
`;
