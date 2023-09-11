<template>
    <el-menu 
        background-color="#324157" text-color="#bfcbd9" active-text-color="#20a0ff"
        class="sidebar-el-menu" :collapse="collapse" unique-opened router>
        <menu-item :collapse="collapse" :menuList="menuList" head="/index"></menu-item>
    </el-menu>
</template>

<script>
import MenuItem from './MenuItem.vue'
import bus from '@/utils/bus';
export default {
    components: { MenuItem },
    data: () => {
        return {
            collapse: false,
            menuList: [
                {index: 'dashboard', title: '系统首页', icon: 'el-icon-s-home'},
                {index: 'personalCenter', title: '个人中心', icon: 'el-icon-user'},
                {index: 'member', title: '用户管理', icon: 'el-icon-user'},
                {index: 'good',  title: '物品管理', icon: 'el-icon-document-copy'},
                {index: 'car', title: '车辆管理', icon: 'el-icon-date'},
                {index: 'order',  title: '订单管理', icon: 'el-icon-document-copy'},
                {index: 'logistics',  title: '物流管理', icon: 'el-icon-document-copy'},

                // {index: 'charts',title: '统计数据', icon: 'el-icon-odometer'},
            ]
        };
    },
    created() {
        bus.$on('collapse',msg => {
            this.collapse = msg;
        })
    }
    
}
</script>

<style scoped>
.sidebar-el-menu{
    position: absolute;
    height: 100%;
    border: 0;
}

.sidebar-el-menu:not(.el-menu--collapse) {
    width: 250px;
}

.el-menu--collapse{
    width: 80px;
}
</style>