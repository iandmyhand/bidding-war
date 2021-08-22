import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import { Link } from 'react-router-dom';
import AppNavbar from "../AppNavbar";

class ProductList extends Component {

    constructor(props) {
        super(props);
        this.state = {products: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/api/products')
            .then(response => response.json())
            .then(data => this.setState({products: data}));
    }
    async remove(id) {
        await fetch(`/api/products/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedClients = [...this.state.products].filter(i => i.id !== id);
            this.setState({products: updatedClients});
        });
    }

    render() {
        const {products, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const productList = products.map(product => {
            return <tr key={product.id}>
                <td style={{whiteSpace: 'nowrap'}}>{product.name}</td>
                <td>{product.amount}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/products/" + product.id}>내용 변경</Button>
                        {/*<Button size="sm" color="danger" onClick={() => this.remove(product.id)}>Delete</Button>*/}
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/products/new">상품 추가</Button>
                    </div>
                    <h3>상품 목록</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">상품명</th>
                            <th width="30%">모집액</th>
                            <th width="40%">엑션</th>
                        </tr>
                        </thead>
                        <tbody>
                        {productList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}
export default ProductList;
