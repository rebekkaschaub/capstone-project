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
    //eslint-disable-next-line react-hooks/exhaustive-deps
  }, [counselingSetting]);

  return (
    <section>
      <p>Art der Beratung</p>
      <CheckButton
        id={"INPERSON"}
        description={"persönlich"}
        handleChange={handleChange}
      />

      <CheckButton
        id={"PHONE"}
        description={"telefonisch"}
        handleChange={handleChange}
      />

      <CheckButton
        id={"CHAT"}
        description={"chat"}
        handleChange={handleChange}
      />

      <CheckButton
        id={"GROUP"}
        description={"Gruppenberatung"}
        handleChange={handleChange}
      />
    </section>
  );
}
