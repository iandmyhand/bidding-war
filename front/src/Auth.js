import React, {createRef} from 'react';
import PropTypes from 'prop-types';
import {authLoginUser} from "./api";
import {createUser} from "./api";

const Login = ({ setUserId, setToken }) => {
    const inputEmail = createRef()
    const inputPassword = createRef()

    const loginUser = async (e) => {
        e.preventDefault();
        console.log("login...");
        console.log("email:" + inputEmail.current.value);
        console.log("pw:" + inputPassword.current.value);

        const resLogin = await authLoginUser({
            'email': inputEmail.current.value,
            'password': inputPassword.current.value
        }).catch((error) => {
            const reason = error.response.data["reason"]
            console.log(reason)
            window.alert(reason)
        });

        console.log(JSON.stringify(resLogin));
        if (resLogin) {
            const userId = resLogin.data.id;
            const token = resLogin.data.sessionKey;
            setUserId(userId);
            setToken(token);
        }
    }

    return <div>
        <form onSubmit={loginUser}>
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
                    <td><button type="submit">로그인</button></td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
}

Login.propTypes = {
    setToken: PropTypes.func.isRequired
}

const LoggedInMessage = ({userId, token}) => {
    return <div>
        {userId}님 환영합니다!<br />
        <small>Token: {token}</small>
    </div>
}

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
        <form>
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
        </form>
    </div>
}

export {Login, LoggedInMessage, RegisterUser};
