import { useHistory, useParams } from "react-router-dom";
import styled from "styled-components/macro";
import Button from "../components/Button";
import InfoLabels from "../components/InfoLabels";
import { useMutation, useQuery } from "react-query";
import { loadCounselingCenterById } from "../service/CounselingCenterService";
import LoadingSpinner from "../components/LoadingSpinner";
import { addNewBookmark } from "../service/BookmarkService";
import { useContext } from "react";
import AuthContext from "../context/AuthContext";

export default function DetailsPage() {
  const history = useHistory();
  const { id } = useParams();
  const { token, userData } = useContext(AuthContext);
  const { isLoading, isError, data, error } = useQuery(["details", id], () =>
    loadCounselingCenterById(id)
  );
  const postBookmark = useMutation(() => {
    addNewBookmark(token, id);
  });
  const handleClick = () => history.goBack();
  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  console.log(data.bookmarkedBy);
  userData && console.log(userData);

  return (
    <Details>
      <Button onClick={handleClick}>Zurück zu den Ergebnissen</Button>
      <Wrapper>
        <h3>{data.name}</h3>
        {userData && !data.bookmarkedBy.includes(userData.sub) && (
          <BookmarkButton onClick={postBookmark.mutate}>merken</BookmarkButton>
        )}

        {userData && data.bookmarkedBy.includes(userData.sub) && (
          <BookmarkButton onClick={postBookmark.mutate}>
            entmerken
          </BookmarkButton>
        )}

        <p>{data.address.street} </p>
        <p>
          {data.address.postalCode} {data.address.city}
        </p>
        <p>Telefon: {data.phoneNo}</p>
        <p>Mail: {data.email}</p>
        <a href={data.url} target="_blank" rel="noreferrer">
          Zur Website
        </a>

        <br />

        <InfoLabels details={data} />
      </Wrapper>
    </Details>
  );
}

const Wrapper = styled.div`
  overflow-wrap: anywhere;
  h3 {
    margin: 0;
  }
`;

const Details = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const BookmarkButton = styled.button`
  margin: 10px 0;
  width: 70%;
  padding: 4px 0;
  color: #fff;
  background-color: #1c3648;
  border: none;
  border-radius: 4px;

  &:disabled {
    background-color: #666666;
  }
`;
