import { useContext, useState } from "react";
import ReactMapGL, { Marker, NavigationControl, Popup } from "react-map-gl";
import ClickAwayListener from "react-click-away-listener";
import styled from "styled-components/macro";
import Button from "../../commons/Button";
import marker from "../../images/marker.png";
import { Link } from "react-router-dom";
import ReviewStats from "../Reviews/ReviewStats";
import AuthContext from "../../context/AuthContext";

export default function ResultsMap({ results, setDisplayMap }) {
  const { userData } = useContext(AuthContext);
  const [selectedCounselingCenter, setSelectedCounselingCenter] =
    useState(null);

  const [viewport, setViewport] = useState({
    latitude: 53.550341,
    longitude: 10.000654,
    width: "100vw",
    height: "100vh",
    zoom: 10,
  });

  const handleClick = () => setDisplayMap(false);
  const handleClickAway = () => setSelectedCounselingCenter(null);

  return (
    <Wrapper>
      <ReactMapGL
        {...viewport}
        mapStyle="mapbox://styles/rebsch/ckq4wct340kdc18pk3hz5wx7d"
        mapboxApiAccessToken={process.env.REACT_APP_MAPBOX_ACCESS_TOKEN}
        onViewportChange={(viewport) => {
          setViewport(viewport);
        }}
      >
        <CenteredButton>
          <Button onClick={handleClick}>Auf Liste ansehen</Button>
        </CenteredButton>

        <NavigationControl className={"navigation"} />

        {results.map((result) => (
          <Marker
            key={result.id}
            latitude={result.address.latitude}
            longitude={result.address.longitude}
            offsetTop={-26.5}
            offsetLeft={-15}
          >
            <MarkerButton
              onClick={(e) => {
                e.preventDefault();
                setSelectedCounselingCenter(result);
              }}
            >
              <img src={marker} alt="" />
            </MarkerButton>
          </Marker>
        ))}

        {selectedCounselingCenter ? (
          <StyledPopup
            latitude={selectedCounselingCenter.address.latitude}
            longitude={selectedCounselingCenter.address.longitude}
            onClose={() => setSelectedCounselingCenter(null)}
            dynamicPosition={true}
            closeOnClick={false}
            offsetLeft={-5}
          >
            <ClickAwayListener onClickAway={handleClickAway}>
              <Link to={`/counseling/${selectedCounselingCenter.id}/details`}>
                <h2>{selectedCounselingCenter.name}</h2>
                {userData && <ReviewStats id={selectedCounselingCenter.id} />}
              </Link>
            </ClickAwayListener>
          </StyledPopup>
        ) : null}
      </ReactMapGL>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  position: absolute;
  top: 95px;
  left: 0;

  .navigation {
    width: min-content;
    position: absolute;
    right: 10px;
    top: 10px;
  }
`;

const MarkerButton = styled.div`
  border: none;
  background-color: transparent;
`;

const StyledPopup = styled(Popup)`
  max-width: 65%;
  overflow-wrap: break-word;
  a {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }
`;

const CenteredButton = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 8px;
`;
