import Tag from "./Tag";
import styled from "styled-components/macro";

export default function InfoTags({ details }) {
  return (
    <Tags>
      <h4>Schwerpunkte</h4>
      {details.specializations && (
        <section>
          {details.specializations.map((specialization) => (
            <Tag
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
            <Tag
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
            <Tag
              key={setting.displayName}
              text={setting.displayName}
              color={"#656688"}
            />
          ))}
        </section>
      )}
    </Tags>
  );
}

const Tags = styled.div`
  section {
    display: inline-block;
  }

  h4 {
    margin-bottom: 8px;
  }
`;
