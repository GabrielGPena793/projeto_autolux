import axios from 'axios';

const api = axios.create({
 baseURL: 'https://back-end-booking.herokuapp.com',
 header: 'application/json'
});

export default api;