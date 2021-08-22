import React from "react";
import {withRouter} from "react-router-dom";
import AppNavbar from "../AppNavbar";
import {Container} from "reactstrap";

function Profile({ user }) {
    const { email, password, token } = user || {};
    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <h1>Profile</h1>
                <dt>Email</dt>
                <dd>{email}</dd>
                <dt>Password</dt>
                <dd>{password}</dd>
                <dt>Token</dt>
                <dd>{token}</dd>
            </Container>
        </div>
    );
}

// export default Profile;
export default withRouter(Profile);