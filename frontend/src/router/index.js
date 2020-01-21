import Vue from 'vue'
import VueRouter from 'vue-router'
import reservation from '../components/reservation.vue'
import menumanager from '../components/MenuManager.vue'
import employeereg from '../components/EmployeeREG.vue'
import member from '../components/member.vue'
import OrderFood from '../components/OrderFood.vue'
import payment from '../components/Payment.vue'
import login from '../components/login.vue'
import db from 'firebase'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: login,
    meta:{
      requireGuest: true
    }
  },
  {
    path: '/',
    name: 'reservation',
    component: reservation,
    meta:{
      requireAuth: true
    }
  },
  {
    path: '/menumng',
    name: 'menumanager',
    component: menumanager,
    meta:{
      requireAuth: true
    }
  },
  {
    path: '/employeereg',
    name: 'employeereg',
    component: employeereg,
    meta:{
      requireAuth: true
    }
  },
   {
    path: '/member',
    name: 'member',
    component: member,
    meta:{
      requireAuth: true
    }
  },
  {
    path: '/OrderFood',
    name: 'OrderFood',
    component: OrderFood,
    meta:{
      requireAuth: true
    }
  },
  {
    path: '/payment',
    name: 'payment',
    component: payment,
    meta:{
      requireAuth: true
    }
  },
  
  
 
]

const router = new VueRouter({
  routes
})
router.beforeEach((to,from,next) => {
  if(to.matched.some(rec => rec.meta.requireAuth)){
    if(!db.auth().currentUser){
      next({
        path: '/login',
        query:{
          redirect: to.fullPath
        }
      })
    }
    
    else{
       next();
    }
    
  }
  else if(to.matched.some(rec => rec.meta.requireGuest)){
      if(db.auth().currentUser){
        next({
          path:'/login',
          query:{
            redirect: to.fullPath
          }
        })
      }
      else{
        next()
      }
  }
  else{
    next()
  }
})



export default router
