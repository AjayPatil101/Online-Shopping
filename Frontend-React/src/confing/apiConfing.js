import axios from "axios";

export const API_BASE_URL="http://localhost:8081"; 
const jwt = localStorage.getItem("jwt");

export const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwt}`, // Add JWT token to header
    }
})