import CheckButton from "./CheckButton";
import { useEffect, useState } from "react";

export default function TargetGroupCheckButtons({
  handleTargetGroupCheckButtonsChange,
}) {
  const [targetGroup, setTargetGroup] = useState([]);

  function handleChange(id) {
    const array = targetGroup.includes(id)
      ? targetGroup.filter((e) => e !== id)
      : [...targetGroup, id];
    setTargetGroup(array);
  }

  useEffect(() => {
    handleTargetGroupCheckButtonsChange(targetGroup);
    //eslint-disable-next-line react-hooks/exhaustive-deps
  }, [targetGroup]);

  return (
    <section>
      <p>Zielgruppe</p>
      <CheckButton
        id="INDIVIDUAL"
        description={"Betroffene"}
        handleChange={handleChange}
      />

      <CheckButton
        id="RELATIVES"
        description={"Angehörige"}
        handleChange={handleChange}
      />
    </section>
  );
}
