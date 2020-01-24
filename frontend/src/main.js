import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import firebase from 'firebase/app';
import nav from '@/components/sidebar.vue'
import appbar from '@/components/appbar.vue'
import card from '@/components/memberfind.vue'
import 'firebase/firestore'

import './plugins/firebaseInnit'
Vue.config.productionTip = false
Vue.component('navbar',nav)
Vue.component('appbar',appbar)
Vue.component('card',card)

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
