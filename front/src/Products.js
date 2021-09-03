import React, {createRef, useEffect, useState} from 'react';
import {createProduct, fetchProducts, createBid} from "./api";

const Products = ({token}) => {
    const [products, setProducts] = useState([])
    const inputName = createRef()
    const inputPrice = createRef()
    const inputProductId = createRef()
    const inputBiddingPrice = createRef()
    const inputUserId = createRef()

    const initProducts = async () => {
        const response = await fetchProducts()
        console.log(response.data)
        setProducts(response.data)
    }

    const registerProduct = async (e) => {
        e.preventDefault();
        console.log("name:" + inputName.current.value)
        console.log("price:" + inputPrice.current.value)

        await createProduct({
            'token': token,
            'name': inputName.current.value,
            'price': inputPrice.current.value
        })

        await initProducts()
    }

    const registerBid = async (e) => {
        e.preventDefault();
        console.log("productId:" + inputProductId.current.value)
        console.log("userId:" + inputUserId.current.value)
        console.log("biddingPrice:" + inputBiddingPrice.current.value)

        await createBid(inputProductId.current.value, {
            'token': token,
            'userId': inputUserId.current.value,
            'biddingPrice': inputBiddingPrice.current.value
        })
    }

    useEffect(() => {
        initProducts().then()
    }, [])

    useEffect(() => {
    }, [products])

    let registerProductComponent;
    if(token) {
        registerProductComponent = <form onSubmit={registerProduct}>
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
                    <td><button type="submit">상품 생성하기</button></td>
                </tr>
                </tbody>
            </table>
        </form>
    } else {
        registerProductComponent = <p>
            상품을 등록하려면 로그인을 해주세요!
        </p>
    }
    const registerBidComponent = <form onSubmit={registerBid}>
        <table>
            <thead>
            <tr><td colSpan={3}>입찰</td></tr>
            </thead>
            <tbody>
            <tr>
                <td>상품번호:</td>
                <td><input type="text" ref={inputProductId}/></td>
            </tr>
            <tr>
                <td>유저ID:</td>
                <td><input type="text" ref={inputUserId}/></td>
            </tr>
            <tr>
                <td>입찰가:</td>
                <td><input type="number" ref={inputBiddingPrice}/></td>
            </tr>
            <tr>
                <td><button type="submit">입찰하기</button></td>
            </tr>
            </tbody>
        </table>
    </form>

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
        {registerProductComponent}
        {registerBidComponent}
    </div>
}

export default Products