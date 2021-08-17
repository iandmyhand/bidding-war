import axios from "axios";


const client = axios.create({
    baseURL: `http://localhost:8080`
})

export const getToken = (data) => client.post('/api/users/sign-in', data)

export const createUser = (data) => client.post('/api/users', data)

export const fetchProducts = () => client.get('/api/products')

export const fetchProduct = (id) => client.get(`/api/products/${id}`)

export const createProduct = (data, token) => client.post('/api/products', data, {
    "headers": {
        "Authorization": `${token}`
    }
})