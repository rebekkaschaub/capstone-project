import ResultsListPage from "./pages/ResultsListPage";
import { Switch, Route } from "react-router-dom";
import Header from "./nav/Header";
import LandingPage from "./pages/LandingPage";
import DetailsPage from "./pages/DetailsPage";

export default function App() {
  return (
    <div>
      <Header />

      <Switch>
        <Route component={LandingPage} path="/" exact />
        <Route component={ResultsListPage} path="/counseling" exact />
        <Route component={DetailsPage} path="/counseling/:id/details" exact />
      </Switch>
    </div>
  );
}
