<template>

<div class = "container">
            
            <h1 class = "text-center"> Add a new Gateway</h1>
            <div>
              <div class="form">
            
            <div class="form-group">
            	<label>Serial Number</label>
            	<input type="text" v.model.trim="gateway.serialNumber" @input="setSerialNumber($event.target.value)">
            	<div v-if="this.$v.gateway.serialNumber.$dirty">
            		<div class="error" v-if="!$v.gateway.serialNumber.required">SerialNumber is required</div>
          			<div class="error" v-if="!$v.gateway.serialNumber.minLength">SerialNumber must have at least {{$v.gateway.serialNumber.$params.minLength.min}} letters</div>
				</div>
            </div>
            <div class="form-group">
            	<label>Name</label>
            	<input type="text" v-model.trim="gateway.name"  @input="setName($event.target.value)">
            	<div v-if="this.$v.gateway.name.$dirty">
            		<div class="error" v-if="!$v.gateway.name.required">Name is required</div>
          			<div class="error" v-if="!$v.gateway.name.minLength">Name must have at least {{$v.gateway.name.$params.minLength.min}} letters</div>
				</div>
            </div>
            <div class="form-group">
            	<label>Ip Address</label>
            	<input type="text"  required 
            	v.model.trim ="gateway.ipAddress" id="ipAddress"   @input="setIpAddress($event.target.value)">
            	<div v-if="this.$v.gateway.ipAddress.$dirty">
            		<div class="error" v-if="!$v.gateway.ipAddress.required">Ip Address is required</div>
          			<!--<div class="error" v-if="!$v.gateway.ipAddress.valid">Ip Address must an valid IP  </div>-->
          			<div class="error" v-if="!$v.gateway.ipAddress.ip">Ip Address must an valid IP   </div>
				</div>
            </div>
            <div>
            	<input :disabled="$v.gateway.$invalid" @click.prevent="submit" type="submit" name="" value="Submit">
            </div>
            </div>
            </div>
            
   <b-modal id="modal1" title="Warning">
      <p class="my-4">mi texto</p>
    </b-modal>
</div>

</template>
<script>
import Vue from 'vue'
import Vuelidate from 'vuelidate'
Vue.use(Vuelidate)


import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

import BootstrapVue from "bootstrap-vue";
import bootstrapvue from 'bootstrap-vue';
Vue.use(bootstrapvue);
import { validationMixin } from 'vuelidate'
import { required, minLength, maxLength, between, sameAs, integer, ipAddress , helpers} from 'vuelidate/lib/validators'
import GatewayService from '../services/GatewayService';

const ip = helpers.regex("ipAddress", /^([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-4])\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-4])\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-4])\.([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-4])/);
//const ip = helpers.regex("ipAddress", /^([1-9])/);

export default {
	 mixins: [ validationMixin ],
	  props: ['gateway'],
	  
	  
	components: {},

    name: 'GatewayForm',
    
    data(){
       
         return {
        	
        }
        },
    
    
    validations :{
    	gateway:{   	
    		
			name:{
				required: required,
				 minLength: minLength(4),
			}, 
			serialNumber:{
				required: required,
				 minLength: minLength(4),
			}, 
			ipAddress:{
				required: required,
				//valid: ipAddress,
				ip , 			
			}	   	
    	}
    },
      
     methods:{
     	setName (value) {
      		this.gateway.name=value
      		this.$v.gateway.name.$touch()
   		 },
   		 setSerialNumber (value) {
      		this.gateway.serialNumber = value
      		this.$v.gateway.serialNumber.$touch()
   		 },
   		 setIpAddress(value) {
   		      this.gateway.ipAddress = value
   		      this.$v.gateway.ipAddress.$touch()
   		 },
   		 showModal() {
         //this.selectedItem = item;
         this.$root.$emit("bv::show::modal", "modal1");
   		 },
   		 
   		 submit () {
      this.$v.gateway.$touch()
      if (this.$v.$invalid) {
         
        console.log('Error')
      } else {
      	GatewayService.updateGateway(this.gateway).then((response) => {
      			//if (status==200)
                 this.$router.push('/')
                 /*else {
                 	this.showModal(message);
                 }*/
            }).catch(e => {
        error({ statusCode: 404, message: 'Post not found' })
      })
        console.log('Done!')
      }
    },
   		 
   		 
     	
     
     } 
      
}
   
    

</script>