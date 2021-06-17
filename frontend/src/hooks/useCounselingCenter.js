import axios from "axios";
import { useEffect, useState } from "react";

export default function useCounselingCenter(id) {
  const [counselingCenters, setCounselingCenters] = useState([]);
  const [counselingCenter, setCounselingCenter] = useState();

  useEffect(() => {
    axios
      .get("api/counseling")
      .then((response) => response.data)
      .then(setCounselingCenters)
      .catch((err) => console.log(err.message));
  }, []);

  useEffect(() => {
    axios
      .get(`api/counseling/${id}`)
      .then((response) => response.data)
      .then(setCounselingCenter)
      .catch((err) => console.log(err.message));
  }, [id]);

  return { counselingCenters, counselingCenter };
}
