import CounselingCenterCard from "../components/CounselingCenterCard";
import styled from "styled-components/macro";
import { useQuery } from "react-query";
import { loadCounselingCenterByQuery } from "../service/CounselingCenterService";

export default function ResultsListPage() {
  const { isLoading, isError, data, error } = useQuery("counselingCenter", () =>
    loadCounselingCenterByQuery(window.location.search)
  );

  if (isLoading) {
    return <span>Loading...</span>;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  return (
    <Wrapper>
      <h2>Beratungstellen in der Nähe</h2>
      <div>
        {data.map((center) => (
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
