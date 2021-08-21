import axios from "axios";

const client = axios.create({
    baseURL: 'http://localhost:8080'
})

export const fetchProducts = () => client.get('/api/auctionItem')

export const fetchProduct = (id) => client.get(`/api/auctionItem/${id}`)

export const createProduct = (data, token) => client.post('/api/auctionItem', data, {
    "headers": {
        "Authorization": `${token}`
    }
})