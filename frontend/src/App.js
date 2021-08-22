import './App.css';
import {useEffect, useState} from "react";
import Home from "./Home";
import ClientList from "./component/ProductList";
import {Route, BrowserRouter as Router, Switch} from "react-router-dom";
import ProductEdit from "./component/ProductEdit";
import SignUp from "./component/SignUp";


function App() {
  // const [products, setProducts] = useState([]);
  //
  // useEffect(async () => {
  //   const response = await fetch('/api/products');
  //   const body = await response.json()
  //   setProducts(body)
  // }, [])

  return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/products' exact={true} component={ClientList}/>
          <Route path='/products/:id' component={ProductEdit}/>
          <Route path='/signup' exact={true} component={SignUp}/>
        </Switch>
      </Router>
  );
}

export default App;
