import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import 'material-design-icons-iconfont/dist/material-design-icons.css'
<<<<<<< HEAD
import firebase from 'firebase/app';
import nav from '@/components/sidebar.vue'
import appbar from '@/components/appbar.vue'
import 'firebase/firestore'

import './plugins/firebaseInnit'
Vue.config.productionTip = false
Vue.component('navbar',nav)
Vue.component('appbar',appbar)
let app='';
firebase.auth().onAuthStateChanged(() => {
  if(!app){
      app = new Vue({
        router,
        vuetify,
        render: h => h(App)
      }).$mount('#app')
  }
});

=======
import VueSimpleAlert from "vue-simple-alert";
Vue.config.productionTip = false
Vue.use(VueSimpleAlert);
new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
>>>>>>> issue-114
