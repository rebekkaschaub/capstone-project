import styled from "styled-components/macro";
import { useMutation, useQueryClient } from "react-query";
import { updateBookmarks } from "../service/BookmarkService";
import bookmark_unmarked from "../images/bookmark_unmarked.png";
import bookmark_marked from "../images/bookmark_marked.png";

export default function BookmarkButton({ marked, id, token }) {
  const queryClient = useQueryClient();

  const updateBookmark = useMutation(
    () => {
      return updateBookmarks(token, id);
    },
    {
      onSuccess: () => {
        queryClient.invalidateQueries("details");
      },
    }
  );

  if (marked) {
    return (
      <StyledBookmarkButton onClick={updateBookmark.mutate}>
        <img src={bookmark_marked} alt="red bookmark icon" />
        <p>entfernen</p>
      </StyledBookmarkButton>
    );
  }

  return (
    <StyledBookmarkButton onClick={updateBookmark.mutate}>
      <img src={bookmark_unmarked} alt="bookmark icon with blue border" />
      <p>merken</p>
    </StyledBookmarkButton>
  );
}

const StyledBookmarkButton = styled.button`
  margin: 10px 0;
  width: 70%;
  padding: 4px 0;
  background-color: transparent;
  border: none;
  border-radius: 4px;
  display: flex;
  align-items: center;

  img {
    width: 30px;
    height: 30px;
  }
`;
