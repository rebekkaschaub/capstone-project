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

export function loadReviewByReviewId(token, reviewId) {
  console.log(reviewId);
  return axios
    .get(`/api/reviews/review/${reviewId}`, config(token))
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

export function removeReview(token, id) {
  return axios.delete(`/api/reviews/${id}`, config(token));
}

export function loadReviewStats(token, counselingCenterId) {
  return axios
    .get(`/api/reviews/stats/${counselingCenterId}`, config(token))
    .then((response) => response.data);
}
