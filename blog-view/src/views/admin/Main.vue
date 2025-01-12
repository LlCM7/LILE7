<template>
    <div>
        管理员首页
    </div>
</template>
<script>
import router from "@/router/index";
import Logo from '@/components/Logo.vue';
export default {
    components: {Logo},
    name: "AdminMain",
    data() {
        return {
            data: {},
            routers: [],
            activeIndex: '',
            flag: false,
            dialogOperaion: false,
            tag: '首页',
            bag: 'rgb(255 255 255)',
            colorLogo: '#333',
            bagMenu: '#f1f1f1',
        };
    },
    created() {
        this.routerLoad();
    },
    methods: {
        // 解析出路由配置
        routerLoad(path) {
            let menus = router.options.routes.filter(route => route.path == path)[0];
            this.routers = menus.children;
        }
    }
};
</script>
<style scoped lang="scss">

</style>