import ResultsListPage from "./pages/ResultsListPage";
import { Switch, Route } from "react-router-dom";
import LandingPage from "./pages/LandingPage";
import DetailsPage from "./pages/DetailsPage";
import Layout from "./GlobalLayout/Layout";

export default function App() {
  return (
    <div>
      <Layout>
        <Switch>
          <Route component={LandingPage} path="/" exact />
          <Route component={ResultsListPage} path="/counseling" exact />
          <Route component={DetailsPage} path="/counseling/:id/details" exact />
        </Switch>
      </Layout>
    </div>
  );
}
