import axios from "axios";

const config = (token) => ({
  headers: {
    Authorization: `Bearer ${token}`,
  },
});

export function loadReviewsById(token, id) {
  return axios
    .get(`/api/reviews/${id}`, config(token))
    .then((response) => response.data);
}

export function loadAllReviewsOfUSer(token) {
  return axios
    .get(`/api/reviews`, config(token))
    .then((response) => response.data);
}

export function addReview(token, review) {
  return axios
    .post(`/api/reviews`, review, config(token))
    .then((response) => response.data);
}

export function updateReview(token, id, review) {
  return axios
    .put(`/api/reviews/${id}`, review, config(token))
    .then((response) => response.data);
}

export function deleteReview(token, id) {
  return axios.delete(`/api/reviews/${id}`, config(token));
}