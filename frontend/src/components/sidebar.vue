<template>
  <v-card  v-if='islogged'  class="align-end fill-height" :width="295" color="#aaceee">
          <v-navigation-drawer color="#aacee4" >
            <h1>va te faire foutre premier ministre</h1>
            <v-btn v-if='islogged' color="#aaceee" tile :height="125" :width="300" to = "/" >Reservation</v-btn>
            <v-btn v-if='islogged' color="#aaceee" tile :height="125" :width="300" to = "/menumng" >Menu Management</v-btn>
            <v-btn v-if='islogged' color="#aaceee" tile :height="125" :width="300" to = "/employeereg" >Employee Register</v-btn>
            <v-btn v-if='islogged' color="#aaceee" tile :height="125" :width="300" to = "/member" >Member</v-btn>
            <v-btn v-if='islogged' color="#aaceee" tile :height="125" :width="300" to = "/OrderFood" >Order Food</v-btn>
            <v-btn v-if='islogged' color="#aaceee" tile :height="125" :width="300" to = "/payment" >Payment</v-btn>
            <v-btn v-if="islogged" color="#afffff" @click="loggout">Log out</v-btn>
          </v-navigation-drawer>
     </v-card>
</template>

<script>
import db from 'firebase'
export default {
    name:'sidebar',
    data: () => ({
    islogged:false,
    currentuser:''
  }),
  created(){
    if(db.auth().currentUser){
      this.islogged = true
      this.currentuser = db.auth().currentUser.email
    }
  },
  methods:{
    loggout(){
      db.auth().signOut().then(
        () =>{
          this.$router.push('/login')
        }

      )
    }
  }
}
</script>

