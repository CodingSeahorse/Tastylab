import React from 'react';
import {
  Route,
  BrowserRouter as Router,
  Switch } from 'react-router-dom';

import './App.scss';

import Home from './pages/home/home';
import Login from './pages/welcome/login/login';
import SignUp from './pages/welcome/registration/signup';
import PageTurnArrow from './components/nav-elements/page-turn/page-turn-arrow';
import SideBar from './components/nav-elements/sidebar/sidebar';

const App = () => {
  return (
      <React.Fragment>
        <Router>
          <SideBar/>
          <Switch>
            <Route path="/welcome/login">
              <Login/>
              <PageTurnArrow route="/home"/>
            </Route>
            <Route path="/welcome/signup">
              <SignUp/>
              <PageTurnArrow route="/home"/>
            </Route>
          </Switch>
          <Switch>
            <Route path="/home">
              <Home/>
              <PageTurnArrow route="/lizzy"/>
            </Route>
          </Switch>
        </Router>
      </React.Fragment>
  );
}

export default App;
