import { useMutation, useQueryClient } from "react-query";
import { updateBookmarks } from "../service/BookmarkService";
import bookmark_unmarked from "../images/bookmark_unmarked.png";
import bookmark_marked from "../images/bookmark_marked.png";
import StyledIconButton from "./StyledIconButton";

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
      <StyledIconButton onClick={updateBookmark.mutate}>
        <img src={bookmark_marked} alt="red bookmark icon" />
        <p>entfernen</p>
      </StyledIconButton>
    );
  }

  return (
    <StyledIconButton onClick={updateBookmark.mutate}>
      <img src={bookmark_unmarked} alt="bookmark icon with blue border" />
      <p>merken</p>
    </StyledIconButton>
  );
}
