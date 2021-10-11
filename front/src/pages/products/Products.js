import React, {createRef, useEffect, useState} from 'react';
import {createProduct, createUser, fetchProducts, getToken} from "../../api";
import SignInOrSignUp from "../../components/SignInOrSignUp";
import {Link} from "react-router-dom";

const Products = () => {
    const [products, setProducts] = useState([])
    const [hasToken, setHasToken] = useState(false)

    const signInAccount = createRef()
    const signInPassword = createRef()
    const signUpAccount = createRef()
    const signUpPassword = createRef()
    const signUpName = createRef()
    const productName = createRef()
    const productPrice = createRef()

    const signIn = async () => {
        try {
            const response = await getToken({
                'account': signInAccount.current.value,
                'password': signInPassword.current.value
            })

            sessionStorage.setItem('account', response.data.account)
            sessionStorage.setItem('token', response.data.token)
            sessionStorage.setItem('name', response.data.name)
            setHasToken(true)
        } catch (e) {
            alert(e.response.data)
        }
    }

    const signUp = async () => {
        try {
            const response = await createUser({
                'account': signUpAccount.current.value,
                'password': signUpPassword.current.value,
                'name': signUpName.current.value
            })

            alert('회원가입에 성공했습니다.')
        } catch (e) {
            alert(e.response.data)
        }

    }

    const initProducts = async () => {
        try {
            const response = await fetchProducts()
            setProducts(response.data)
        } catch (e) {
            alert(e.response.data)
        }
    }

    const addProduct = async () => {
        try {
            const response = await createProduct({
                'name': productName.current.value,
                'price': productPrice.current.value
            }, sessionStorage.getItem('token'))

            await initProducts()
            alert('상품 등록에 성공했습니다.')
        } catch (e) {
            alert(e.response.data)
        }
    }

    useEffect(() => {
        initProducts().then()
    }, [])

    useEffect(() => {
    }, [products, hasToken])

    return <div>
        <strong>유저정보</strong><br/>
        계정: {sessionStorage.getItem('account')}<br/>
        토큰: {sessionStorage.getItem('token')}
        <br/><br/>

        <SignInOrSignUp
            signInAccount={signInAccount}
            signInPassword={signInPassword}
            signIn={signIn}
            signUpAccount={signUpAccount}
            signUpPassword={signUpPassword}
            signUpName={signUpName}
            signUp={signUp}
            visible={!!!sessionStorage.getItem('token')}
        />

        <strong>상품 목록</strong>
        <br/>
        ======
        <br/>
        ID 판매자ID 채권명 최소가격
        <br/>
        ======
        {products.map(product =>
            <div key={product.id}>
                <Link
                    to={`/products/${product.id}`}>{product.id}</Link> {product.sellerId} {product.name} {product.minPrice} [{product.status}]
            </div>)}
        <br/><br/>

        <strong>상품 등록</strong><br/>
        채권: <input type="text" ref={productName}/><br/>
        금액: <input type="number" ref={productPrice}/><br/>
        <button onClick={addProduct}>생성</button>
        <br/><br/>
    </div>
}

export default Products