import React, { useState } from 'react';
import Products from "./Products";
import {Login, LoggedInMessage, RegisterUser} from "./Auth";

const App = () => {
    const [userId, setUserId] = useState();
    const [token, setToken] = useState();

    let authComponent;

    if(!token) {
        authComponent = (
            <React.Fragment>
                <Login setUserId={setUserId} setToken={setToken} />
                <RegisterUser />
            </React.Fragment>
        );
    } else {
        authComponent = <LoggedInMessage userId={userId} token={token} />;
    }

    return (
        <div className="App">
            <Products />
            {authComponent}
        </div>
    );
}

export default App;
