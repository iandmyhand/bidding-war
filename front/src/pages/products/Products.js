import React, {createRef, useEffect, useState} from 'react';
import {createProduct, fetchProducts} from "../../api";

const Products = () => {
    const [products, setProducts] = useState([])
    const inputName = createRef()
    const inputPrice = createRef()

    const initProducts = async () => {
        const response = await fetchProducts()
        console.log(response.data)

        setProducts(response.data)
    }

    const addProduct = async () => {
        console.log('인풋!')
        console.log(inputName.current.value)
        console.log(inputPrice.current.value)

        await createProduct({
            'name': inputName.current.value,
            'price': inputPrice.current.value
        })

        await initProducts()
    }

    useEffect(() => {
        initProducts().then()
    }, [])

    useEffect(() => {
    }, [products])

    return <div>상품 목록
        {products.map(product => <div key={product.id}>{product.id} {product.name} {product.price}</div>)}

        상품 등록
        <input type="text" ref={inputName}/>
        <input type="number" ref={inputPrice}/>
        <button onClick={addProduct}>생성</button>
    </div>
}

export default Products