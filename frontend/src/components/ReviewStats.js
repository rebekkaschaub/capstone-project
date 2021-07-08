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
      {data.count > 0 && (
        <>
          <p>{data.average}</p>
          <FaStar size={13} color={"#FFC107"} />
          {data.count === 1 ? (
            <p>({data.count} Bewertung)</p>
          ) : (
            <p>({data.count} Bewertungen)</p>
          )}
        </>
      )}
    </Wrapper>
  );
}

const Wrapper = styled.section`
  display: flex;
  align-items: center;
  p {
    font-size: 13px;
    margin: 0;
  }

  p:nth-child(3) {
    margin-left: 7px;
  }
`;
