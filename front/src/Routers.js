import React from 'react';
import {BrowserRouter, Route} from 'react-router-dom';
import Products from "./pages/products/Products";
import ProductDetail from "./pages/products/ProductDetail";

const Routers = () => {
    return (
        <BrowserRouter>
            <Route exact path='/' component={Products}/>
            <Route exact path='/products/:id' component={ProductDetail}/>
        </BrowserRouter>
    )
}

export default Routers