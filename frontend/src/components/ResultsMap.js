import { useState } from "react";
import ReactMapGL, { Marker, NavigationControl, Popup } from "react-map-gl";
import ClickAwayListener from "react-click-away-listener";
import styled from "styled-components/macro";
import Button from "./Button";
import marker from "../images/marker.png";

export default function ResultsMap({ results, setDisplayMap }) {
  const [selectedCounselingCenter, setSelectedCounselingCenter] =
    useState(null);

  const [viewport, setViewport] = useState({
    latitude: 53.550341,
    longitude: 10.000654,
    width: "100vw",
    height: "100vh",
    zoom: 10,
  });

  const navControlStyle = {
    right: 10,
    top: 10,
  };

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
        <section>
          <Button onClick={handleClick}>Auf Liste ansehen</Button>
        </section>

        <NavigationControl style={navControlStyle} />

        {results.map((result) => (
          <Marker
            key={result.id}
            latitude={result.coordinates[0]}
            longitude={result.coordinates[1]}
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
            latitude={selectedCounselingCenter.coordinates[0]}
            longitude={selectedCounselingCenter.coordinates[1]}
            onClose={() => setSelectedCounselingCenter(null)}
            dynamicPosition={true}
          >
            <ClickAwayListener onClickAway={handleClickAway}>
              <div>
                <h2>{selectedCounselingCenter.name}</h2>
              </div>
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
  //z-index: 5;

  section {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 8px;
  }
`;

const MarkerButton = styled.div`
  border: none;
  background-color: transparent;
`;

const StyledPopup = styled(Popup)`
  max-width: 70%;
  overflow-wrap: break-word;
`;