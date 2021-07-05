import { useQuery } from "react-query";
import { listAllBookmarksOfUser } from "../service/BookmarkService";
import styled from "styled-components/macro";
import { useContext } from "react";
import AuthContext from "../context/AuthContext";
import LoadingSpinner from "../components/LoadingSpinner";
import CounselingCenterCard from "../components/CounselingCenterCard";

export default function BookmarkedPage() {
  const { token } = useContext(AuthContext);

  const { isLoading, isError, data, error } = useQuery("bookmarks", () =>
    listAllBookmarksOfUser(token)
  );

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  if (data.length === 0) {
    return <p>Noch keine gespeicherten Beratungsstellen</p>;
  }

  return (
    <Wrapper>
      <h2>Gemerkt</h2>
      {data.map((el) => (
        <CounselingCenterCard key={el.id} counselingCenter={el} />
      ))}
    </Wrapper>
  );
}

const Wrapper = styled.div``;
