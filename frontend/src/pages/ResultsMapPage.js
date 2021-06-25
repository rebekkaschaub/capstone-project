import ReactMapGL from "react-map-gl";
import { useState } from "react";
import styled from "styled-components/macro";

export default function ResultsMapPage() {
  const [viewport, setViewport] = useState({
    latitude: 53.550341,
    longitude: 10.000654,
    width: "100vw",
    height: "100vh",
    zoom: 10,
  });

  return (
    <Wrapper>
      <ReactMapGL
        {...viewport}
        mapStyle="mapbox://styles/rebsch/ckq4wct340kdc18pk3hz5wx7d"
        mapboxApiAccessToken="pk.eyJ1IjoicmVic2NoIiwiYSI6ImNrcWIwNWVhZjBkbngyb2sxeGRjenllZmgifQ.wJaORsp6zRuxDZmRWmaABg"
        onViewportChange={(viewport) => {
          setViewport(viewport);
        }}
      >
        marker here
      </ReactMapGL>
    </Wrapper>
  );
}

const Wrapper = styled.div`
  position: absolute;
  top: 50px;
  left: 0;
`;
