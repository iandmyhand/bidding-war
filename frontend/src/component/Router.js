import React from "react";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "../router/Home";
import ClientList from "../router/ProductList";
import ProductEdit from "../router/ProductEdit";
import SignUp from "../router/SignUp";
import SignIn from "../router/SignIn";
import Profile from "../router/Profile";


const AppRouter = ({isLoggedIn, userObj}) => {
    return (
        <Router>
            <Switch>
                {isLoggedIn ? (
                    <>
                        <Route path='/' exact={true} component={Home}/>
                        <Route path='/products' exact={true} component={ClientList}/>
                        <Route path='/products/:id' component={ProductEdit}/>
                        <Route path='/profile' exact={true} component={Profile}/>
                    </>
                ) : (
                    <>
                        <Route path='/signup' exact={true} component={SignUp}/>
                        <Route path='/signin' exact={true} component={SignIn}/>
                    </>
                )}
            </Switch>
        </Router>
    )
}

export default AppRouter;