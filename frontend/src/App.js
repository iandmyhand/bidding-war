import './App.css';
import {useEffect, useState} from "react";
import Home from "./Home";
import ClientList from "./component/ProductList";
import {Route, BrowserRouter as Router, Switch} from "react-router-dom";
import ProductEdit from "./component/ProductEdit";


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
        </Switch>
      </Router>
  );
}

export default App;
