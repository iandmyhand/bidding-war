<<<<<<< HEAD
import axios from 'axios';
import {useEffect, useState} from "react";

export default function App() {
  return (
    <div className="App">
      <h1>경매 물품</h1>
        <ItemList />
    </div>
  );
}

function ItemList() {
    const [items, setItems] = useState([])

    useEffect(() => {
        axios.get("/item").then(response => setItems(response.data))
    }, [])

    return (
        <div>{
            items.map(item => (
                <ul>물품명: { item.title } / 시작가: { item.initialPrice} / 설명: { item.description } / 등록일시: { item.registered }</ul>
            ))
        }
        </div>
    )
}
=======
import React, { useState } from 'react';
import Products from "./Products";
import {Login, LoggedInMessage, RegisterUser} from "./Auth";

const App = () => {
    const [userId, setUserId] = useState();
    const [token, setToken] = useState();

    let authComponent;

    if(token) {
        authComponent = <LoggedInMessage userId={userId} token={token} />;
    } else {
        authComponent = (
            <React.Fragment>
                <Login setUserId={setUserId} setToken={setToken} />
                <RegisterUser />
            </React.Fragment>
        );
    }

    return (
        <div className="App">
            <Products token={token} />
            {authComponent}
        </div>
    );
}

export default App;
>>>>>>> origin/feature/seomgi/6-session
