import styled from "styled-components/macro";
import { useQuery } from "react-query";
import { loadCounselingCenterByQuery } from "../service/CounselingCenterService";
import ResultsList from "../components/ResultsList";
import { useState } from "react";
import ResultsMap from "../components/ResultsMap";
import { Link } from "react-router-dom";
import search from "../images/search_icon.png";
import LoadingSpinner from "../components/FilterForm/LoadingSpinner";

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

  return (
    <Wrapper>
      <h2>Beratungstellen in der Nähe</h2>
      <Link to={"/search"}>
        <img src={search} alt="" />
        Suche anpassen
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
    display: flex;
    align-items: center;
    img {
      margin-right: 3px;
    }
  }
`;
