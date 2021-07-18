import { BrowserRouter, Route } from 'react-router-dom';
import AuctionMain from './auction/post/AuctionMain';
import AuctionItemView from './auction/post/AuctionItemView';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Route exact path='/auctionItem/:id' component={AuctionItemView} />
        <Route exact path='/' component={AuctionMain} />
      </BrowserRouter>
    </div>
  );
}

export default App;
