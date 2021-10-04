import React, {createRef, useEffect, useState} from 'react';
import {fetchBiddingList, fetchProduct, patchStatus, postBidding} from "../../api";

const ProductDetail = ({match}) => {
    const productId = match.params.id

    const [product, setProduct] = useState({})
    const [biddingList, setBiddingList] = useState([])

    const inputPrice = createRef()
    const selectedStatus = createRef()

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
            alert(e.response.data)
        }
    }

    const updateStatus = async () => {
        try {
            const data = {
                'status': selectedStatus.current.value
            }

            await patchStatus(productId, data, sessionStorage.getItem('token'))
            alert(`낙찰 처리에 성공했습니다!`)
        } catch (e) {
            alert(e.response.data)
        }
    }

    const getWinningBid = () => {
        let current = 0

        for (const bidding of biddingList) {
            if (bidding.price > current) {
                current = bidding.price
            }
        }

        return <div>
            낙찰가: {current}
        </div>
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
        상태: [{product.status}]<br/>
        {product.status === "낙찰" ? getWinningBid() : ''}
        <br/><br/>

        금액:<input type="number" ref={inputPrice}/>
        <button onClick={bid}>입찰</button>
        <br/><br/>

        상태:
        <select name="종결" ref={selectedStatus}>
            <option value="낙찰">낙찰</option>
            <option value="취소">취소</option>
        </select>
        <button onClick={updateStatus}>종료</button>
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