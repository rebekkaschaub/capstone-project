import Label from "./Label";
import styled from "styled-components/macro";

export default function InfoLabels({ details }) {
  return (
    <Labels>
      <h4>Schwerpunkte</h4>
      {details.specializations && (
        <section>
          {details.specializations.map((specialization) => (
            <Label
              key={specialization.abbreviation}
              text={specialization.description}
              color={"#005E71"}
            />
          ))}
        </section>
      )}
      {details.targetGroup && (
        <section>
          {details.targetGroup.map((group) => (
            <Label
              key={group.displayName}
              text={group.displayName}
              color={"#38B389"}
            />
          ))}
        </section>
      )}
      {details.counselingSetting && (
        <section>
          {details.counselingSetting.map((setting) => (
            <Label
              key={setting.displayName}
              text={setting.displayName}
              color={"#656688"}
            />
          ))}
        </section>
      )}
    </Labels>
  );
}

const Labels = styled.div`
  section {
    display: inline-block;
  }

  h4 {
    margin-bottom: 8px;
  }
`;
