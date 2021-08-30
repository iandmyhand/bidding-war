import axios from "axios";

const client = axios.create({
    baseURL: 'http://localhost:8080'
})

export const fetchAllAuctionItems = () => client.get('/api/auctionItem')

export const fetchAuctionItem = (id) => client.get(`/api/auctionItem/${id}`)

export const fetchBiddingsByAuctionItem = (id) => client.get(`/api/bidding/${id}`)

export const createAuctionItem = (data) => client.post('/api/auctionItem', data, {
    "headers": {
        "Authorization": `${sessionStorage.getItem("token")}`
    }
})

export const bidding = (data) => client.post('/api/auctionItem/bidding', data, {
    "headers": {
        "Authorization": `${sessionStorage.getItem("token")}`
    }
})