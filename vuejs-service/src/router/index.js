import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'

const requireAuth = () => (to, from, next) => {
  const accessToken = localStorage.getItem('access-token');
  if (accessToken) {
    return next()
  }
  next('/login')
}

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    beforeEnter: requireAuth()
  },
  {
    path: '/login',
    name: 'login',
    beforeEnter: (to, from, next) => {
      sessionStorage.setItem('prev_url', from.fullPath || '/')
      next()
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginView.vue')
  },
  {
    path: '/set-token',
    redirect: to => {
      const {query} = to
      const token = query['token']
      if (token) {
        localStorage.setItem('access-token', token)
      }
      const redirectUrl = query['prev_url'] || '/'
      location.replace(redirectUrl)
    }
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
