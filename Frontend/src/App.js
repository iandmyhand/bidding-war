import { BrowserRouter, Route } from 'react-router-dom';
import AuctionMain from './pages/post/AuctionMain';
import AuctionItemView from './pages/post/AuctionItemView';
import Login from './pages/auth/Login';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Route exact path='/auctionItem/:id' component={AuctionItemView} />
        <Route exact path='/' component={AuctionMain} />
        <Route exact path='/login' component={Login} />
      </BrowserRouter>
    </div>
  );
}

export default App;
