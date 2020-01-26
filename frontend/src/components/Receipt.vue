<template>
  <v-card class="mx-auto" max-width="400">
    <v-card-text>

      <v-card-text class="text--primary" >
      
      <p class="subtitle-1 text-center">Receipt restaurant.</p>
      <p class="text-center">va te faire foutre premier ministre.</p>
      <p class="text-center">BB place Nakhon Ratchasima 30000</p>

    </v-card-text>
        <p>--------------------------------------------------------------------------------------------</p>
        <p class="text-center">Payment</p>
        <p>--------------------------------------------------------------------------------------------</p>

        <v-container>
            <div>
                <v-row > 
                    <p>โต๊ะ</p> <v-spacer></v-spacer><p class="text-center">ประเภทสมาชิก</p><v-spacer></v-spacer> <p>ราคา</p>
                </v-row>
                <v-row>
                    <p>{{this.$attrs.table}}</p><v-spacer></v-spacer><p class="text-center">{{this.$attrs.memtype}}</p><v-spacer></v-spacer><p>{{this.$attrs.total}}</p>
                </v-row>
                <br/>
                <p>--------------------------------------------------------------------------------------</p>
                <br/>
                <v-row>
                  <p>จ่าย</p><v-spacer></v-spacer><p class="text-center">ทอน</p><v-spacer></v-spacer>
                </v-row>
                <v-row>
                  <p>{{this.$attrs.money}}</p><v-spacer></v-spacer><p class="text-center">{{this.$attrs.change}}</p><v-spacer></v-spacer>
                </v-row>
            </div>
        </v-container>
         
      
        
      <v-card-text class="text-center">
        Thank you for using the service.
      </v-card-text>

    </v-card-text>
  </v-card>
</template>
<script>
import http from "../plugins/https"
export default {
  name:'receipt',
  data(){
    return{
      order:[]
    }
  },
  created() {
    
  },
  methods: {
    getOrders(){
      http
        .get("/Order")
        .then(response => {
          this.order = response.data;
          
          
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });

    }
  },
  

    
}
</script>