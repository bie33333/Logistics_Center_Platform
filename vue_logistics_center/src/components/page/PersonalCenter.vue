<template>
    <div style="line-height: 20px;">
        <h1 style="line-height: 50px;text-align: center;font-size: larger;">个人中心</h1>
          <el-descriptions class="margin-top" title="用户个人信息" :column="3" :size="size" border>
            <!-- <template slot="extra">
              <el-button type="primary" size="small">操作</el-button>
            </template> -->
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>
                用户名
              </template>
                Test
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                <i class="el-icon-user"></i>
                  姓名
                </template>
                Test
              </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>
                手机号
              </template>
              18100000000
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-location-outline"></i>
                性别
              </template>
                男
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                <i class="el-icon-location-outline"></i>
                  年龄
                </template>
                  20
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-office-building"></i>
                联系地址
              </template>
              江苏省苏州市吴中区吴中大道 1188 号
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
           D
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