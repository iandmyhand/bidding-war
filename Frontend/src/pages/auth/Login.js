import React, {createRef, useEffect, useState} from 'react';
import { Container, Button, Input } from '../../component/Login';
import {signIn, signUp} from "../../api/user/user_api";
import { setCookie } from '../../utils/cookie/cookie';

const Login = () => {
    const inputEmail = createRef()
    const inputPassword = createRef()

    const signUpUser = async () => {
      try {
          const response = await signUp({
              'email': inputEmail.current.value,
              'password': inputPassword.current.value,
          })
          console.log(response)

          if (response.status === 201) {
              window.alert("회원가입을 완료했습니다. 로그인 해주세요!")
              return
          }

      } catch(error){
          if (error.response.status === 409){
              window.alert('이미 존재하는 계정입니다.')
          }
          else{
              window.alert('회원가입에 실패했습니다.')
          }
      }

  }
  const signInUser = async () => {
      try{
          const response = await signIn({
            'email': inputEmail.current.value,
            'password': inputPassword.current.value
        })

        if (response.status === 200) {
          window.alert("로그인을 완료했습니다.")
          sessionStorage.setItem("token",response.data.token)
          sessionStorage.setItem("user_id",response.data.userId)
          window.location.replace("/")
          return
        }      
      } catch(error){
        window.alert("아이디와 비밀번호를 확인해주세요. 계정이 없다면 회원가입을 해주세요.")
      }
    
}

    return <Container>
        <h1>Login</h1>
        <div>
            <h3>ID</h3>
            <Input type="text" ref={inputEmail} placeholder="아이디를 입력해주세요"/>
        </div>
        <div>
            <h3>Password</h3>
            <Input type="password" ref={inputPassword} placeholder="비밀번호를 입력해주세요"/>
        </div>
        &nbsp;
        <div>
            <Button onClick={signInUser}>로그인</Button>
        </div>
        &nbsp;
        <div>
            <Button onClick={signUpUser}>회원가입</Button>
        </div>
    </Container>
}

export default Login