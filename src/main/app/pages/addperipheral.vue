import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'



<template>
  <div id="app">
   
   <PeripheralForm :peripheral="peripheral"/>
  </div>
</template>

<script>
import PeripheralForm from '@/components/PeripheralForm'

export default {


  components: { PeripheralForm },

  data () {
    return {
      gatewayId: String,
       peripheral: {
        vendor: '',
        status: '',
      }
      
    }
  },
}
</script>