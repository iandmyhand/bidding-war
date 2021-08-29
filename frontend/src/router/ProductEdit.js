import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from "../component/AppNavbar";

class ProductEdit extends Component {

    emptyItem = {
        name: '',
        amount: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            product: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const client = await (await fetch(`/api/products/${this.props.match.params.id}`)).json();
            this.setState({product: client});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let product = {...this.state.product};
        product[name] = value;
        this.setState({product});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {product} = this.state;

        await fetch('/api/products' + (product.id ? '/' + product.id : ''), {
            method: (product.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(product),
        });
        this.props.history.push('/products');
    }

    render() {
        const {product: product} = this.state;
        const title = <h2>{product.id ? 'Edit Product' : 'Add Product'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">상품명</Label>
                        <Input type="text" name="name" id="name" value={product.name || ''}
                               onChange={this.handleChange} autoComplete="name"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="amount">모집액</Label>
                        <Input type="number" name="amount" id="amount" value={product.amount || ''}
                               onChange={this.handleChange} autoComplete="amount"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/products">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }

}
export default withRouter(ProductEdit);
