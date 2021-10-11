import React, {useEffect, useState} from 'react';
import {Button, Form, Input, InputNumber} from 'antd';
import axios from "axios";
import {useParams} from "react-router-dom";

const Product = () => {
    const { productId } = useParams();
    const [Product, setProduct] = useState({})

    useEffect(() => {
        if (!isNaN(parseInt(productId))) {
            axios.get('/api/products/' + productId)
                .then(response => {
                    console.log(response.data)
                    setProduct(response.data)
                })
        } else {
            console.log("새로운 상품 생성")
        }
    }, [])

    const onFinish = (values) => {
        console.log('Success:', values);
    };

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    return (
        <div style={{
            display: 'flex', justifyContent: 'center', alignItems: 'center'
            , width: '100%', height: '100vh'
        }}>
            <Form
                name="basic"
                labelCol={{
                    span: 8,
                }}
                wrapperCol={{
                    span: 16,
                }}
                initialValues={Product}
                onFinish={onFinish}
                onFinishFailed={onFinishFailed}
                autoComplete="off"
            >
                <Form.Item
                    label="상품명"
                    name="name"
                    rules={[
                        {
                            required: true,
                            message: 'Please input product name!',
                        },
                    ]}
                >
                    <Input value={Product.name || ''}/>
                </Form.Item>

                <Form.Item
                    label="모집액"
                    name="amount"
                    rules={[
                        {
                            required: true,
                            message: '모집액은 필수입니다.',
                        },
                    ]}
                >
                    <InputNumber min={0} max={100000000} value={Product.amount || ''}/>
                </Form.Item>


                <Form.Item
                    wrapperCol={{
                        offset: 8,
                        span: 16,
                    }}
                >
                    <Button type="primary" htmlType="submit">
                        저장
                    </Button>
                    <Button type="secondary" href="/products">
                        되돌아가기
                    </Button>
                </Form.Item>
            </Form>
        </div>
    );
};

export default Product;