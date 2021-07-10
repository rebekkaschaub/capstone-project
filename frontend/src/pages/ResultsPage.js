import styled from "styled-components/macro";
import { useQuery } from "react-query";
import { loadCounselingCenterByQuery } from "../service/CounselingCenterService";
import ResultsList from "../components/Results/ResultsList";
import { useState } from "react";
import ResultsMap from "../components/Results/ResultsMap";
import { Link } from "react-router-dom";
import search from "../images/search_icon.png";
import LoadingSpinner from "../commons/LoadingSpinner";

export default function ResultsPage() {
  const [displayMap, setDisplayMap] = useState(false);

  const { isLoading, isError, data, error } = useQuery("counselingCenter", () =>
    loadCounselingCenterByQuery(window.location.search)
  );

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  if (data.length === 0) {
    return <p>Leider ergab die Suche keine Ergebnisse.</p>;
  }

  return (
    <Wrapper>
      <h2>Beratungstellen in der Nähe</h2>
      <Link to={"/search"}>
        <img src={search} alt="" />
        Neue Suche
      </Link>
      {displayMap ? (
        <ResultsMap results={data} setDisplayMap={setDisplayMap} />
      ) : (
        <ResultsList results={data} setDisplayMap={setDisplayMap} />
      )}
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #ffff;

  h2 {
    margin: 0;
    font-size: medium;
    padding-bottom: 5px;
  }

  a {
    align-self: flex-start;
    text-decoration: none;
    font-size: 11px;
    color: #666666;
    img {
      margin-right: 3px;
    }
  }
`;
