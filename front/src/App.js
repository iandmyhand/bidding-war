// import axios from 'axios'
import {useEffect, useState} from "react"
import {BrowserRouter as Router, Route, Link} from "react-router-dom"
import Login from "./components/Login"
import Button from "./components/Button"
import Header from "./components/Header"
import Items from "./components/Items"
import Footer from "./components/Footer"

const App = () => {
    const [user, setUser] = useState(null)
    const [items, setItems] = useState([
        {
            title: '유아용 변기',
            initialPrice: 10000,
            description: '아이가 사용했던 유아용 변기로 물내리는 소리가 납니다.',
            registeredDate: '2021-09-29',
        },
        {
            title: '유아용 점퍼',
            initialPrice: 10000,
            description: '아이가 사용했던 점퍼입니다.',
            registeredDate: '2021-09-29',
        },
    ])

    // useEffect(() => {
    //     axios.get("/item").then(response => setItems(response.data))
    // }, [])

    return (
    <Router>
        <div className="container">
            <Route path='/login' component={Login} />
            <Route exact path='/' render={() => (
                <>
                    {!user ? <Link to='/login'><Button text='로그인'/></Link> : (
                        user.name
                    )}
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
