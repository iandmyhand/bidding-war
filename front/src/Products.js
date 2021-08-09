import React, {createRef, useEffect, useState} from 'react';
import {createProduct, fetchProducts} from "./api";

const Products = () => {
    const [products, setProducts] = useState([])
    const inputName = createRef()
    const inputPrice = createRef()

    const initProducts = async () => {
        const response = await fetchProducts()
        console.log(response.data)
        setProducts(response.data)
    }

    const registerProduct = async () => {
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
                <tr><td colSpan={2}>등록</td></tr>
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
                    <td><button onClick={registerProduct}>생성</button></td>
                </tr>
                </tbody>
            </table>
    </div>
}

export default Products