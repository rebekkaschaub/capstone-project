import { FaStar } from "react-icons/fa";
import styled from "styled-components/macro";
import { useState } from "react";

export default function StarRating({ onChange, value }) {
  const [rating, setRating] = useState(value);
  const [hover, setHover] = useState(null);
  const handleClick = (event) => {
    setRating(event.target.value);
    onChange(event);
  };

  return (
    <Wrapper>
      {[...Array(5)].map((star, index) => {
        const ratingValue = index + 1;

        return (
          <label key={index}>
            <input
              type="radio"
              name="rating"
              value={ratingValue}
              onClick={handleClick}
            />
            <Star
              size={50}
              onMouseEnter={() => setHover(ratingValue)}
              onMouseLeave={() => setHover(null)}
              color={ratingValue <= (hover || rating) ? "#FFC107" : "#c1c0b9"}
            />
          </label>
        );
      })}
    </Wrapper>
  );
}

const Wrapper = styled.div`
  input {
    display: none;
  }
  display: flex;
  justify-content: center;
  padding: 0 15px;
`;

const Star = styled(FaStar)`
  cursor: pointer;
  transition: color 200ms;
`;
