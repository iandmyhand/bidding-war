import React, {createRef, useEffect, useState} from 'react';
import {signUpUser, signInUser} from "../../api";
import styled from "styled-components";

const Login = () => {
    const [products, setProducts] = useState([])
    const inputEmail = createRef()
    const inputName = createRef()
    const inputPassword = createRef()

    const Container = styled.div`
      margin-top: 100px;
      padding: 20px;
      text-align: center;
    `;

    const Button = styled.div`
      font-size: 18px;
      font-weight: 700;
      line-height: 49px;
      display: block;
      width: 10%;
      height: 5%;
      margin-left: 45%;
      cursor: pointer;
      text-align: center;
      color: #fff;
      border-radius: 0;
      background-color: #03c75a;
    `;

    const Input = styled.input`
      position: relative;
      overflow: hidden;
      width: 20%;
      height: 40px;
      margin: 0 0 8px;
      padding: 5px 39px 5px 11px;
      border: solid 1px #dadada;
      background: #fff;
      box-sizing: border-box;
    `;

    const signUp = async () => {
        try {
            console.log('sign-up!')
            console.log(inputEmail.current.value)
            console.log(inputPassword.current.value)

            await signUpUser({
                'email': inputEmail.current.value,
                'pwd': inputPassword.current.value,
            })

            window.alert("회원가입 성공!")
        } catch (error) {
            console.log(error)
            window.alert("회원가입 실패")
        }
    }

    const signIn = async () => {
        try {
            console.log('sign-in!')
            console.log({
                'email': inputEmail.current.value,
                'pwd': inputPassword.current.value,
            })

            await signInUser({
                'email': inputEmail.current.value,
                'pwd': inputPassword.current.value,
            })

            window.alert('로그인 성공!')
        } catch (error) {
            console.log(error)
            window.alert("로그인 실패!")
        }
    }

    return <Container>
        <h1>Bidding-War</h1>
        <div>
            <h3>Email</h3>
            <Input type="text" ref={inputEmail} placeholder="이메일을 입력해주세요"/>
        </div>
        <div>
            <h3>Password</h3>
            <Input type="text" ref={inputPassword} placeholder="비밀번호를 입력해주세요"/>
        </div>
        &nbsp;
        <div>
            <Button onClick={signIn}>로그인</Button>
        </div>
        &nbsp;
        <div>
            <Button onClick={signUp}>회원가입</Button>
        </div>
    </Container>
}

export default Login