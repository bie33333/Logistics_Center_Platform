<template>
    <div style="line-height: 20px;">
        <h1 style="line-height: 50px;text-align: center;font-size: larger;">个人中心</h1>
          <el-descriptions class="margin-top" title="用户个人信息" :column="3" :size="size" border>
            <template slot="extra">
              <el-button type="primary" size="small">操作</el-button>
            </template>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>
                用户名
              </template>
                {{ user.account }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                <i class="el-icon-user"></i>
                  姓名
                </template>
                {{ user.name }}
              </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>
                手机号
              </template>
              {{ user.phone }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-location-outline"></i>
                性别
              </template>
              <!-- 女 0 男 1 -->
              <slot v-if="user.gender===0">女</slot>
              <slot v-else-if="user.gender===1">男</slot>
              <slot v-else>其他</slot>
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                <i class="el-icon-location-outline"></i>
                  年龄
                </template>
                  {{ user.age }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-office-building"></i>
                联系地址
              </template>
              {{ user.address }}
            </el-descriptions-item>
          </el-descriptions>

          <DateUtils></DateUtils>
    </div>
</template>

<script>
import DateUtils from "@/components/common/DateUtils";
import { dataUser } from "@/js/user.js"

export default {

    components: {DateUtils},
    data() {
        return {
           user: dataUser(),
        }
    },
    mounted: {
    },
}
</script>

<style>
.mgb20 {
    margin-bottom: 20px;
}
</style>