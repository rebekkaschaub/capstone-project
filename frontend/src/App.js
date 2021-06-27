import ResultsPage from "./pages/ResultsPage";
import { Switch, Route } from "react-router-dom";
import LandingPage from "./pages/LandingPage";
import DetailsPage from "./pages/DetailsPage";
import Layout from "./GlobalLayout/Layout";
import FilterPage from "./pages/FilterPage";
import { QueryClient, QueryClientProvider } from "react-query";

export default function App() {
  const queryClient = new QueryClient();

  return (
    <QueryClientProvider client={queryClient}>
      <Layout>
        <Switch>
          <Route component={LandingPage} path="/" exact />
          <Route component={ResultsPage} path="/counseling" exact />
          <Route component={FilterPage} path="/search" exact />
          <Route component={DetailsPage} path="/counseling/:id/details" exact />
        </Switch>
      </Layout>
    </QueryClientProvider>
  );
}
