import styled from "styled-components/macro";
import Checkbutton from "../components/Checkbutton";
import { useState } from "react";
import useCounselingCenter from "../hooks/useCounselingCenter";
import { useHistory } from "react-router-dom";

export default function FilterPage() {
  const history = useHistory();
  const [queryObject, setQueryObject] = useState({
    city: null,
    postalCode: null,
    specialization: "",
    targetGroup: [],
    counselingSetting: [],
  });

  const { submitQuery } = useCounselingCenter(queryObject);

  function handleSubmit(event) {
    event.preventDefault();
    submitQuery();
    history.push(`/counseling`);
  }

  function handleChange(event) {
    const query = { ...queryObject, [event.target.id]: event.target.value };
    setQueryObject(query);
  }

  function handleClickTargetGroup(event) {
    const query = {
      ...queryObject,
      targetGroup: event.target.checked
        ? [...queryObject.targetGroup, event.target.id]
        : queryObject.targetGroup.filter((e) => e !== event.target.id),
    };
    setQueryObject(query);
    console.log(query);
  }

  function handleClickCounselingSetting(event) {
    const query = {
      ...queryObject,
      counselingSetting: event.target.checked
        ? [...queryObject.counselingSetting, event.target.id]
        : queryObject.counselingSetting.filter((e) => e !== event.target.id),
    };
    setQueryObject(query);
    console.log(query);
  }

  return (
    <Wrapper onSubmit={handleSubmit}>
      <h2>Beratungsstelle finden</h2>
      <label>
        <p>Beratungsschwerpunkt</p>
        <select
          id="specialization"
          value={queryObject.specialization}
          onChange={handleChange}
        >
          <option value="">Alle Beratungsstellen</option>
          <option value="ALLEINERZIEHENDE">
            Beratung alleinerziehender Mütter und Väter
          </option>
          <option value="EHEBERATUNG">Eheberatung</option>
          <option value="TRENNUNG_UND_SCHEIDUNG">
            Beratung bei Trennung und Scheidung
          </option>
          <option value="ERZIEHUNGSBERATUNG">Erziehungsberatung</option>
          <option value="FAMILIENBERATUNG">Familienberatung</option>
          <option value="FRAUEN">Hilfe und Beratung für Frauen</option>
          <option value="GEWALTOPFER">
            Beratung für Opfer jeglicher Gewalt
          </option>
          <option value="GEWALTTAETER">Beratung für Gewalttäter*innen</option>
          <option value="STI">HIV- und STI-Beratung</option>
          <option value="JUGENDBERATUNG">Jugendberatung</option>
          <option value="KRISENINTERVENTION">Krisenintervention</option>
          <option value="KINDER">Beratung für Kinder und Jugendliche</option>
          <option value="LEBENSBERATUNG">Lebensberatung</option>
          <option value="LSBTIQ">
            Beratung für Lesben, Schwule, Bi-, Trans- und Intersexuelle (LSBTI)
          </option>
          <option value="MIGRATION">
            Beratung für Migration, Flüchtlinge und Spätaussiedler*innen
          </option>
          <option value="PARTNERSCHAFT">Partnerschaftsberatung</option>
          <option value="PSYCHISCH">
            Beratung für Menschen mit psychischer Erkrankung
          </option>
          <option value="ESSSTOERUNG">
            Beratung für Menschen mit Esstörungen
          </option>
          <option value="SEXUALBERATUNG">Sexualberatung</option>
          <option value="SOZIALBERATUNG">Sozialberatung</option>
          <option value="SUCHT">Suchtberatung</option>
          <option value="TRAUMA">Trauma</option>
        </select>
      </label>

      <Location>
        <label className="location">
          <p>PLZ</p>
          <input type="text" id="postalCode" onChange={handleChange} />
        </label>
        <label className="location">
          <p>Ort</p>
          <input type="text" id="city" onChange={handleChange} />
        </label>
      </Location>

      <section>
        <p>Art der Beratung</p>
        <Checkbutton>
          <label>
            <input
              type="checkbox"
              id="INPERSON"
              className="counselingSetting"
              onClick={handleClickCounselingSetting}
            />
            <span>persönlich</span>
          </label>
        </Checkbutton>

        <Checkbutton>
          <label>
            <input
              type="checkbox"
              id="PHONE"
              className="counselingSetting"
              onClick={handleClickCounselingSetting}
            />
            <span>telefonisch</span>
          </label>
        </Checkbutton>

        <Checkbutton>
          <label>
            <input
              type="checkbox"
              id="CHAT"
              className="counselingSetting"
              onClick={handleClickCounselingSetting}
            />
            <span>chat</span>
          </label>
        </Checkbutton>
        <Checkbutton>
          <label>
            <input
              type="checkbox"
              id="GROUP"
              className="counselingSetting"
              onClick={handleClickCounselingSetting}
            />
            <span>Gruppenarbeit</span>
          </label>
        </Checkbutton>
      </section>

      <section>
        <p>Zielgruppe</p>
        <Checkbutton>
          <label>
            <input
              type="checkbox"
              id="INDIVIDUAL"
              className="targetGroup"
              onClick={handleClickTargetGroup}
            />
            <span>Betroffene</span>
          </label>
        </Checkbutton>

        <Checkbutton>
          <label>
            <input
              type="checkbox"
              id="RELATIVES"
              className="targetGroup"
              onClick={handleClickTargetGroup}
            />
            <span>Angehörige</span>
          </label>
        </Checkbutton>
      </section>
      <button>Suchen</button>
    </Wrapper>
  );
}

const Wrapper = styled.form`
  display: grid;
  grid-template-rows: min-content 1fr 1fr 1fr 1fr min-content;
  row-gap: 10px;
  height: 100%;
  justify-content: space-evenly;

  h2 {
    margin: 0;
  }

  p {
    margin-bottom: 4px;
    font-weight: bold;
    font-size: 16px;
  }

  select {
    width: 100%;
    text-overflow: ellipsis;
    font-family: inherit;
    color: inherit;
  }

  input,
  select {
    border: 1px solid #1c3648;
    border-radius: 4px;
    background-color: #fff;
    padding: 6px 2px;
    font-size: 14px;
  }

  button {
    width: 100%;
    padding: 8px 0;
    font-size: 18px;
    color: #fff;
    background-color: #1c3648;
    border: none;
    border-radius: 4px;
  }
`;

const Location = styled.div`
  display: flex;
  justify-content: space-between;
  
  .location {
    width: 50%;
    
    input {
      width: 90%;
    }
`;
