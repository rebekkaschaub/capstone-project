import { useEffect, useState } from "react";
import axios from "axios";

export default function useDetails(id) {
  const [details, setDetails] = useState();

  useEffect(() => {
    axios
      .get(`/api/counseling/${id}`)
      .then((response) => response.data)
      .then(setDetails)
      .catch((err) => console.log(err.message));
  }, [id]);

  return { details };
}
