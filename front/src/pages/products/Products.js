import React, {createRef, useEffect, useState} from 'react';
import {createProduct, createUser, fetchProducts, getToken} from "../../api";

const Products = () => {
    const [signUpMessage, setSignUpMessage] = useState('')
    const [products, setProducts] = useState([])
    const [user, setUser] = useState({
        account: "",
        token: "",
        name: ""
    })

    const signInAccount = createRef()
    const signInPassword = createRef()
    const signUpAccount = createRef()
    const signUpPassword = createRef()
    const signUpName = createRef()
    const productName = createRef()
    const productPrice = createRef()

    const signIn = async () => {
        const response = await getToken({
            'account': signInAccount.current.value,
            'password': signInPassword.current.value
        })

        console.log(response.data)
        setUser(response.data)
    }

    const signUp = async () => {
        try {
            const response = await createUser({
                'account': signUpAccount.current.value,
                'password': signUpPassword.current.value,
                'name': signUpName.current.value
            })

            if (response.status === 201) {
                setSignUpMessage('회원가입에 성공했습니다.')
                return
            }

            setSignUpMessage('회원가입에 실패했습니다.')
        } catch {
            setSignUpMessage('회원가입에 실패했습니다.')
        }

    }

    const initProducts = async () => {
        const response = await fetchProducts()
        console.log(response.data)

        setProducts(response.data)
    }

    const addProduct = async () => {
        await createProduct({
            'name': productName.current.value,
            'price': productPrice.current.value
        }, user.token)

        await initProducts()
    }

    useEffect(() => {
        initProducts().then()
    }, [])

    useEffect(() => {
    }, [signUpMessage, products, user])

    return <div>
        <strong>유저정보</strong><br/>
        계정: {user.account}<br/>
        토큰: {user.token}

        <br/><br/>
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
        <br/>
        {signUpMessage}

        <br/><br/>
        <strong>상품 목록</strong>
        {products.map(product => <div key={product.id}>{product.id} {product.name} {product.price}</div>)}

        <br/><br/>
        <strong>상품 등록</strong><br/>
        채권: <input type="text" ref={productName}/><br/>
        금액: <input type="number" ref={productPrice}/><br/>
        <button onClick={addProduct}>생성</button>
    </div>
}

export default Products