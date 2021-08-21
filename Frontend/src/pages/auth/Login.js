import React, {createRef, useEffect, useState} from 'react';
import { Container, Button, Input } from '../../component/Login';
import {signIn, signUp} from "../../api/user/user_api";

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
              window.alert("회원가입 완료")
              return
          }

          window.alert('회원가입에 실패했습니다.')
      } catch(error){
        window.alert(error)
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
          document.location.href("/")
          return
        }
        window.alert("아이디와 비밀번호를 확인해주세요.")
      } catch(error){
        window.alert(error)
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