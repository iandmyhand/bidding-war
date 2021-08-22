import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';

import Products from "./pages/products/Products";
import Login from "./pages/users/Login";

const App = () => {
  return (
    <div className="App">
        <BrowserRouter>
          <Route exact path='/products' component={Products} />
          <Route exact path='/' component={Login} />
        </BrowserRouter>
    </div>
  );
}

export default App;
