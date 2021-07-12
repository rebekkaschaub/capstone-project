import { useMutation, useQueryClient } from "react-query";
import { updateBookmarks } from "../service/BookmarkService";
import IconButton from "./IconButton";
import { IoBookmarkSharp } from "react-icons/io5";

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
      <IconButton onClick={updateBookmark.mutate}>
        <IoBookmarkSharp size={30} color={"#c1c0b9"} />
        <p>entfernen</p>
      </IconButton>
    );
  }

  return (
    <IconButton onClick={updateBookmark.mutate}>
      <IoBookmarkSharp size={30} color={"#1c3648"} />
      <p>merken</p>
    </IconButton>
  );
}
