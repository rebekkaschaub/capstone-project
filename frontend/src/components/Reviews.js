import { useQuery } from "react-query";
import { loadReviewsById } from "../service/ReviewService";
import { useContext } from "react";
import AuthContext from "../context/AuthContext";
import LoadingSpinner from "./LoadingSpinner";
import ReviewCard from "./ReviewCard";

export default function Reviews({ id }) {
  const { token } = useContext(AuthContext);
  const { isLoading, isError, data, error } = useQuery(["reviews", id], () =>
    loadReviewsById(token, id)
  );

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  if (data.length === 0) {
    return <p>Noch keine Erfahrungsberichte. Gib den ersten ab!</p>;
  }

  return (
    <div>
      {data.map((review) => (
        <ReviewCard key={review.id} review={review} />
      ))}
    </div>
  );
}
