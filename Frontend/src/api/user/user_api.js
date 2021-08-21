import axios from "axios";
import base_url from '../../constants/api_constants'

const client = axios.create({
    baseURL: 'http://localhost:8080'
})

export const signUp = (data) => client.post('/api/users', data)

export const signIn = (data) => client.post('/api/users/signIn', data)
