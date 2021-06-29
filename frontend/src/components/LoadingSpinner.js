import styled from "styled-components/macro";

export default function LoadingSpinner() {
  return <Load />;
}

const Load = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
  

  &::after {
    content: '';
    width: 60px;
    height: 60px;
    border-radius: 50%;
    background-color: #1c3648;
    animation: loader 1.0s infinite ease-in-out;
  }

  @keyframes loader {
    0% {
      scale(0);
    }
    100% {
      transform: scale(1.1);
      opacity: 0
    }
  }
`;
