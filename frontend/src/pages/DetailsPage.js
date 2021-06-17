import { useParams } from "react-router-dom";
import useDetails from "../hooks/useDetails";
import styled from "styled-components/macro";
import Label from "../components/Label";

export default function DetailsPage() {
  const { id } = useParams();

  const { details } = useDetails(id);

  return (
    <>
      {details && (
        <Wrapper>
          <h3>{details.name}</h3>
          {details.specializations && (
            <div>
              {details.specializations.map((specialization) => (
                <Label key={specialization} text={specialization} />
              ))}
            </div>
          )}
          {details.targetGroup && (
            <div>
              {details.targetGroup.map((group) => (
                <Label key={group.displayName} text={group.displayName} />
              ))}
            </div>
          )}
          {details.counselingSetting && (
            <div>
              {details.counselingSetting.map((setting) => (
                <Label key={setting.displayName} text={setting.displayName} />
              ))}
            </div>
          )}
          <p>{details.address.street} </p>
          <p>
            {details.address.postalCode} {details.address.city}
          </p>
          <p>Telefon: {details.phoneNo}</p>
          <p>Mail: {details.email}</p>
          <a href={details.url}>Zur Website</a>
        </Wrapper>
      )}
    </>
  );
}

const Wrapper = styled.div`
  h3 {
    margin: 0;
  }
`;
