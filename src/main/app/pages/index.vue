import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'



<template>
  <div id="app">
    <Gateways />
  </div>
</template>

<script>
import Gateways from '../components/Gateways.vue'

export default {
  name: 'App',
  components: {
    Gateways
  }
}
</script>