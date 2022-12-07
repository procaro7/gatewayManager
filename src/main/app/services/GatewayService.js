
import axios from '../node_modules/axios/index.js';
//import axios from 'axios';
const GATEWAY_API_BASE_URL = 'http://localhost:8080';

class GatewayService{

    getGateways(){		
        return  axios.get(GATEWAY_API_BASE_URL+"/gateway/all");
    }
    
    async deletePeripheral(id, gatewayId){
		await axios.delete(GATEWAY_API_BASE_URL + "/gateway/deletePeripheral/" + id + "/" + gatewayId);
	}
}

export default new GatewayService();