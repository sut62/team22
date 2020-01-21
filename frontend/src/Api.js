import axios from 'axios'

const SERVER_URL = 'http://localhost:9000';

const instance = axios.create({
  baseURL: SERVER_URL,
  timeout: 1000
});

export default {
 
}
