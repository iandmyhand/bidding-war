import React, { useEffect } from "react";
import axios from "axios";
import './App.css';

function App() {

  const baseUrl = "http://localhost:8080"

  useEffect(() =>{
    getAuctions();
  }, [])

  async function getAuctions(){
    await axios
    .get(baseUrl + "/auctionItem")
    .then((response) => {
      console.log(response.data)
    })
    .catch((error) => {
      console.error(error)
    })
  }
  

  return (
    <div className="App">
      hello
    </div>
  );
}

export default App;
