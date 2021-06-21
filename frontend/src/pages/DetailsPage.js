import { useHistory, useParams } from "react-router-dom";
import useDetails from "../hooks/useDetails";
import styled from "styled-components/macro";
import Button from "../components/Button";
import InfoLabels from "../components/InfoLabels";

export default function DetailsPage() {
  const history = useHistory();
  const { id } = useParams();
  const { details } = useDetails(id);

  const handleClick = () => history.goBack();

  return (
    <Details>
      <Button onClick={handleClick}>Zurück zu den Ergebnissen</Button>
      {details && (
        <Wrapper>
          <h3>{details.name}</h3>
          <p>{details.address.street} </p>
          <p>
            {details.address.postalCode} {details.address.city}
          </p>
          <p>Telefon: {details.phoneNo}</p>
          <p>Mail: {details.email}</p>
          <a href={details.url}>Zur Website</a>

          <br />

          <InfoLabels details={details} />
        </Wrapper>
      )}
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
