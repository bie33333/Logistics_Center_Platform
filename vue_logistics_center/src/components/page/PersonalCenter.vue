<template>
    <div style="line-height: 20px;">
        <h1 style="line-height: 50px;text-align: center;font-size: larger;">个人中心</h1>
          <el-descriptions v-if=" user !== null" class="margin-top" title="用户个人信息" :column="3" :size="size" border>
            <!-- <template slot="extra">
              <el-button type="primary" size="small">操作</el-button>
            </template> -->
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>
                用户名
              </template>
                {{ user.userAccount }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                <i class="el-icon-user"></i>
                  姓名
                </template>
                {{ user.userName }}
              </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>
                手机号
              </template>
              {{ user.userPhone }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-location-outline"></i>
                性别
              </template>
              {{ user.userSex }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                <i class="el-icon-location-outline"></i>
                  年龄
                </template>
                  {{ user.userAge }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-office-building"></i>
                联系地址
              </template>
              {{ user.userAddress }}
            </el-descriptions-item>
          </el-descriptions>

          <el-descriptions v-else class="margin-top" title="管理员个人信息" :column="3" :size="size" border>
            <!-- <template slot="extra">
              <el-button type="primary" size="small">操作</el-button>
            </template> -->
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>
                用户名
              </template>
                {{ admin.account }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                <i class="el-icon-user"></i>
                  姓名
                </template>
                {{ admin.name }}
              </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>
                手机号
              </template>
              {{ admin.phone }}
            </el-descriptions-item>
          </el-descriptions>

          <DateUtils></DateUtils>
    </div>
</template>

<script>
import DateUtils from "@/components/common/DateUtils";

export default {

    components: {DateUtils},
    data() {
        return {
           user: JSON.parse(localStorage.getItem('user')),
           admin: JSON.parse(localStorage.getItem('manager'))
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