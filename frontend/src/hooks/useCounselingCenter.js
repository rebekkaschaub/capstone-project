import axios from "axios";
import { useEffect, useState } from "react";

export default function useCounselingCenter(queryObject) {
  const [counselingCenters, setCounselingCenters] = useState([]);

  useEffect(() => {
    axios
      .get("/api/counseling")
      .then((response) => response.data)
      .then(setCounselingCenters)
      .catch((err) => console.log(err.message));
  }, []);

  const submitQuery = () =>
    axios
      .post("/api/counseling/filter", queryObject)
      .then((response) => response.data)
      .then(setCounselingCenters)
      .catch(console.error);

  return { counselingCenters, submitQuery };
}
