import axios from "axios";
import { useEffect, useState } from "react";

export default function useCounselingCenter() {
  const [counselingCenters, setCounselingCenters] = useState([]);

  useEffect(() => {
    axios
      .get("api/counseling")
      .then((response) => response.data)
      .then(setCounselingCenters)
      .catch((err) => console.log(err.message));
  }, []);

  return { counselingCenters };
}
