import CounselingCenterCard from "../components/CounselingCenterCard";
import styled from "styled-components/macro";
import useQuery from "../utils/useQuery";
import useCounselingCenterByQuery from "../hooks/useCounselingCenterByQuery";

export default function ResultsListPage() {
  const city = useQuery().get("city");
  const postalCode = useQuery().get("postalCode");
  const specialization = useQuery().get("specialization");
  const targetGroup = useQuery().getAll("targetGroup");
  const counselingSetting = useQuery().getAll("counselingSetting");

  const { counselingCenters } = useCounselingCenterByQuery({
    city,
    postalCode,
    specialization,
    targetGroup,
    counselingSetting,
  });

  return counselingCenters.length === 0 ? (
    <p>Loading</p>
  ) : (
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
