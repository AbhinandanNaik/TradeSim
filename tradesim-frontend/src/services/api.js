import axios from 'axios';

// 1. Create a "Base URL" configuration
// This tells React: "Whenever we ask for data, go to localhost:8080/api"
const API = axios.create({
    baseURL: 'http://localhost:8080/api',
});

// 2. Define the API calls
// These functions match the Controller methods we wrote in Java.

// GET /api/assets -> Get all crypto prices
export const getAssets = () => API.get('/assets');

// POST /api/assets/refresh -> Force CoinGecko update
export const refreshMarketData = () => API.post('/assets/refresh');

// POST /api/trade/buy -> Buy a coin
// We send userId, symbol, and quantity as query parameters
export const buyAsset = (userId, symbol, quantity) => 
    API.post(`/trade/buy?userId=${userId}&symbol=${symbol}&quantity=${quantity}`);

export default API;