import { createRouter, createWebHistory } from 'vue-router';
import PizzePage from './pages/PizzePage.vue';
import PizzeCreate from './pages/PizzeCreate.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'home',
            component: PizzePage
        },
        {
            path: '/create',
            name: 'pizze',
            component: PizzeCreate
        }
    ]
});
export { router };