import React, {createRef} from 'react';
import {authLoginUser} from "./api";

const Login = () => {
    const inputEmail = createRef()
    const inputPassword = createRef()

    const loginUser = async () => {
        console.log("login...")
        console.log("email:" + inputEmail.current.value)
        console.log("pw:" + inputPassword.current.value)

        await authLoginUser({
            'email': inputEmail.current.value,
            'password': inputPassword.current.value
        })
    }

    return <div>
        <table>
            <thead>
            <tr><td colSpan={2}>로그인</td></tr>
            </thead>
            <tbody>
            <tr>
                <td>이메일:</td>
                <td><input type="text" ref={inputEmail}/></td>
            </tr>
            <tr>
                <td>비밀번호:</td>
                <td><input type="password" ref={inputPassword}/></td>
            </tr>
            <tr>
                <td><button onClick={loginUser}>로그인</button></td>
            </tr>
            </tbody>
        </table>
    </div>
}

export default Login