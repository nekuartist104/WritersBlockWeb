import { RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        redirect: '/home',
        component: () => import('layouts/MainLayout.vue'),
        children: [
            {
                path: 'home',
                name: 'home',
                component: () => import('pages/HomePage.vue')
            },
            {
                path: 'writing',
                name: 'writing',
                component: () => import('pages/WritingPage.vue')
            },
            {
                path: 'character',
                name: 'character',
                component: () => import('pages/CharacterPage.vue')
            },
            {
                path: 'createCharacter',
                name: 'createCharacter',
                component: () => import('pages/CreateCharacterPage.vue')
            },
            {
                path: 'world',
                name: 'world',
                component: () => import('pages/WorldPage.vue')
            },
            {
                path: 'createWorld',
                name: 'createWorld',
                component: () => import('pages/CreateWorldPage.vue')
            },
            {
                path: 'location',
                name: 'location',
                component: () => import('pages/LocationPage.vue')
            },
            {
                path: 'createLocation',
                name: 'createLocation',
                component: () => import('pages/CreateLocationPage.vue')
            },
            {
                path: 'areaType',
                name: 'areaType',
                component: () => import('pages/AreaTypePage.vue')
            },
            {
                path: 'createAreaType',
                name: 'createAreaType',
                component: () => import('pages/CreateAreaTypePage.vue')
            },
            {
                path: 'area',
                name: 'area',
                component: () => import('pages/AreaPage.vue')
            },
            {
                path: 'createArea',
                name: 'createArea',
                component: () => import('pages/CreateAreaPage.vue')
            },
            {
                path: '/user',
                name: 'user',
                component: () => import('pages/UserPage.vue')
            }
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('pages/LoginPage.vue')
    },
]

export default routes
