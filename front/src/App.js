import React from 'react';
import Products from "./Products";
import RegisterUser from "./RegisterUser";
import Login from "./Login";

const App = () => {
    return (
        <div className="App">
            <Products/>
            <RegisterUser/>
            <Login/>
        </div>
    );
}

export default App;
