import './App.css';
import {useEffect, useState} from "react";
import Home from "./Home";
import ClientList from "./component/ProductList";
import {Route, BrowserRouter as Router, Switch} from "react-router-dom";
import ProductEdit from "./component/ProductEdit";
import SignUp from "./component/SignUp";
import SignIn from "./component/SignIn";
import Profile from "./component/Profile";


function App() {
    const [userObj, setUserObj] = useState(null);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/products' exact={true} component={ClientList}/>
          <Route path='/products/:id' component={ProductEdit}/>
          <Route path='/signup' exact={true} component={SignUp}/>
          <Route path='/signin' exact={true} component={SignIn}/>
          <Route path='/profile' exact={true} component={Profile}/>
        </Switch>
      </Router>
  );
}

export default App;
