import CounselingCenterCard from "../components/CounselingCenterCard";
import styled from "styled-components/macro";
import useCounselingCenter from "../hooks/useCounselingCenter";

export default function ResultsListPage() {
  const { counselingCenters } = useCounselingCenter();

  return (
    <Wrapper>
      <h2>Beratungstellen in der Nähe</h2>
      <div>
        {counselingCenters.map((center) => (
          <CounselingCenterCard key={center.id} counselingCenter={center} />
        ))}
      </div>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  background-color: #ffff;
  height: 100vh;

  h2 {
    margin: 0;
    font-size: medium;
  }
`;
