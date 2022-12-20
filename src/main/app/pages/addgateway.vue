import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'



<template>
  <div id="app">
   
   <GatewayForm :gateway="gateway"/>
  </div>
</template>

<script>
import GatewayForm from '@/components/GatewayForm'

export default {


  components: { GatewayForm },

  data () {
    return {
      gateway: {
        name: '',
        ipAddress: '',
        serialNumber: '',
      }
    }
  },
}
</script>