<template>
  <v-app style="
  background-image: url(rest_wall.jpg);
  background-repeat: no-repeat;
  background-size: cover">
    

    <v-content class="font-weight-black ">
      <router-view/>
    </v-content>
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
