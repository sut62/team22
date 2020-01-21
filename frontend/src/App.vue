<template>
  <v-app style="
    background-image: url(rest_wall.jpg);
   background-repeat: no-repeat;
    background-size: cover">
    <v-row no-gutters>
      <v-col  :cols="3">
        <navbar :key="$route.fullPath"/>
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
