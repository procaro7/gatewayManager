<template>
    <div class = "container">
            
            <h1 class = "text-center"> Gateway List</h1>
            
<div class="container text-center">
  <div class="row">
  <div class="col"></div>
    <div class="col">
      Serial Number
    </div>
    <div class="col">
      Name
    </div>
    <div class="col">
      IP Address
    </div>
    <div class="col"></div>
  </div>
  <div  v-for="gateway in gateways" >
  	<div class="row" >
  	<div class="col"> <button @click="showHide(gateway.serialNumber)" v-if='gateway.peripherals.length > 0' :id="gateway.serialNumber"> 📁️ </button></div>
	<div class="col">  
    <input type="text" :disabled='!disabled[gateway.serialNumber]'  :value="gateway.serialNumber"></div>
	<div class="col"> <input type="text" :disabled='!disabled[gateway.serialNumber]'  :value="gateway.name"></div> 
	<div class="col"><input type="text" :disabled='!disabled[gateway.serialNumber]'  :value="gateway.ipAddress"></div>
	<div class="col"><button @click="changeActiveStatus(gateway.serialNumber)" :id="gateway.serialNumber">📃</button></div>
	</div>
 <div v-for="peripheral in gateway.peripherals" id="hide" v-show='hidden[gateway.serialNumber]' :id="gateway.serialNumber">
                    <div class="row  bg-light">
                    		<div class="col-2"></div>
                        	<div class="col"><input type="text" :disabled='!disabled[peripheral.id]'  :value="peripheral.vendor"></div>
                        	<div class="col form-check form-switch" >  
                        	
                        	  <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault" :checked="peripheral.status"
                        	  :disabled='!disabled[peripheral.id]' >
  								                       	
                        	</div>	
                        	<div class="col">{{peripheral.created}}</div> 
                        	<div class="col"> 
                        	<button @click="changeActiveStatus(peripheral.id)" :id="peripheral.id">📃</button>
                        	<button @click="deletePeripheral(peripheral.id,gateway.serialNumber)" :id="peripheral.id"> 💾️ </button>
                        	<button @click="deletePeripheral(peripheral.id,gateway.serialNumber)" :id="peripheral.id"> 🗑️ </button>                        	
                        	 </div>
                        	  
                        </div> 
  </div>
  </div>
</div>
           
            
  
            </div>
</template>

<script>

import GatewayService from '../services/GatewayService';

  

export default {

    name: 'Gateways',
       
    data(){
        return {
        toggle: false,
        	hidden: {},
        	disabled: {},
            gateways: []
        }
    },
    methods: {
        getGateways(){
            GatewayService.getGateways().then((response) => {
                this.gateways = response.data;   
            });
        },
        showHide(id) {        	
            this.$set(this.hidden, id, !this.hidden[id]);           
        }  ,
        deletePeripheral(id, gatewayId){
        	GatewayService.deletePeripheral(id, gatewayId).then((response) => {
                 this.getGateways(); 
            });
        },
        changeActiveStatus(id){
        	this.$set(this.disabled, id, !this.disabled[id]);
        },
    },
    created() {
        this.getGateways();      

    }
     
   
    
}



</script>

