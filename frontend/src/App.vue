<template>
  <v-app style="
    background-image: url(rest_wall.jpg);
   background-repeat: no-repeat;
    background-size: cover">
    <v-row no-gutters>
      <v-col  :cols="3">
        <v-card class="align-end fill-height" :width="295" color="#aaceee">
          <v-navigation-drawer color="#aacee4" >
            <h1>Restuarant</h1>
            <v-btn color="#aaceee" tile :height="125" :width="300" to = "/" >Reservation</v-btn>
            <v-btn color="#aaceee" tile :height="125" :width="300" to = "/menumng" >Menu Management</v-btn>
            <v-btn color="#aaceee" tile :height="125" :width="300" to = "/employeereg" >Employee Register</v-btn>
            <v-btn color="#aaceee" tile :height="125" :width="300" to = "/member" >Member</v-btn>
            <v-btn color="#aaceee" tile :height="125" :width="300" to = "/OrderFood" >Order Food</v-btn>
            <v-btn color="#aaceee" tile :height="125" :width="300" to = "/payment" >Payment</v-btn>
          </v-navigation-drawer>
        </v-card>
      </v-col>
      <v-col :cols="8">
        <v-content>
          <router-view/>
        </v-content>  
      </v-col>
    </v-row>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data: () => ({
    //
  }),
};
</script>
