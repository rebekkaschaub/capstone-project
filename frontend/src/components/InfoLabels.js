import Label from "./Label";
import styled from "styled-components/macro";

export default function InfoLabels({ details }) {
  return (
    <Labels>
      {details.specializations && (
        <section>
          {details.specializations.map((specialization) => (
            <Label
              key={specialization}
              text={specialization}
              color={"crimson"}
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
              color={"green"}
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
              color={"purple"}
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
`;
