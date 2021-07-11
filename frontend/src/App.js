import ResultsPage from "./pages/ResultsPage";
import { Switch, Route } from "react-router-dom";
import LandingPage from "./pages/LandingPage";
import DetailsPage from "./pages/DetailsPage";
import Layout from "./GlobalLayout/Layout";
import FilterPage from "./pages/FilterPage";
import { QueryClient, QueryClientProvider } from "react-query";
import LoginPage from "./pages/LoginPage";
import AuthProvider from "./context/AuthProvider";
import BookmarkedPage from "./pages/BookmarkedPage";
import PrivateRoute from "./routing/PrivateRoute";
import CreateReviewForm from "./pages/CreateReviewForm";
import ReviewPage from "./pages/ReviewPage";
import UpdateReviewForm from "./pages/UpdateReviewForm";
import SignUpPage from "./pages/SignUpPage";
import EmergencyPage from "./pages/EmergencyPage";

export default function App() {
  const queryClient = new QueryClient();

  return (
    <QueryClientProvider client={queryClient}>
      <AuthProvider>
        <Layout>
          <Switch>
            <Route component={LandingPage} path="/" exact />
            <Route component={ResultsPage} path="/counseling" exact />
            <Route component={FilterPage} path="/search" />
            <Route component={DetailsPage} path="/counseling/:id/details" />
            <Route component={EmergencyPage} path="/emergency" />
            <Route component={LoginPage} path={"/login"} />
            <Route component={SignUpPage} path={"/signup"} />
            <PrivateRoute component={BookmarkedPage} path={"/me/bookmarked"} />
            <PrivateRoute component={ReviewPage} path={"/me/reviews"} />
            <PrivateRoute
              component={CreateReviewForm}
              path={"/review/:id"}
              exact
            />
            <PrivateRoute
              component={UpdateReviewForm}
              path={"/review/:id/:reviewId"}
            />
          </Switch>
        </Layout>
      </AuthProvider>
    </QueryClientProvider>
  );
}
