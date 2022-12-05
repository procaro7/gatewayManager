
import axios from '../node_modules/axios/index.js';
//import axios from 'axios';
const GATEWAY_API_BASE_URL = 'http://localhost:8080/gateway/all';

class GatewayService{

    getGateways(){		
        return  axios.get(GATEWAY_API_BASE_URL);
    }
}

export default new GatewayService();