<template>
  <v-card dark v-if='islogged'  class="align-end fill-height" height="100%" :width="295" style="background-color:transparent;">
          <v-navigation-drawer
            expand-on-hover
                
           >
           <v-list dense  nav>
            <v-list-item  class="py-6"
              v-for="item in page"
              :key="item.title"
              link
              :to="item.go"
              >
              <v-list-item-icon class="py-6">
              <v-icon>{{item.icon}}</v-icon>
            </v-list-item-icon>
              
            <v-list-item-content class="py-6">
              <v-list-item-title  >{{item.title}}</v-list-item-title>
            </v-list-item-content>
             
            </v-list-item>
           </v-list>
           <v-divider></v-divider>
          </v-navigation-drawer>
     </v-card>
</template>

<script>
import db from 'firebase'
export default {
    name:'sidebar',
    data: () => ({
    islogged:false,
    currentuser:'',
    page:[
      {
      title:'Reservation',go:"/",icon:'mdi-table-chair'
      },
      {
      title:'Menu Management',go:"/menumng",icon:'mdi-book-open'
      },
      {
      title:'Employee Register',go:"/employeereg",icon:'mdi-chef-hat'
      },
      {
      title:'Member',go:"/member",icon:'mdi-human'
      },
      {
      title:'Order Food',go:"/OrderFood",icon:'mdi-food'
      },
      {
      title:'Payment',go:"/payment",icon:'mdi-currency-usd'
      },
    ]
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

