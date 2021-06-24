import CounselingCenterCard from "../components/CounselingCenterCard";
import styled from "styled-components/macro";
import useCounselingCenterByQuery from "../hooks/useCounselingCenterByQuery";

export default function ResultsListPage() {
  const { counselingCenters } = useCounselingCenterByQuery(
    window.location.search
  );

  return counselingCenters.length !== 0 ? (
    <Wrapper>
      <h2>Beratungstellen in der Nähe</h2>
      <div>
        {counselingCenters.map((center) => (
          <CounselingCenterCard key={center.id} counselingCenter={center} />
        ))}
      </div>
    </Wrapper>
  ) : (
    <p>Loading</p>
  );
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #ffff;

  div {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
  }

  h2 {
    margin: 0;
    font-size: medium;
  }
`;
