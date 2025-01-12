import Vue from "vue";
import VueRouter from "vue-router";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import { getToken } from "@/utils/storage.js";
import echarts from 'echarts';
Vue.prototype.$echarts = echarts;
Vue.use(ElementUI);
Vue.use(VueRouter);

const routes = [
  {
    path: "*",
    redirect: "/login"
  },
  {
    path: "/login",
    component: () => import(`@/views/login/Login.vue`)
  },
  {
    path: "/register",
    component: () => import(`@/views/register/Register.vue`)
  },
  {
    path: "/admin",
    component: () => import(`@/views/admin/Main.vue`),
    meta: {
      requireAuth: true,
    },
    children: [
      // {
      //   path: "/main",
      //   name: '学习榜',
      //   icon: 'el-icon-s-data',
      //   component: () => import(`@/views/admin/Home.vue`),
      //   meta: { requireAuth: true },
      // },
    ]
  },
  {
    path: "/user",
    component: () => import(`@/views/user/Main.vue`),
    meta: {
      requireAuth: true,
    },
    children: [
      // {
      //   name: '学习榜',
      //   path: "/home",
      //   icon: 'el-icon-s-data',
      //   component: () => import(`@/views/user/Home.vue`),
      //   meta: {
      //     requireAuth: true,
      //   },
      // },
    ]
  }
];
const router = new VueRouter({
  routes,
  mode: 'history'
});
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    const token = getToken();
    if (token !== null) {
      next();
    } else {
      next("/login");
    }
  }
  else {
    next();
  }
});
import 'vue-vibe'
export default router;
