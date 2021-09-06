import './App.css';
import React, {useEffect, useState} from "react";
import AppRouter from "./component/Router";
import * as AuthService from "./services/AuthService";


function App() {
    const [init, setInit] = useState(false);
    const [userObj, setUserObj] = useState(null);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        let user = AuthService.getUser();
        console.log(user);
        if(user) {
            setIsLoggedIn(true);
            setUserObj(user);
        } else {
            setIsLoggedIn(false);
        }
        setInit(true);
    }, [])

  return (
      <div>
          {init ? <AppRouter isLoggedIn={isLoggedIn} userObj={userObj}/> : "Initializing..."}
          <footer>&copy; {new Date().getFullYear()} bidding war</footer>
      </div>
  );
}

export default App;
