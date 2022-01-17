import React, {createRef, useEffect, useState} from 'react';
import {createProduct, fetchProducts, createBid} from "../../api";
import styled from "styled-components";
import {Button, Container, Input} from '../../components/Login';
import CommonTable from '../../components/Table/CommonTable';
import CommonTableColumn from '../../components/Table/CommonTableColumn';
import CommonTableRow from '../../components/Table/CommonTableRow';

const Products = () => {
    const [products, setProducts] = useState([])
    const inputName = createRef()
    const inputPrice = createRef()
    const inputDescription = createRef()
    const biddingPrice = createRef()

    const initProducts = async () => {
        const response = await fetchProducts()
        console.log(response.data)

        setProducts(response.data)
    }

    const BidButton = styled.div`
      font-size: 18px;
      font-weight: 700;
      line-height: 49px;
      display: block;
      width: 120px;
      height: 50px;
      margin-left: 45%;
      margin-top: 5%;
      margin-bottom: 5%;
      cursor: pointer;
      text-align: center;
      color: #fff;
      border-radius: 0;
      background-color: #03c75a;
    `;

    const addProduct = async () => {
        try{
            let session = window.sessionStorage.getItem('session')
            console.log('session' + session)

            await createProduct({
                'name': inputName.current.value,
                'price': inputPrice.current.value,
                'description': inputDescription.current.value,
                'user_id': session
            })
            await initProducts()
            window.alert("상품 등록 완료")
        } catch (error) {
            window.alert("상품 등록에 실패했습니다. 다시 로그인해주세요")
            window.location.replace("/")
        }
    }

    const addBid = async (productId) => {
        try{
            let userId = window.sessionStorage.getItem('session')
            console.log(userId)
            await createBid({
                'product_id' : productId,
                'bidding_price' : biddingPrice.current.value,
                'user_id': userId,
            })
            window.alert("입찰완료")
        } catch (error) {
            window.alert("입찰에 실패했습니다.")
            console.log(error)
        }
    }

    const getBids = async =>{
        window.location.replace("/bids")
    }

    useEffect(() => {
        initProducts().then()
    }, [])

    useEffect(() => {
    }, [products])

    return <Container>
        <h1>Bidding-War</h1>
        <h2>상품목록</h2>
          <CommonTable headersName={['등록번호', '제품명', '호가', '제품설명']}>
            {
              products ? products.map((product) => {
                return (
                  <CommonTableRow key={product.id}>
                    <CommonTableColumn>{ product.id }</CommonTableColumn>
                    <CommonTableColumn>{ product.name }</CommonTableColumn>
                    <CommonTableColumn>{ product.price }</CommonTableColumn>
                    <CommonTableColumn>{ product.description }</CommonTableColumn>
                    <BidButton onClick={() => addBid(product.id)}>입찰하기</BidButton>
                    <BidButton onClick={getBids}>입찰 목록 조회</BidButton>
                  </CommonTableRow>
                )
              }) : ''
            }
          </CommonTable>
        <h2>상품 등록하기</h2>
        <div>
            <h3>상품명</h3>
            <Input type="text" ref={inputName} placeholder="상품명을 입력해주세요"/>
        </div>
        <div>
            <h3>상품 가격</h3>
            <Input type="number" ref={inputPrice} placeholder="상품 가격을 입력해주세요"/>
        </div>
        <div>
            <h3>상품 설명</h3>
            <Input type="text" ref={inputDescription} placeholder="상품 설명을 입력해주세요"/>
        </div>
        &nbsp;
        <div>
            <Button onClick={addProduct}>상품 등록</Button>
        </div>
    </Container>
}

export default Products