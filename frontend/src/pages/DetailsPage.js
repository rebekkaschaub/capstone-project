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
import { FaStar } from "react-icons/fa";
import { loadReviewsById } from "../service/ReviewService";

export default function DetailsPage() {
  const history = useHistory();
  const { id } = useParams();
  const { userData, token } = useContext(AuthContext);
  const counselingCenter = useQuery(["details", id], () =>
    loadCounselingCenterById(id)
  );

  const reviews = useQuery(["reviews", id], () => loadReviewsById(token, id));

  const handleClick = () => history.goBack();

  const handleReviewButtonClick = () => history.push(`/review/${id}`);

  if (counselingCenter.isLoading) {
    return <LoadingSpinner />;
  }

  if (counselingCenter.isError) {
    return <span>Error: {counselingCenter.error.message}</span>;
  }

  const reviewsContainLoggedInUser = reviews.data?.filter((review) =>
    review.author.includes(userData.sub)
  );

  return (
    <Details>
      <Headline onClick={handleClick}>
        <img src={backIcon} alt="Back Icon" />
        <h3>{counselingCenter.data.name}</h3>
      </Headline>
      <Wrapper>
        {userData && (
          <Buttons>
            <BookmarkButton
              marked={counselingCenter.data.bookmarkedBy.includes(userData.sub)}
              id={id}
              token={token}
            />
            {reviewsContainLoggedInUser?.length === 0 && (
              <StyledIconButton onClick={handleReviewButtonClick}>
                <FaStar size={30} color={"#FFC107"} />
                <p>bewerten</p>
              </StyledIconButton>
            )}
          </Buttons>
        )}
        <ContactDetails>
          <p>{counselingCenter.data.address.street} </p>
          <p>
            {counselingCenter.data.address.postalCode}
            {counselingCenter.data.address.city}
          </p>
          <p>Telefon: {counselingCenter.data.phoneNo}</p>
          <p>Mail: {counselingCenter.data.email}</p>
          <a href={counselingCenter.data.url} target="_blank" rel="noreferrer">
            Zur Website
          </a>
        </ContactDetails>

        <InfoLabels details={counselingCenter.data} />
        {userData && (
          <section>
            <h4>Erfahrungsberichte</h4> <Reviews id={id} reviews={reviews} />
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
  justify-content: space-between;
`;

const ContactDetails = styled.section`
  margin: 20px 0;

  p,
  a {
    margin: 5px;
  }
`;
