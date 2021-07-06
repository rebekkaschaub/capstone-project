import { FaStar } from "react-icons/fa";
import styled from "styled-components/macro";
import { useQuery } from "react-query";
import { loadReviewStats } from "../service/ReviewService";
import { useContext } from "react";
import AuthContext from "../context/AuthContext";
import LoadingSpinner from "./LoadingSpinner";

export default function ReviewStats({ id }) {
  const { token } = useContext(AuthContext);
  const { isLoading, isError, data, error } = useQuery(
    ["ReviewStats", id],
    () => loadReviewStats(token, id)
  );

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  return (
    <Wrapper>
      <p>{data.average}</p>
      <FaStar size={15} color={"#FFC107"} />
      <p>({data.count} Bewertungen)</p>
    </Wrapper>
  );
}

const Wrapper = styled.section`
  margin: 4px 10px;
  display: flex;
  p {
    margin: 0 0 0 10px;
  }
`;
