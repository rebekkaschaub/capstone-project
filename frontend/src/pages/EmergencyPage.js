import { FiExternalLink, FiHome, FiPhone } from "react-icons/fi";
import styled from "styled-components/macro";

export default function EmergencyPage() {
  return (
    <Wrapper>
      <h2>Notfallhilfe</h2>
      <ImportantCard>
        <p>
          Sobald in einer Situation{" "}
          <strong>
            unmittelbare Selbst- oder Fremdgefährdung (insbesondere
            Suizidgefährdung){" "}
          </strong>
          besteht, sollte man nicht zögern, sofort einen{" "}
          <strong>
            psychiatrischen Notdienst, den Rettungsdienst oder die Polizei{" "}
          </strong>
          zu verständigen.
        </p>
      </ImportantCard>
      <Card>
        <h2>Rettungswagen/Feuerwehr</h2>
        <StyledSection>
          <FiPhone />
          <p>112</p>
        </StyledSection>
      </Card>
      <Card>
        <h2>Polizei</h2>
        <StyledSection>
          <FiPhone />
          <p>110</p>
        </StyledSection>
      </Card>
      <Card>
        <h2>Giftnoruf</h2>
        <StyledSection>
          <FiPhone size={15} />
          <p>+49 551 19240</p>
        </StyledSection>
      </Card>
      <Card>
        <h2>Psychatrische Notaufnahme</h2>
        <StyledSection>
          <FiHome size={40} />
          <p>
            Asklepios Klinik Nord, Campus Ochsenzoll, Haus 5, Erdgeschoß
            Langenhorner Chaussee 560 22419 Hamburg{" "}
          </p>
        </StyledSection>
        <StyledSection>
          <FiPhone size={15} />
          <p>(040) 18 18 87-2143</p>
        </StyledSection>

        <StyledSection>
          <FiExternalLink size={15} />
          <a
            href="https://www.asklepios.com/hamburg/nord/psychiatrie-ochsenzoll/notfall/"
            target="_blank"
            rel="noreferrer"
          >
            Zur Website
          </a>
        </StyledSection>
      </Card>
      <Card>
        <h2>Telefonseelsorge</h2>
        <p>Anonyme, kostenlose Beratung zu jeder Tages- und Nachtzeit.</p>
        <StyledSection>
          <FiPhone />

          <p>0800-1110111 oder 0800-1110222</p>
        </StyledSection>
        <StyledSection>
          <FiExternalLink />
          <a
            href="https://www.telefonseelsorge.de"
            target="_blank"
            rel="noreferrer"
          >
            Zur Website
          </a>
        </StyledSection>
      </Card>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Card = styled.section`
  padding: 0 8px;
  margin: 8px 0;
  box-shadow: 0 13px 10px -7px rgba(0, 0, 0, 0.1);
  font-size: 15px;
  background-color: #f7f6e7;
  border: none;
  border-radius: 12px;
  width: 100%;
  p {
    margin: 5px;
  }
  h2 {
    margin-bottom: 5px;
  }
`;

const StyledSection = styled.section`
  display: flex;
  align-items: center;
  margin: 5px;

  a {
    text-decoration: none;
    color: #007185;
  }

  a,
  p {
    margin-left: 8px;
  }

  a:visited {
    color: #854e6c;
  }
`;

const ImportantCard = styled.section`
  padding: 8px;
  margin: 8px 0;
  box-shadow: 0 13px 10px -7px rgba(0, 0, 0, 0.1);
  font-size: small;
  background-color: #ce2c3a;
  color: #fff;
  border: none;
  border-radius: 12px;
  width: 100%;

  p {
    font-size: 15px;
    margin: 0;
  }
`;
