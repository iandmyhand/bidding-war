import axios from "axios";


const client = axios.create({
    baseURL: `http://localhost:8080`
})

export const fetchProducts = () => client.get('products')

export const fetchProduct = (id) => client.get(`/products/${id}`)

export const createProduct = (data) => client.post('products', data)

export const signUpUser = (data) => client.post('sign-up', data)

export const signInUser = (data) => client.post('sign-in', data)

export const createBid = (data) => client.post('bids', data)

export const fetchBids = (id) => client.get('bids')