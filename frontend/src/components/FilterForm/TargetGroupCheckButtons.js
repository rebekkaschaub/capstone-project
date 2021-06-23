import CheckButton from "./CheckButton";
import { useEffect, useState } from "react";

export default function TargetGroupCheckButtons({
  handleTargetGroupCheckButtonsChange,
}) {
  const [targetGroup, setTargetGroup] = useState([]);

  function handleChange(id) {
    console.log(id);
    const array = targetGroup.includes(id)
      ? targetGroup.filter((e) => e !== id)
      : [...targetGroup, id];
    setTargetGroup(array);
  }

  useEffect(() => {
    console.log(targetGroup);
    handleTargetGroupCheckButtonsChange(targetGroup);
  }, [targetGroup]);

  return (
    <section>
      <p>Zielgruppe</p>
      <CheckButton
        id="INDIVIDUAL"
        className="targetGroup"
        description={"Betroffene"}
        handleChange={handleChange}
      />

      <CheckButton
        id="RELATIVES"
        className="targetGroup"
        description={"Angehörige"}
        handleChange={handleChange}
      />
    </section>
  );
}