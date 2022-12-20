<template>
<div class = "container ">
<h3> Gateway --> {{this.gatewayId}}</h3>
	<div class="form-group " >
		<div class="row d-flex flex-wrap align-content-stretch">
			<div class="col d-flex  w-25"><label>Vendor</label></div>
			<div class="col" sixe>
				<input type="text" required v.model.trim="peripheral.vendor" @input="setVendor($event.target.value)">
			</div>
		</div>
		<div class="row">
		<div class="col">Status</div>
		<div class="col form-check form-switch" >  
                        	
                        	  <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault" :checked="peripheral.status"
                        	   >
  								                       	
                        	</div>	
		</div>
		<div class = "row">
		<div class="col">
           	<input :disabled="$v.peripheral.$invalid" @click.prevent="submit" type="submit" name="" value="Submit">
        </div>
        </div>
	</div>
</div>

</template>

<script>
import Vue from 'vue'
import Vuelidate from 'vuelidate'
Vue.use(Vuelidate)
import { validationMixin } from 'vuelidate'
import { required, minLength, maxLength, between, sameAs, integer, ipAddress , helpers} from 'vuelidate/lib/validators'
import GatewayService from '../services/GatewayService';
	export default{
		name: 'Peripheral',
		props: ['peripheral'],
		data(){
			return { gatewayId: this.$route.params.gatewayId}
		},
		
		validations :{
    	peripheral:{   	
    		
			vendor:{
				required: required,
				 minLength: minLength(4),
			}, 
				   	
    	}
    },
	
	     methods:{
     		setVendor (value){
      			this.peripheral.vendor=value
      			this.$v.peripheral.vendor.$touch()
   		 	},
   		 	submit(){
   		 	this.peripheral.gatewayId = this.gatewayId;
   		 	GatewayService.addPeripheral(this.peripheral).then((response) => {
                 this.$router.push('/')
            }).catch(e => {
        error({ statusCode: 404, message: 'Post not found' })
      })
        console.log('Done!')
   		 	},
   		 },
   		 
   		 }
</script>