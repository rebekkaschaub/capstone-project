import CheckButton from "./CheckButton";
import { useEffect, useState } from "react";

export default function CounselingSettingCheckButtons({
  handleSettingCheckButtonsChange,
}) {
  const [counselingSetting, setCounselingSetting] = useState([]);

  function handleChange(id) {
    const array = counselingSetting.includes(id)
      ? counselingSetting.filter((e) => e !== id)
      : [...counselingSetting, id];
    setCounselingSetting(array);
  }

  useEffect(() => {
    handleSettingCheckButtonsChange(counselingSetting);
  }, [counselingSetting]);

  return (
    <section>
      <p>Art der Beratung</p>
      <CheckButton
        id={"INPERSON"}
        className={"counselingSetting"}
        description={"persönlich"}
        handleChange={handleChange}
      />

      <CheckButton
        id={"PHONE"}
        className={"counselingSetting"}
        description={"telefonisch"}
        handleChange={handleChange}
      />

      <CheckButton
        id={"CHAT"}
        className={"counselingSetting"}
        description={"chat"}
        handleChange={handleChange}
      />

      <CheckButton
        id={"GROUP"}
        className={"counselingSetting"}
        description={"Gruppenberatung"}
        handleChange={handleChange}
      />
    </section>
  );
}
