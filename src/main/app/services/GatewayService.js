
import axios from '../node_modules/axios/index.js';
//import axios from 'axios';
const GATEWAY_API_BASE_URL = 'http://localhost:8080';

const actions = {
	getCartItems({ commit }) {
		axios.get('/api/cart').then((response) => {
			commit('UPDATE_CART_ITEMS', response.data)
		});
	},
	addCartItem({ commit }, cartItem) {
		axios.post('/api/cart', cartItem).then((response) => {
			commit('UPDATE_CART_ITEMS', response.data)
		});
	}
}

class GatewayService {

	getGateways() {
		 return axios.get(GATEWAY_API_BASE_URL + "/gateway/all");
	}

	async deletePeripheral(id, gatewayId) {
		await axios.delete(GATEWAY_API_BASE_URL + "/gateway/deletePeripheral/" + id + "/" + gatewayId);
	}
	async updateGateway(gateway) {
		await fetch(GATEWAY_API_BASE_URL + "/gateway/updateGateway", {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(gateway),
		});
	}
	async updatePeripheral(peripheral) {

		await fetch(GATEWAY_API_BASE_URL + "/peripheral/updatePeripheral", {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(peripheral),
		});
	}
	 async deleteGateway(gatewayId){
		await axios.delete(GATEWAY_API_BASE_URL + "/gateway/deleteGateway/" + gatewayId);
	}
	
	async addPeripheral(peripheral,gateway){
		
		let req = {
    		peripheral: peripheral,
    		gateway: gateway,      
    		}  
    		let lenght = gateway.peripherals.length;
    		gateway.peripherals[length] = peripheral;
		await fetch(GATEWAY_API_BASE_URL + "/gateway/addPeripheral", {
			method: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(gateway),
		
		/*this.submitting = true;
		axios.post(GATEWAY_API_BASE_URL + "/gateway/addPeripheral",{
			vendor: mivendor,
    		gatewayId: migatewayId,
		}).
		then((response) => {
          const data = response.data;
          */
          
		/*let req = {
    		vendor: vendor,
    		gatewayId: gatewayId,      
    		}  
		await fetch(GATEWAY_API_BASE_URL + "/gateway/addPeripheral", {
			method: 'POST',			
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(req),*/
            
			
		});
	}
	
}

export default new GatewayService();