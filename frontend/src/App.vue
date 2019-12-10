<template>
  <v-app style="
  background-image: url(rest_wall.jpg);
  background-repeat: no-repeat;
  background-size: cover">
    

    <v-content class="font-weight-black ">
      <HelloWorld/>
    </v-content>
  </v-app>
</template>

<script>
import HelloWorld from './components/HelloWorld';

export default {
  name: 'App',

  components: {
    HelloWorld,
  },

  data: () => ({
    //
  }),
};
</script>
