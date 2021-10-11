import Vue from 'vue'
import Router from 'vue-router'
import Register from '@/components/Register'
import Login from '@/components/Login'
import Index from '@/components/Index'
import Home from '@/components/home/Home'
import Bookshelf from '@/components/bookshelf/Bookshelf'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
      redirect: '/index',
      children: [
        {
          path: '/home',
          name: Home,
          component: Home
        },
        {
          path: '/bookshelf',
          name: Bookshelf,
          component: Bookshelf
        }
      ]
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
