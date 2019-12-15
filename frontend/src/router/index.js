import Vue from 'vue'
import VueRouter from 'vue-router'
import reservation from '../components/reservation.vue'
import menumanager from '../components/MenuManager.vue'
import employeereg from '../components/EmployeeREG.vue'
import member from '../components/member.vue'
import OrderFood from '../components/OrderFood.vue'

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
  },
  {
    path: '/employeereg',
    name: 'employeereg',
    component: employeereg
  },
   {
    path: '/member',
    name: 'member',
    component: member
  },
  {
    path: '/OrderFood',
    name: 'OrderFood',
    component: OrderFood
  }
  
 
]

const router = new VueRouter({
  routes
})

export default router
