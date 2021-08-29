import './App.css';
import React, {useEffect, useState} from "react";
import AppRouter from "./component/Router";


function App() {
    const [userObj, setUserObj] = useState(null);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
      <div>
          <AppRouter isLoggedIn={isLoggedIn} userObj={userObj}/>
      </div>
  );
}

export default App;
