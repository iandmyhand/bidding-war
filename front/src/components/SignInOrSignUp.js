import React from 'react';

const SignInOrSignUp = (
    {signInAccount, signInPassword, signIn, signUpAccount, signUpPassword, signUpName, signUp, visible}
) => {
    if (!visible) {
        return <></>
    }

    return <>
        <strong>로그인</strong><br/>
        계정: <input type="text" ref={signInAccount}/><br/>
        비밀번호: <input type="text" ref={signInPassword}/>
        <button onClick={signIn}>로그인</button>
        <br/><br/>

        <strong>회원가입</strong><br/>
        계정: <input type="text" ref={signUpAccount}/><br/>
        비번: <input type="text" ref={signUpPassword}/><br/>
        이름: <input type="text" ref={signUpName}/><br/>
        <button onClick={signUp}>생성</button>
        <br/><br/>
    </>
}

export default SignInOrSignUp