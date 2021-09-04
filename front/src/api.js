import axios from "axios";


const client = axios.create({
    baseURL: `http://localhost:8080`
})

export const fetchProducts = () => client.get('/api/products')

export const fetchProduct = (id) => client.get(`/api/products/${id}`)

export const createProduct = (data) => client.post('/api/products', data)

export const createBid = (productId, data) => {
    const promise = client.post('/api/products/' + productId + '/bids', data)
    console.log("create bid succeed!!")
    return promise
}

export const createUser = (data) => {
    const promise = client.post('/api/users', data)
    console.log("create user succeed!!")
    return promise
}

export const authLoginUser = (data) => {
    const promise = client.post('/api/auth/login', data)
    console.log("login succeed!!")
    return promise
}
