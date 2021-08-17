import React, {createRef, useEffect, useState} from 'react';
import styled from "styled-components";
import axios from "axios";
// import { Redirect } from 'react-router';
import { Link } from 'react-router-dom' //****

const Login = () => {
    const inputName = createRef()
    const inputPassword = createRef()
    const baseUrl = "http://localhost:8080"
    
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

    function signUpUser(){
        let body = {
          email: inputName.current.value,
          password: inputPassword.current.value,
        }
        const signUpUser = async () => {
          await axios
                .post(baseUrl + "/users/signUp", body)
                .then((response) => {
                  console.log(response.data)
                  window.alert("회원가입 완료")
                  window.location.reload();
                })
                .catch((error) =>{
                  window.alert("이미 존재하는 계정입니다.");
                  window.location.reload();
                })
        }
        signUpUser();

      }

    const signUp = async () => {
        try {
          signUpUser({
            'user_name': inputName.current.value,
            'password': inputPassword.current.value,
          })
        } catch (error) {
          window.alert(error)
        }
    }

    function signInUser(){
      let body = {
        email: inputName.current.value,
        password: inputPassword.current.value,
      }
      const signInUser = async () => {
        await axios
              .post(baseUrl + "/users/signIn", body)
              .then((response) => {
                console.log(response.data)
                window.alert("로그인 완료")
                window.location.href = "/";             
              })
              .catch((error) =>{
                window.alert("존재하지 않는 아이디 입니다.");
              })
      }
      signInUser();
    }

  const signIn = async () => {
      try {
        signInUser({
          'user_name': inputName.current.value,
          'password': inputPassword.current.value,
        })
      } catch (error) {
        window.alert(error)
      }
  }

    return <Container>
        <h1>Login</h1>
        <div>
            <h3>ID</h3>
            <Input type="text" ref={inputName} placeholder="아이디를 입력해주세요"/>
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