import React, {useEffect, useState} from 'react';
import {Button, Space, Table} from 'antd';
import axios from "axios";

const ProductList = () => {

    const [Products, setProducts] = useState([])

    useEffect(() => {
        axios.get('/api/products')
            .then(response => {
                console.log(response.data)
                setProducts(response.data)
            })
    }, [])

    const onDeleteHandler = (event) => {

        // setEmail(event.currentTarget.value)
    }

    const columns = [
        {
            title: '상품명',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: '설명',
            dataIndex: 'description',
            key: 'description',
        },
        {
            title: '모집액',
            dataIndex: 'amount',
            key: 'amount',
        },
        {
            title: 'Action',
            key: 'action',
            render: (text, record) => (
                <Space size="middle">
                    <a href={"/products/" + record.id}>내용 변경</a>
                    <a onClick={onDeleteHandler}>삭제</a>
                </Space>
            ),
        },
    ];

    return (
        <div
            style={{
            display: 'flex', justifyContent: 'center', alignItems: 'center'
            , width: '100%', height: '100vh'
        }}
        >
            <Button type="primary" href="/products/new">
                상품 등록
            </Button>
            <Table dataSource={Products} columns={columns} ></Table>
        </div>
    );
};

export default ProductList;