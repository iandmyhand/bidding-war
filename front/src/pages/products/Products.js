import React, {createRef, useEffect, useState} from 'react';
import {createProduct, createUser, fetchProducts, getToken, postBidding} from "../../api";
import SignInOrSignUp from "../../components/SignInOrSignUp";

const Products = () => {
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
    const bidId = createRef()
    const bidPrice = createRef()

    const signIn = async () => {
        try {
            const response = await getToken({
                'account': signInAccount.current.value,
                'password': signInPassword.current.value
            })

            if (response.status !== 200) {
                alert('로그인에 실패했습니다.')
            }

            setUser(response.data)
            alert('로그인에 성공했습니다.')
        } catch {
            alert('로그인에 실패했습니다.')
        }
    }

    const signUp = async () => {
        try {
            const response = await createUser({
                'account': signUpAccount.current.value,
                'password': signUpPassword.current.value,
                'name': signUpName.current.value
            })

            if (response.status === 201) {
                alert('회원가입에 성공했습니다.')
                return
            }

            alert('회원가입에 실패했습니다.')
        } catch {
            alert('회원가입에 실패했습니다.')
        }

    }

    const initProducts = async () => {
        const response = await fetchProducts()
        console.log(response.data)

        setProducts(response.data)
    }

    const addProduct = async () => {
        try {
            const response = await createProduct({
                'name': productName.current.value,
                'price': productPrice.current.value
            }, user.token)

            if (response.status !== 201) {
                alert('상품 등록에 실패했습니다.')
                return
            }

            await initProducts()
            alert('상품 등록에 성공했습니다.')
        } catch {
            alert('상품 등록에 실패했습니다.')
        }
    }

    const bid = async () => {
        try {
            const response = await postBidding({
                'id': bidId.current.value,
                'price': bidPrice.current.value
            }, user.token)

            if (response.status !== 201) {
                alert('입찰 신청에 실패했습니다.')
                return
            }

            alert('입찰 신청에 성공했습니다.')
        } catch {
            alert('입찰 신청에 실패했습니다.')
        }
    }

    useEffect(() => {
        initProducts().then()
    }, [])

    useEffect(() => {
    }, [products, user])

    return <div>
        <strong>유저정보</strong><br/>
        계정: {user.account}<br/>
        토큰: {user.token}
        <br/><br/>

        <SignInOrSignUp
            signInAccount={signInAccount}
            signInPassword={signInPassword}
            signIn={signIn}
            user={user}
            signUpAccount={signUpAccount}
            signUpPassword={signUpPassword}
            signUpName={signUpName}
            signUp={signUp}
            visible={!!!user.token}
        />

        <strong>상품 목록</strong>
        <br/>
        ======
        <br/>
        ID 채권명 최소가격 현재가격
        <br/>
        ======
        {products.map(product =>
            <div key={product.id}>{product.id} {product.name} {product.minPrice} {product.currentPrice}</div>)}
        <br/><br/>

        <strong>상품 등록</strong><br/>
        채권: <input type="text" ref={productName}/><br/>
        금액: <input type="number" ref={productPrice}/><br/>
        <button onClick={addProduct}>생성</button>
        <br/><br/>

        <strong>입찰</strong>
        <br/>
        채권 ID: <input type="number" ref={bidId}/><br/>
        금액: <input type="number" ref={bidPrice}/><br/>
        <button onClick={bid}>신청</button>
        <br/><br/>
    </div>
}

export default Products