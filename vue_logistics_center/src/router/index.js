import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/login', component: ()=> import('../components/page/Login.vue'),
        },
        {
            path: '/home', component: ()=> import('../components/common/Home.vue'), redirect: '/index/personalCenter'
        },
        // {
        //     path: '/memberView', component: ()=> import('../components/common/MemberView.vue'),
        // },
        {
            path: '/index', component: ()=> import('../components/common/Home.vue'),
            name: 'index',
            redirect: '/index/personalCenter',
            children: [
                { path: 'dashboard', component: ()=> import('../components/page/Dashboard.vue'), meta: {title: '系统首页'}},
                { path: 'member', component: ()=> import('../components/page/Member.vue'), meta: {title: '用户管理'}},
                { path: 'car', component: ()=> import('../components/page/Car.vue'), meta: {title: '车辆列表'}},
                // { path: 'order', component: ()=> import('../components/page/Order.vue'), meta: {title: '订单管理'}},
                { path: 'logistics', component: ()=> import('../components/page/Logistics.vue'), meta: {title: '物流管理'}},
                { path: 'personalCenter', component: ()=> import('../components/page/PersonalCenter.vue'), meta: {title: '个人中心'}},
                // { path: 'charts', component: ()=> import('../components/page/Charts.vue'), meta: {title: '统计数据'}},
                { path: 'good', component: ()=> import('../components/page/Good.vue'),meta: {title: '物品管理'}},
                { path: '*', redirect: '/index/dashboard'}
            ]
        },
        {
            path: '/', redirect: '/login',
        },
        // {
        //     path: '*', component: () => import('../components/page/404.vue')
        // }
    ]
});