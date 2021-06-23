import styled from "styled-components/macro";

export default function SelectBox({ specialization, handleChange }) {
  return (
    <StyledSelectBox>
      <p>Beratungsschwerpunkt</p>
      <select
        id="specialization"
        value={specialization}
        onChange={handleChange}
      >
        <option value="ALL">Alle Beratungsstellen</option>
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
        <option value="GEWALTOPFER">Beratung für Opfer jeglicher Gewalt</option>
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
    </StyledSelectBox>
  );
}

const StyledSelectBox = styled.label`
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
    border: 1px solid #1c3648;
    border-radius: 4px;
    background-color: #fff;
    padding: 6px 2px;
    font-size: 14px;
  }
`;
