import axios from "axios";


const client = axios.create({
    baseURL: `http://localhost:8080`
})

export const fetchProducts = () => client.get('/api/products')

export const fetchProduct = (id) => client.get(`/api/products/${id}`)

export const createProduct = (data) => client.post('/api/products', data)