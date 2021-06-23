import axios from "axios";
import { useEffect, useState } from "react";

export default function useCounselingCenterByQuery(queryObject) {
  const [counselingCenters, setCounselingCenters] = useState([]);

  useEffect(() => {
    axios
      .post(`/api/counseling/filter`, queryObject)
      .then((response) => response.data)
      .then(setCounselingCenters)
      .catch(console.error);
  }, []);

  return { counselingCenters };
}
