import axios from 'axios'
import { useState } from 'react'
import { Redirect } from 'react-router'

const Login = () => {
    const [name, setName] = useState('')
    const [password, setPassword] = useState('')
    const [loggedIn, setLoggedIn] = useState(false)

    const onSubmit = (e) => {
        e.preventDefault()

        if(!name || !password) {
            alert('아이디와 비밀번호를 입력해 주세요.')
            return
        }

        axios.get('/login', {
            auth: { username: name, password: password }
        }).then(response => {
            console.log(response)
            setLoggedIn(true)
        }).catch(error => {
            console.log(error)
        })
    }

    if (loggedIn) {
        return <Redirect to="/" />
    }

    return (
        <form className='add-form' onSubmit={onSubmit}>
            <div className='form-control'>
                <label>아이디</label>
                <input
                    type='text'
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
            </div>
            <div className='form-control'>
                <label>비밀번호</label>
                <input
                    type='password'
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>

            <input className='btn btn-block' type='submit' value='로그인' />
        </form>
    )
}

export default Login
