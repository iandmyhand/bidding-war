import React, {createRef, useEffect, useState} from 'react';
import {fetchBiddingList, fetchProduct, postBidding} from "../../api";

const ProductDetail = ({match}) => {
    const productId = match.params.id

    const [product, setProduct] = useState({})
    const [biddingList, setBiddingList] = useState([])

    const inputPrice = createRef()

    const getProduct = async () => {
        const response = await fetchProduct(productId)
        setProduct(response.data)
    }

    const getBiddingList = async () => {
        const response = await fetchBiddingList(productId)
        setBiddingList(response.data)
    }

    const bid = async () => {
        try {
            const data = {
                'productId': productId,
                'price': inputPrice.current.value
            }

            await postBidding(data, sessionStorage.getItem('token'))
            await getBiddingList()
        } catch (e) {
            alert(e)
        }
    }

    useEffect(() => {
        getProduct().then()
        getBiddingList().then()
    }, [])

    return <div>
        <strong>상품 상세 정보</strong>
        <br/><br/>

        ID {product.id}<br/>
        판매자 ID: {product.sellerId}<br/>
        상품명: {product.name}<br/>
        최소가격: {product.minPrice}<br/>
        <br/><br/>

        금액: <input type="number" ref={inputPrice}/>
        <button onClick={bid}>입찰</button>
        <br/><br/>

        <strong>입찰 현황</strong>
        <br/><br/>

        {biddingList.map(bidding => {
            return <div key={bidding.id}>
                {bidding.id} {bidding.userId} {bidding.productId} {bidding.price} <br/>
            </div>
        })}

    </div>
}

export default ProductDetail