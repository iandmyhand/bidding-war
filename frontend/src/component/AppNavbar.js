import React, {Component} from 'react';
import {Navbar, NavbarBrand} from 'reactstrap';
import {Link} from 'react-router-dom';


class AppNavbar extends Component {
    static defaultProps = {
        isLoggedIn: false
    };

    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        const { isLoggedIn } = this.props;
        return <Navbar color="dark" dark expand="md">
            {
                isLoggedIn ? (
                    <>
                        <NavbarBrand tag={Link} to="/">메인화면</NavbarBrand>
                        <NavbarBrand tag={Link} to="/products">상품</NavbarBrand>
                        <NavbarBrand tag={Link} to="/profile">프로필</NavbarBrand>
                    </>
                ) : (
                    <>
                        <NavbarBrand tag={Link} to="/signup">회원가입</NavbarBrand>
                        <NavbarBrand tag={Link} to="/signin">로그인</NavbarBrand>
                    </>
                )
            }
        </Navbar>;
    }
}

export default AppNavbar;