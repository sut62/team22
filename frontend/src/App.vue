<template>
  <v-app style="
  background-image: url(rest_wall.jpg);
  background-repeat: no-repeat;
  background-size: cover">
    

    <v-content class="font-weight-black ">
      <reservation/>
    </v-content>
  </v-app>
</template>

<script>
import reservation from './components/reservation';

export default {
  name: 'App',

  components: {
    reservation,
  },

  data: () => ({
    //
  }),
};
</script>
