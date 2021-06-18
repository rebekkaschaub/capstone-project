import Label from "./Label";
import styled from "styled-components/macro";

export default function InfoLabels({ details }) {
  return (
    <Labels>
      {details.specializations && (
        <div>
          {details.specializations.map((specialization) => (
            <Label
              key={specialization}
              text={specialization}
              color={"crimson"}
            />
          ))}
        </div>
      )}
      {details.targetGroup && (
        <div>
          {details.targetGroup.map((group) => (
            <Label
              key={group.displayName}
              text={group.displayName}
              color={"green"}
            />
          ))}
        </div>
      )}
      {details.counselingSetting && (
        <div>
          {details.counselingSetting.map((setting) => (
            <Label
              key={setting.displayName}
              text={setting.displayName}
              color={"purple"}
            />
          ))}
        </div>
      )}
    </Labels>
  );
}

const Labels = styled.div`
  div {
    display: inline-block;
  }
`;
