import styled from "styled-components/macro";
import { FaStar } from "react-icons/fa";

export default function CounselingCenterReviewCard({ review }) {
  return (
    <Wrapper>
      <Title>{review.title}</Title>
      <Info>
        <p>von {review.author}</p>
        <section>
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
        </section>
      </Info>

      <p>{review.comment}</p>
    </Wrapper>
  );
}

const Wrapper = styled.section`
  padding: 8px;
  margin: 8px 0;
  transition: 0.3s;
  box-shadow: 0 13px 10px -7px rgba(0, 0, 0, 0.1);
  font-size: small;
  background-color: #f7f6e7;
  border: none;
  border-radius: 12px;
  width: 100%;
`;

const Info = styled.div`
  display: flex;
  p {
    margin: 0;
  }
`;

const Title = styled.p`
  font-size: 14px;
  font-weight: bold;
  margin: 5px 0;
`;
