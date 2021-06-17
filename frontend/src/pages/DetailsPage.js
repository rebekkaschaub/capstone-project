import { useParams } from "react-router-dom";
import useCounselingCenter from "../hooks/useCounselingCenter";

export default function DetailsPage() {
  const { id } = useParams();
  const { counselingCenter } = useCounselingCenter(id);

  return (
    <>
      <h3>{counselingCenter.name}</h3>
      <p>{counselingCenter.address.street} </p>
      <p>
        {counselingCenter.address.postalCode} {counselingCenter.address.city}
      </p>
    </>
  );
}
