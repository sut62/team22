import Vue from 'vue'
import VueRouter from 'vue-router'
import reservation from '../components/reservation.vue'
import menumanager from '../components/MenuManager.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'reservation',
    component: reservation
  },
  {
    path: '/menumng',
    name: 'menumanager',
    component: menumanager
  }
 
]

const router = new VueRouter({
  routes
})

export default router
