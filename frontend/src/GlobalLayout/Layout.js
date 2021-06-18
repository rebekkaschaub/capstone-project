import styled from "styled-components/macro";
import Header from "../nav/Header";

export default function Layout(props) {
  return (
    <PageLayout>
      <Header />
      <div className="content">{props.children}</div>
    </PageLayout>
  );
}

const PageLayout = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding: 8px;
  display: grid;
  grid-template-rows: min-content 1fr;

  div {
    overflow-y: auto;
  }
`;
