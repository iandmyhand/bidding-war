import React, {createRef, useEffect, useState} from 'react';
import {createProduct, fetchProducts, createBid, finishBid} from "./api";

const Products = ({token}) => {
    const [products, setProducts] = useState([])
    const inputName = createRef()
    const inputPrice = createRef()
    const inputBiddingEndTime = createRef()
    const inputProductIdRegisterBid = createRef()
    const inputProductIdFinishBid = createRef()
    const inputBiddingPrice = createRef()

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
            'price': inputPrice.current.value,
            'biddingEndTime': inputBiddingEndTime.current.value
        }).catch((error) => {
            const reason = error.response.data["reason"]
            console.log(reason)
            window.alert(reason)
        })

        await initProducts()
    }

    const registerBid = async (e) => {
        e.preventDefault();
        console.log("productId:" + inputProductIdRegisterBid.current.value)
        console.log("biddingPrice:" + inputBiddingPrice.current.value)

        await createBid(inputProductIdRegisterBid.current.value, {
            'token': token,
            'biddingPrice': inputBiddingPrice.current.value
        }).catch((error) => {
            const reason = error.response.data["reason"]
            console.log(reason)
            window.alert(reason)
        })

        await initProducts()
    }

    const finishBidding = async (e) => {
        e.preventDefault();
        console.log("productId:" + inputProductIdFinishBid.current.value)

        await finishBid(inputProductIdFinishBid.current.value, {
            'token': token
        }).catch((error) => {
            const reason = error.response.data["reason"]
            console.log(reason)
            window.alert(reason)
        })

        await initProducts()
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
                    <td>입찰종료시각:</td>
                    <td><input type="datetime-local" ref={inputBiddingEndTime}/></td>
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
                <td><input type="number" ref={inputProductIdRegisterBid}/></td>
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

    const finishBidComponent = <form onSubmit={finishBidding}>
        <table>
            <thead>
            <tr><td colSpan={3}>입찰종료</td></tr>
            </thead>
            <tbody>
            <tr>
                <td>상품번호:</td>
                <td><input type="text" ref={inputProductIdFinishBid}/></td>
            </tr>
            <tr>
                <td><button type="submit">입찰종료하기</button></td>
            </tr>
            </tbody>
        </table>
    </form>

    return <div>
        <table>
            <thead>
            <tr>
                <td colSpan="7">상품목록</td>
            </tr>
            </thead>
            <tbody>
            {products.map(product =>
                <tr key={product.id}>
                    <td>상품번호: {product.id}</td>
                    <td>등록자: {product.ownerUserId}</td>
                    <td>상품명: {product.name}</td>
                    <td>상품가: {product.price}</td>
                    <td>최소입찰가: {product.minimumBiddingPrice}</td>
                    <td>입찰종료시각: {product.biddingEndTime}</td>
                    <td>낙찰여부: {product.winningBidId}</td>
                    <td>
                        <table>
                            <thead>
                            <tr>
                                <td colSpan={3}>입찰목록</td>
                            </tr>
                            </thead>
                            <tbody>
                    {product.bids.map(bid =>
                        <tr key={bid.id}>
                            <td>입찰번호: {bid.id}</td>
                            <td>입찰자 유저ID: {bid.bidder.id}</td>
                            <td>입찰가: {bid.biddingPrice}</td>
                        </tr>
                    )}
                            </tbody>
                        </table>
                    </td>
                </tr>
            )}
            </tbody>
        </table>
        {registerProductComponent}
        {registerBidComponent}
        {finishBidComponent}
    </div>
}

export default Products