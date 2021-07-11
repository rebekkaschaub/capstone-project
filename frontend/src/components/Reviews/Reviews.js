import LoadingSpinnerSmall from "../../commons/LoadingSpinner";
import ReviewCard from "./ReviewCard";

export default function Reviews({ reviews }) {
  if (reviews.isLoading) {
    return <LoadingSpinnerSmall />;
  }

  if (reviews.isError) {
    return <span>Error: {reviews.error.message}</span>;
  }

  if (reviews.data.length === 0) {
    return <p>Noch keine Erfahrungsberichte. Gib den ersten ab!</p>;
  }

  return (
    <div>
      {reviews.data.map((review) => (
        <ReviewCard key={review.reviewId} review={review} />
      ))}
    </div>
  );
}
