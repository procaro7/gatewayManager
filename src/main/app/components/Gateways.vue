<template>
    <div class = "container">
            
            <h1 class = "text-center"> Gateway List</h1>

            <table class = "table table-striped">
                <thead>
                    <tr>
                        <th> Serial Number</th>
                        <th> Name</th>
                        <th> IP Address</th>                       
                    </tr>

                </thead>
                <tbody>
                    <tr v-for="gateway in gateways" v-bind:key="gateway.serialNUmber">
                        <td> {{gateway.serialNUmber }}</td>
                        <td> {{gateway.name }}</td>
                        <td> {{gateway.ipAddress}}</td>    
                        
                    </tr>
                </tbody>
            </table>
        </div>
</template>

<script>

import GatewayService from '../services/GatewayService';

export default {
    name: 'Gateways',
    data(){
        return {
            gateways: []
        }
    },
    methods: {
        getGateways(){
            GatewayService.getGateways().then((response) => {
                this.gateways = response.data;   
            });
        }
    },
    created() {
        this.getGateways();
    }
}

</script>