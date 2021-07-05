import emergency from "../images/remi-boyer-5pthQnMi5hY-unsplash.jpg";
import compass from "../images/ali-kazal-2mg-3crJFgk-unsplash.jpg";
import stones from "../images/deniz-altindas-t1XLQvDqt_4-unsplash.jpg";

import styled from "styled-components/macro";
import { MdChevronRight } from "react-icons/md";
import { useHistory } from "react-router-dom";

export default function LandingPage() {
  const history = useHistory();
  return (
    <div>
      <h2>Finde Deine Beratungsstelle</h2>
      <Card onClick={() => history.push("/")}>
        <img src={emergency} alt="Life buoy" />

        <div>
          <h2>Notfallhilfe</h2>
          <p>
            Wenn Du akut Hilfe brauchst, geh auf die Seite Notfallhilfe. Dort
            werden Dir Telefonnummern und Adressen für den Notfall
            bereitgestellt.
          </p>
          <MdChevronRight size={30} color={"#1c3648"} />
        </div>
      </Card>

      <Card onClick={() => history.push("/search")}>
        <img src={compass} alt="A person is holding a conpass" />

        <div>
          <h2>Hilfe finden</h2>
          <p>
            Brauchst Du Hilfe in einer schwierigen Situation oder Hilfe für
            Angehörige, kannst du hier Beratungsstellen in der Nähe finden.
          </p>
          <MdChevronRight size={30} color={"#1c3648"} />
        </div>
      </Card>

      <Card onClick={() => history.push("/login")}>
        <img src={stones} alt="Stone tower with water in the background" />

        <div>
          <h2>Account anlegen</h2>
          <p>
            Lege Dir einen Account an, um Beratungsstellen zu speichern,
            Erfahrungsberichte von anderen Usern sehen und selbst
            Beratungsstelle zu bewerten.
          </p>
          <MdChevronRight size={30} color={"#1c3648"} />
        </div>
      </Card>
    </div>
  );
}

const Card = styled.section`
  display: flex;
  transition: 0.3s;
  box-shadow: 0 13px 10px -7px rgba(0, 0, 0, 0.1);
  font-size: small;
  background-color: #f7f6e7;
  border: none;
  border-radius: 12px;
  width: 100%;
  overflow: hidden;
  padding-right: 10px;
  margin: 10px 0;
  img {
    width: 130px;
    height: 190px;
    margin-right: 10px;
  }
  h2 {
    margin-bottom: 0;
    font-size: 16px;
  }

  &:hover {
    box-shadow: 0 15px 9px -7px rgba(0, 0, 0, 0.1);
    transform: scale(1.01, 1.01);
  }
`;
