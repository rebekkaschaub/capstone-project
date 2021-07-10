import CounselingCenterCard from "../CounselingCenterCard";
import styled from "styled-components/macro";
import Button from "../../commons/Button";

export default function ResultsList({ results, setDisplayMap }) {
  const handleClick = () => setDisplayMap(true);

  return (
    <Wrapper>
      <Button onClick={handleClick}>Auf Karte ansehen</Button>
      {results.map((center) => (
        <CounselingCenterCard key={center.id} counselingCenter={center} />
      ))}
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;

  Button {
    margin-top: 8px;
  }
`;
