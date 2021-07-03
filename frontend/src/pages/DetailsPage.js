import { useHistory, useParams } from "react-router-dom";
import styled from "styled-components/macro";
import InfoLabels from "../components/InfoLabels";
import { useQuery } from "react-query";
import { loadCounselingCenterById } from "../service/CounselingCenterService";
import LoadingSpinner from "../components/LoadingSpinner";
import { useContext } from "react";
import AuthContext from "../context/AuthContext";
import BookmarkButton from "../components/BookmarkButton";
import backIcon from "../images/backIcon.png";
import Reviews from "../components/Reviews";
import StyledIconButton from "../components/StyledIconButton";
import star from "../images/star.png";

export default function DetailsPage() {
  const history = useHistory();
  const { id } = useParams();
  const { userData, token } = useContext(AuthContext);
  const { isLoading, isError, data, error } = useQuery(["details", id], () =>
    loadCounselingCenterById(id)
  );

  const handleClick = () => history.goBack();

  const handleReviewButtonClick = () => history.push(`/review/${id}`);

  if (isLoading) {
    return <LoadingSpinner />;
  }

  if (isError) {
    return <span>Error: {error.message}</span>;
  }

  return (
    <Details>
      <Headline onClick={handleClick}>
        <img src={backIcon} alt="Back Icon" />
        <h3>{data.name}</h3>
      </Headline>
      <Wrapper>
        {userData && (
          <Buttons>
            <BookmarkButton
              marked={data.bookmarkedBy.includes(userData.sub)}
              id={id}
              token={token}
            />
            <StyledIconButton onClick={handleReviewButtonClick}>
              <img src={star} alt="yellow star" />
              <p>bewerten</p>
            </StyledIconButton>
          </Buttons>
        )}
        <ContactDetails>
          <p>{data.address.street} </p>
          <p>
            {data.address.postalCode} {data.address.city}
          </p>
          <p>Telefon: {data.phoneNo}</p>
          <p>Mail: {data.email}</p>
          <a href={data.url} target="_blank" rel="noreferrer">
            Zur Website
          </a>
        </ContactDetails>

        <InfoLabels details={data} />
        {userData && (
          <section>
            <h4>Erfahrungsberichte</h4> <Reviews id={id} />
          </section>
        )}
      </Wrapper>
    </Details>
  );
}

const Wrapper = styled.div`
  overflow-wrap: anywhere;
  margin: 0 30px;

  h4 {
    margin-top: 30px;
    margin-bottom: 0;
  }
`;

const Details = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Headline = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;

  img {
    width: 20px;
    height: 20px;
    position: fixed;
    left: 5px;
  }

  h3 {
    margin: 0;
    margin-left: 30px;
  }
`;

const Buttons = styled.section`
  display: flex;
`;

const ContactDetails = styled.section`
  margin: 20px 0;

  p,
  a {
    margin: 5px;
  }
`;
