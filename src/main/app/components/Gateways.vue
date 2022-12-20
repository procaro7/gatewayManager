<template>
    <div class = "container">
            
            <h1 class = "text-center"> Gateway List</h1>
             <NuxtLink to="/addgateway">Add a Gateway</NuxtLink>
             
             
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
     <div class="col"></div>
     
  </div>
  <div  v-for="gateway in gateways" >
  	<div class="row" >
  	<div class="col"> <button @click="showHide(gateway.serialNumber)" v-if='gateway.peripherals.length > 0' :id="gateway.serialNumber"> ↕️ </button></div>
	<div class="col">  
    <input type="text" :disabled='!disabled[gateway.serialNumber]'   v-model="gateway.serialNumber"></div>
	<div class="col"> <input type="text" :disabled='!disabled[gateway.serialNumber]'  v-model="gateway.name"></div> 
	<div class="col"><input type="text" :disabled='!disabled[gateway.serialNumber]'  v-model="gateway.ipAddress"></div>
	<div class="col"><button @click="changeActiveStatus(gateway.serialNumber)" :id="gateway.serialNumber" title="Edit Gateway">📃</button></div>
	<div class="col"><button @click="$router.push({ name: 'addperipheral', params: { gateway: gateway } })">➕️</button></div>
	<div class="col"><button @click="deleteGateway(gateway.serialNumber)" :id="gateway.serialNumber" title="Delete Gateway"> 🗑️ </button> </div>
	</div>
 <div v-for="peripheral in gateway.peripherals" id="hide" v-show='hidden[gateway.serialNumber]' :id="gateway.serialNumber">
                    <div class="row  bg-light">
                    		<div class="col-2"></div>
                        	<div class="col"><input type="text" :disabled='!disabled[peripheral.id]'  v-model="peripheral.vendor"></div>
                        	<div class="col form-check form-switch" >  
                        	
                        	  <input class="form-check-input" type="checkbox" role="switch" :id="peripheral.status" v-model="peripheral.status"
                        	  :disabled='!disabled[peripheral.id]' >
  								                       	
                        	</div>	
                        	<div class="col">{{peripheral.created}}</div> 
                        	<div class="col"> 
                        	<button @click="changeActiveStatus(peripheral.id)" :id="peripheral.id">📃</button>
                        	<button @click="updatePeripheral(peripheral)" :id="peripheral.id"> 💾️ </button>
                        	<!--<button @click="updateGateway(gateway)" :id="peripheral.id"> 💾️ </button>--> 
                        	<button @click="deletePeripheral(peripheral.id,gateway.serialNumber)" :id="peripheral.id"> 🗑️ </button>                        	
                        	 </div>
                        	  
                        </div> 
  </div>
  </div>
</div>

 <b-modal id="modal1" title="Add Peripheral">
      <p class="my-4">Add Peripheral</p>
      
      
    </b-modal>
</div>





           
            
  
      
            
            
</template>

<script>
import Vue from 'vue'
import BootstrapVue from "bootstrap-vue";
import bootstrapvue from 'bootstrap-vue';
Vue.use(bootstrapvue);

import GatewayService from '../services/GatewayService';
  
export default {
	components: {},

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
        deleteGateway(gatewayId){
        	GatewayService.deleteGateway(gatewayId).then((response) => {
                 this.getGateways(); 
            });
        },
        changeActiveStatus(id){
        	this.$set(this.disabled, id, !this.disabled[id]);
        },
        
        updateGateway(gateway){
        	GatewayService.updateGateway(gateway).then((response) => {
                 this.getGateways(); 
            });
        	
        },
        addPeripheral(gatewayId){
        	this.showModal();
        },
        showModal() {
         //this.selectedItem = item;
         this.$root.$emit("bv::show::modal", "modal1");
   		 },
        updatePeripheral(peripheral){
        	GatewayService.updatePeripheral(peripheral).then((response) => {
                 this.getGateways(); 
            });
        }        
        
    },
    created() {
        this.getGateways();      

    }
     
   
    
}



</script>

