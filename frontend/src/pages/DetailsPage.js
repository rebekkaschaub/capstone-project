import { useHistory, useParams } from "react-router-dom";
import styled from "styled-components/macro";
import Button from "../components/Button";
import InfoLabels from "../components/InfoLabels";
import { useQuery } from "react-query";
import { loadCounselingCenterById } from "../service/CounselingCenterService";
import LoadingSpinner from "../components/LoadingSpinner";

export default function DetailsPage() {
  const history = useHistory();
  const { id } = useParams();

  const handleClick = () => history.goBack();
  const { isLoading, isError, data, error } = useQuery(["details", id], () =>
    loadCounselingCenterById(id)
  );

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  return (
    <Details>
      <Button onClick={handleClick}>Zurück zu den Ergebnissen</Button>
      <Wrapper>
        <h3>{data.name}</h3>
        <p>{data.address.street} </p>
        <p>
          {data.address.postalCode} {data.address.city}
        </p>
        <p>Telefon: {data.phoneNo}</p>
        <p>Mail: {data.email}</p>
        <a href={data.url} target="_blank" rel="noreferrer">
          Zur Website
        </a>

        <br />

        <InfoLabels details={data} />
      </Wrapper>
    </Details>
  );
}

const Wrapper = styled.div`
  overflow-wrap: anywhere;
  h3 {
    margin: 0;
  }
`;

const Details = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;
