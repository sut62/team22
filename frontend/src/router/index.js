import Vue from 'vue'
import VueRouter from 'vue-router'
import reservation from '../components/reservation.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'reservation',
    component: reservation
  },
 
]

const router = new VueRouter({
  routes
})

export default router
