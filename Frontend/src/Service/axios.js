import axios from 'axios'

export const api = axios.create({
  baseURL: "https://back-end-booking.herokuapp.com",
})
