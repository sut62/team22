<template>
  <v-toolbar   dark>
    <v-toolbar-title>va te faire foutre premier ministre</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn v-if="islogged" icon @click="loggout">
        <v-icon>mdi-logout</v-icon>
    </v-btn>
  </v-toolbar>
</template>

<script>
import db from 'firebase'
export default {
    name:'bar',
    data: () => ({
    islogged:false,
    currentuser:'',
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

