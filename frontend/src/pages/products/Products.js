import React, {createRef, useEffect, useState} from 'react';
import {createProduct, fetchProducts} from "../../api";

const Products = () => {
    const [products, setProducts] = useState([])
    const inputName = createRef()
    const inputPrice = createRef()
    const inputDescription = createRef()

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

    return <div>
        <h1>Bidding-War</h1>
        {products.map(product => <div key={product.id}>{product.id} {product.name} {product.price} {product.description}}</div>)}

        <h2>상품 등록하기</h2>
        <div>
            <h3>상품명 : </h3>
            <input type="text" ref={inputName}/>
        </div>
        <div>
            <h3>상품 가격 : </h3>
            <input type="number" ref={inputPrice}/>
        </div>
        <div>
            <h3>상품 설명 : </h3>
            <input type="text" ref={inputDescription}/>
        </div>
        <div>
            <button onClick={addProduct}>상품 등록</button>
        </div>
    </div>
}

export default Products