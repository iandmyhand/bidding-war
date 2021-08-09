import React, {createRef, useEffect, useState} from 'react';
import {createProduct, fetchProducts, createUser} from "./api";

const Products = () => {
    const [products, setProducts] = useState([])
    const inputName = createRef()
    const inputPrice = createRef()
    const inputUserId = createRef()
    const inputPassword = createRef()

    const initProducts = async () => {
        const response = await fetchProducts()
        console.log(response.data)
        setProducts(response.data)
    }

    const registerProduct = async () => {
        console.log("name:" + inputName.current.value)
        console.log("price:" + inputPrice.current.value)

        await createProduct({
            'name': inputName.current.value,
            'price': inputPrice.current.value
        })

        await initProducts()
    }

    const registerUser = async () => {
        console.log("id:" + inputUserId.current.value)
        console.log("pw:" + inputPassword.current.value)

        await createUser({
            'userId': inputUserId.current.value,
            'password': inputPassword.current.value
        })
    }

    useEffect(() => {
        initProducts().then()
    }, [])

    useEffect(() => {
    }, [products])

    return <div>
        <table>
            <thead>
            <tr>
                <td colSpan="3">목록</td>
            </tr>
            </thead>
            <tbody>
            {products.map(product =>
                <tr key={product.id}>
                    <td>{product.id}</td>
                    <td>{product.name}</td>
                    <td>{product.price}</td>
                </tr>
            )}
            </tbody>
        </table>
        <table>
            <thead>
            <tr><td colSpan={2}>상품등록</td></tr>
            </thead>
            <tbody>
            <tr>
                <td>상품이름:</td>
                <td><input type="text" ref={inputName}/></td>
            </tr>
            <tr>
                <td>상품가격:</td>
                <td><input type="number" ref={inputPrice}/></td>
            </tr>
            <tr>
                <td><button onClick={registerProduct}>상품 생성하기</button></td>
            </tr>
            </tbody>
        </table>
        <table>
            <thead>
            <tr><td colSpan={2}>유저등록</td></tr>
            </thead>
            <tbody>
            <tr>
                <td>유저ID:</td>
                <td><input type="text" ref={inputUserId}/></td>
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

export default Products