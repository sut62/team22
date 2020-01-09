import Vue from 'vue'
import VueRouter from 'vue-router'
import reservation from '../components/reservation.vue'
import menumanager from '../components/MenuManager.vue'
import employeereg from '../components/EmployeeREG.vue'
import member from '../components/member.vue'
import OrderFood from '../components/OrderFood.vue'
import payment from '../components/Payment.vue'
import login from '../components/Login.vue'
import sec from '../components/Secure.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect:{
      name:"login"
    }
  },
  {
    path: '/reservation',
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
  },
  {
    path: '/payment',
    name: 'payment',
    component: payment}
  },
  {
     path: '/login',
     name: 'login',
     component: login
  },
  {
    path: '/sec',
    name: 'sec',
    component: sec
  }


]

const router = new VueRouter({
  routes
})

export default router
