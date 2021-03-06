import styled from "styled-components/macro";
import { useState } from "react";
import { useHistory } from "react-router-dom";
import SelectBox from "../components/FilterForm/SelectBox";
import Location from "../components/FilterForm/Location";
import CounselingSettingCheckButtons from "../components/FilterForm/CounselingSettingCheckButtons";
import TargetGroupCheckButtons from "../components/FilterForm/TargetGroupCheckButtons";

export default function FilterPage() {
  const history = useHistory();
  const [queryObject, setQueryObject] = useState({
    city: null,
    postalCode: null,
    specialization: "ALL",
    targetGroup: [],
    counselingSetting: [],
  });

  function handleSubmit(event) {
    event.preventDefault();

    const path = ["/counseling?"];
    path.push(`specialization=${queryObject.specialization}`);
    if (queryObject.city) {
      path.push(`city=${queryObject.city}`);
    }
    if (queryObject.postalCode) {
      path.push(`postalCode=${queryObject.postalCode}`);
    }
    if (queryObject.targetGroup) {
      queryObject.targetGroup.map((e) => path.push(`targetGroup=${e}`));
    }
    if (queryObject.counselingSetting) {
      queryObject.counselingSetting.map((e) =>
        path.push(`counselingSetting=${e}`)
      );
    }

    history.push(path.join("&"));
  }

  function handleChange(event) {
    const query = { ...queryObject, [event.target.id]: event.target.value };
    setQueryObject(query);
  }

  function handleSettingCheckButtonsChange(element) {
    const query = {
      ...queryObject,
      counselingSetting: element,
    };
    setQueryObject(query);
  }

  function handleTargetGroupCheckButtonsChange(element) {
    const query = {
      ...queryObject,
      targetGroup: element,
    };
    setQueryObject(query);
  }

  return (
    <Wrapper onSubmit={handleSubmit}>
      <h2>Beratungsstelle finden</h2>
      <SelectBox
        handleChange={handleChange}
        specialization={queryObject.specialization}
      />

      <Location handleChange={handleChange} />

      <CounselingSettingCheckButtons
        handleSettingCheckButtonsChange={handleSettingCheckButtonsChange}
      />

      <TargetGroupCheckButtons
        handleTargetGroupCheckButtonsChange={
          handleTargetGroupCheckButtonsChange
        }
      />

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
