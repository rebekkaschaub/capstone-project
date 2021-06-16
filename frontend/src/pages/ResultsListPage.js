import axios from "axios";
import { useEffect, useState } from "react";
import CounselingCenterCard from "../components/CounselingCenterCard";
import styled from "styled-components/macro";

export default function ResultsListPage() {
  const [counselingCenters, setCounselingCenters] = useState([]);

  useEffect(() => {
    axios
      .get("api/counseling")
      .then((response) => response.data)
      .then(setCounselingCenters)
      .catch((err) => console.log(err.message));
  }, []);

  return (
    <Wrapper>
      <Header>
        <h1>Beratungsstellen in der Nähe</h1>
      </Header>

      <div>
        {counselingCenters.map((center) => (
          <CounselingCenterCard key={center.url} counselingCenter={center} />
        ))}
      </div>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  background-color: #f1f1e6;
  height: 100vh;
`;

const Header = styled.div`
  background-color: #ffff;
  border-radius: 12px;
  box-shadow: 0px 1px 1px -1px rgba(0, 0, 0, 0.1);
  margin-bottom: 15px;
  display: flex;
  justify-content: center;

  h1 {
    text-align: center;
    font-size: 21px;
  }
`;
