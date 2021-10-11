import axios from 'axios';
import {
    LOGIN_USER,
    REGISTER_USER,
    AUTH_USER
} from './types'


export function signInUser(dataToSubmit) {
    const request = axios.post('/api/users/signin', dataToSubmit)
        .then(response => response.data )

    return {
        type: LOGIN_USER,
        payload: request
    }
}

export function registerUser(dataToSubmit) {
    const request = axios.post('/api/users/signup', dataToSubmit)
        .then(response => response.data )

    return {
        type: REGISTER_USER,
        payload: request
    }
}

export function auth() {
    const request = axios.get('/api/users/auth')
        .then(response => response.data )

    return {
        type: AUTH_USER,
        payload: request
    }
}