import { useHistory, useParams } from "react-router-dom";
import styled from "styled-components/macro";
import InfoTags from "../components/InfoTags";
import { useQuery } from "react-query";
import { loadCounselingCenterById } from "../service/CounselingCenterService";
import LoadingSpinner from "../commons/LoadingSpinner";
import { useContext } from "react";
import AuthContext from "../context/AuthContext";
import BookmarkButton from "../commons/BookmarkButton";
import backIcon from "../images/backIcon.png";
import Reviews from "../components/Reviews/Reviews";
import IconButton from "../commons/IconButton";
import { FaStar } from "react-icons/fa";
import { FiAtSign, FiExternalLink, FiPhone, FiHome } from "react-icons/fi";
import { loadReviewsById } from "../service/ReviewService";
import Headline from "../commons/Headline";

export default function DetailsPage() {
  const history = useHistory();
  const { id } = useParams();
  const { userData, token } = useContext(AuthContext);

  const counselingCenter = useQuery(["details", id], () =>
    loadCounselingCenterById(id)
  );

  const reviews = useQuery(["reviews", id], () => loadReviewsById(token, id), {
    enabled: token !== null,
  });

  const handleBackClick = () => history.goBack();

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
      <Headline>
        <img src={backIcon} alt="Back Icon" onClick={handleBackClick} />
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
              <IconButton onClick={handleReviewButtonClick}>
                <FaStar size={30} color={"#FFC107"} />
                <p>bewerten</p>
              </IconButton>
            )}
          </Buttons>
        )}
        <ContactDetails>
          <StyledSection>
            <FiHome />
            <div>
              <p>{counselingCenter.data.address.street} </p>
              <p>
                {counselingCenter.data.address.postalCode + " "}
                {counselingCenter.data.address.city}
              </p>
            </div>
          </StyledSection>
          <StyledSection>
            <FiPhone />
            <p>{counselingCenter.data.phoneNo}</p>
          </StyledSection>
          <StyledSection>
            <FiAtSign />
            <a href={"mailto:" + counselingCenter.data.email}>Mail schreiben</a>
          </StyledSection>

          <StyledSection>
            <FiExternalLink />
            <a
              href={counselingCenter.data.url}
              target="_blank"
              rel="noreferrer"
            >
              Zur Website
            </a>
          </StyledSection>
        </ContactDetails>

        <InfoTags details={counselingCenter.data} />
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

const Buttons = styled.section`
  display: flex;
  justify-content: space-between;
`;

const ContactDetails = styled.section`
  margin-top: 20px;

  p,
  a {
    margin: 5px;
  }
`;

const StyledSection = styled.section`
  display: flex;
  align-items: center;
  margin: 5px;

  a {
    text-decoration: none;
    color: #007185;
  }

  a:visited {
    color: #854e6c;
  }
`;
