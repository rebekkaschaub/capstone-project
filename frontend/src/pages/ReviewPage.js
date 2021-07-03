import AuthContext from "../context/AuthContext";
import { useQuery } from "react-query";
import LoadingSpinner from "../components/LoadingSpinner";
import { useContext } from "react";
import MyReviewCard from "../components/MyReviewCard";
import { loadAllReviewsOfUSer } from "../service/ReviewService";

export default function ReviewPage() {
  const { token } = useContext(AuthContext);

  const { isLoading, isError, data, error } = useQuery("myReviews", () =>
    loadAllReviewsOfUSer(token)
  );

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  if (data.length === 0) {
    return <p>Noch keine Erfahrungsberichte abgegeben</p>;
  }

  return (
    <div>
      <h2>Deine Erfahrungsberichte</h2>
      {data.map((el) => (
        <MyReviewCard key={el.id} review={el} />
      ))}
    </div>
  );
}
