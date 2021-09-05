import React from "react";
import {BrowserRouter as Router, Redirect, Route, Switch} from "react-router-dom";
import Home from "../router/Home";
import ClientList from "../router/ProductList";
import ProductEdit from "../router/ProductEdit";
import SignUp from "../router/SignUp";
import SignIn from "../router/SignIn";
import Profile from "../router/Profile";
import AppNavbar from "./AppNavbar";


const AppRouter = ({isLoggedIn}) => {
    return (
        <Router>
            <Switch>
                {isLoggedIn ? (
                    <>
                        <AppNavbar isLoggedIn={isLoggedIn}/>
                        <Route path='/' exact component={Home}/>
                        <Route path='/products' exact component={ClientList}/>
                        <Route path='/products/:id' component={ProductEdit}/>
                        <Route path='/profile' exact component={Profile}/>
                    </>
                ) : (
                    <>
                        <AppNavbar isLoggedIn={isLoggedIn}/>
                        <Route path='/signup' exact component={SignUp}/>
                        <Route path='/signin' exact component={SignIn}/>
                        <Redirect from="*" to="/signin" />
                    </>
                )}
            </Switch>
        </Router>
    )
}

export default AppRouter;