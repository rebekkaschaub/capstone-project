import ResultsListPage from "./pages/ResultsListPage";
import { Switch, Route } from "react-router-dom";

import DetailsPage from "./pages/DetailsPage";
import Header from "./components/Header";

export default function App() {
  return (
    <div>
      <Header />

      <Switch>
        <Route path="/counseling" exact>
          <ResultsListPage />
        </Route>
      </Switch>
    </div>
  );
}
