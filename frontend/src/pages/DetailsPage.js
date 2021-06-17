import { useParams } from "react-router-dom";
import useDetails from "../hooks/useDetails";

export default function DetailsPage() {
  const { id } = useParams();

  const { details } = useDetails(id);

  return (
    <>
      {details && (
        <>
          <h3>{details.name}</h3>
          <p>{details.address.street} </p>
          <p>
            {details.address.postalCode} {details.address.city}
          </p>
        </>
      )}
    </>
  );
}
