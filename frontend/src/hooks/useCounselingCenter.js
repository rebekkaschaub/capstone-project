import axios from "axios";
import { useState } from "react";

export default function useCounselingCenter() {
  const [counselingCenters, setCounselingCenters] = useState([]);

  // useEffect(() => {
  //   axios
  //     .get("/api/counseling")
  //     .then((response) => response.data)
  //     .then(setCounselingCenters)
  //     .catch((err) => console.log(err.message));
  // }, []);

  const submitQuery = (queryObject) =>
    axios
      .post("/api/counseling/filter", queryObject)
      .then((response) => response.data)
      .then(setCounselingCenters)
      .catch(console.error);

  return { counselingCenters, submitQuery };
}
