import React, {createRef} from 'react';
import {createUser} from "./api";

const RegisterUser = () => {
    const inputEmail = createRef()
    const inputPassword = createRef()

    const registerUser = async () => {
        console.log("register...")
        console.log("email:" + inputEmail.current.value)
        console.log("pw:" + inputPassword.current.value)

        await createUser({
            'email': inputEmail.current.value,
            'password': inputPassword.current.value
        })
    }

    return <div>
        <table>
            <thead>
            <tr><td colSpan={2}>유저등록</td></tr>
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
                <td><button onClick={registerUser}>가입하기</button></td>
            </tr>
            </tbody>
        </table>
    </div>
}

export default RegisterUser