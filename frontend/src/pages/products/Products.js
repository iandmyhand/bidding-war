import React, {createRef, useEffect, useState} from 'react';
import {createProduct, fetchProducts} from "../../api";
import styled from "styled-components";

const Products = () => {
    const [products, setProducts] = useState([])
    const inputName = createRef()
    const inputPrice = createRef()
    const inputDescription = createRef()

    const Container = styled.div`
      margin-top: 100px;
      padding: 20px;
      text-align: center;
    `;

    const Button = styled.div`
      font-size: 18px;
      font-weight: 700;
      line-height: 49px;
      display: block;
      width: 10%;
      height: 5%;
      margin-left: 45%;
      cursor: pointer;
      text-align: center;
      color: #fff;
      border-radius: 0;
      background-color: #03c75a;
    `;

    const Input = styled.input`
      position: relative;
      overflow: hidden;
      width: 20%;
      height: 40px;
      margin: 0 0 8px;
      padding: 5px 39px 5px 11px;
      border: solid 1px #dadada;
      background: #fff;
      box-sizing: border-box;
    `;

    const initProducts = async () => {
        const response = await fetchProducts()
        console.log(response.data)

        setProducts(response.data)
    }

    const addProduct = async () => {
        console.log('input!')
        console.log(inputName.current.value)
        console.log(inputPrice.current.value)

        await createProduct({
            'name': inputName.current.value,
            'price': inputPrice.current.value,
            'description': inputDescription.current.value
        })

        await initProducts()
    }

    useEffect(() => {
        initProducts().then()
    }, [])

    useEffect(() => {
    }, [products])

    return <Container>
        <h1>Bidding-War</h1>
        {products.map(product => <div key={product.id}>{product.id}. {product.name} {product.price} {product.description}&nbsp;</div>)}

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