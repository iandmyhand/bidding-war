import axios from "axios";


const client = axios.create({
    baseURL: `http://localhost:8080`
})

export const fetchProducts = () => client.get('/bidding-war')

export const fetchProduct = (id) => client.get(`/bidding-war/${id}`)

export const createProduct = (data) => client.post('bidding-war', data)

export const signUpUser = (data) => client.post('bidding-war/users', data)

export const signInUser = (data) => client.post('bidding-war/users/login', data)