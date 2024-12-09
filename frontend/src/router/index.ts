import { route } from 'quasar/wrappers'
import {
    createMemoryHistory,
    createRouter,
    createWebHashHistory,
    createWebHistory
} from 'vue-router'

import routes from './routes'
// import { useUserStore } from 'src/stores/user-store'

export default route(function () {
    const createHistory = process.env.SERVER
        ? createMemoryHistory
        : process.env.VUE_ROUTER_MODE === 'history'
        ? createWebHistory
        : createWebHashHistory

    const router = createRouter({
        scrollBehaviour: () => ({ left: 0, top: 0 }),
        routes,
        history: createHistory(process.env.VUE_ROUTER_BASE)
    })

    router.beforeEach((to, from, next) => {
        // const userStore = useUserStore()
        // debugger
        if (sessionStorage.getItem('loogedInID') === undefined && to.path!=='/login') {
            next('/login')
        }
        else {
            next()
        }


        // if ( /* IsItABackButton && */ from.meta.someLogica) {
        //   next(false) 
        //   return ''
        // }
        // next()
      })

    return router
})
