import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify';
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import VueSimpleAlert from "vue-simple-alert";
Vue.config.productionTip = false
Vue.use(VueSimpleAlert);
new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
