import axios from "axios";

const config = (token) => ({
  headers: {
    Authorization: `Bearer ${token}`,
  },
});

export function listAllBookmarksOfUser(token) {
  return axios
    .get(`/api/bookmark`, config(token))
    .then((response) => response.data);
}

export function addNewBookmark(token, id) {
  return axios
    .post(`/api/bookmark`, { id }, config(token))
    .then((response) => response.data);
}
