// import axios from 'axios'
import {useEffect, useState} from "react"
import {BrowserRouter as Router, Route, Link} from "react-router-dom"
import Button from "./components/Button"
import Header from "./components/Header"
import Items from "./components/Items"
import Footer from "./components/Footer"

const App = () => {
    const [loggedIn, setLoggedIn] = useState(true)
    const [items, setItems] = useState([
        {
            title: '유아용 변기',
            initialPrice: 10000,
            description: '아이가 사용했던 유아용 변기로 물내리는 소리가 납니다.',
            registeredDate: '2021-09-29',
        },
        {
            title: '유아용 변기',
            initialPrice: 10000,
            description: '아이가 사용했던 유아용 변기로 물내리는 소리가 납니다.',
            registeredDate: '2021-09-29',
        },
    ])

    // useEffect(() => {
    //     axios.get("/item").then(response => setItems(response.data))
    // }, [])

    return (
    <Router>
        <div className="container">
            {!loggedIn ? <Link to='/login'><Button text='로그인'/></Link> : (
                '마승완님'
            )}
            <Route path='/login' component='Login' />
            <Route exact path='/' render={() => (
                <>
                    <Header title='경매 물품' />
                    {items.length > 0 ? <Items items={items} /> : '없음'}
                </>
                )
            } />
            <Footer />
        </div>
    </Router>
    )
}

export default App
