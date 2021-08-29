import React, {Component} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from "../component/AppNavbar";

class SignUp extends Component {

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

        await fetch('/api/signup', {
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
                <h2>가입</h2>
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
                        <Button color="primary" type="submit">SignUp</Button>{' '}
                        <Button color="secondary" tag={Link} to="/products">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }

}
export default withRouter(SignUp);
