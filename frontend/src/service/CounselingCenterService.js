import axios from "axios";

export function loadCounselingCenterByQuery(query) {
  return axios
    .get(`/api/counseling/search${query}`)
    .then((response) => response.data);
}

export function loadCounselingCenterById(id) {
  return axios.get(`/api/counseling/${id}`).then((response) => response.data);
}
