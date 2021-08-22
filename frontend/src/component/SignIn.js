import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from "../AppNavbar";

class SignIn extends Component {

    emptyUser = {
        userId: '',
        password: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            user: this.emptyUser
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let user = {...this.state.user};
        user[name] = value;
        this.setState({user});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {user} = this.state;

        await fetch('/api/signin', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user),
        });
        this.props.history.push('/');
    }

    render() {
        const {user: user} = this.state;
        return <div>
            <AppNavbar/>
            <Container>
                <h2>로그인</h2>
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="userId">ID</Label>
                        <Input type="text" name="userId" id="userId" value={user.userId || ''}
                               onChange={this.handleChange} autoComplete="userId"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="password">패스워드</Label>
                        <Input type="password" name="password" id="password" value={user.password || ''}
                               onChange={this.handleChange} autoComplete="password"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">로그인</Button>{' '}
                        <Button color="secondary" tag={Link} to="/">취소</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }

}
export default withRouter(SignIn);
