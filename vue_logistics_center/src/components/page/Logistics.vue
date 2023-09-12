<template>
    <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
        <el-input v-model="orderId" placeholder="请输入订单编号关键字" suffix-icon="el-icon-search" style="width: 300px;"></el-input>
    
        <el-button type="success" style="margin-left: 10px;">搜索</el-button>
        <el-button type="info" @click="getMethod('resetButton')">重置</el-button>
        <el-button size="medium" type="primary" style="margin-left: 10px;" @click="getMethod('addButton')">增添新物流订单</el-button>
      
        <div>
          <el-table :data="tableData" :header-cell-style="{ background:'orange',color:'black'}" border>
            <el-table-column prop="orderId" label="订单编号" width="120">
            </el-table-column>
            <el-table-column prop="userName" label="用户姓名" width="120">
            </el-table-column>
            <el-table-column prop="goodName" label="寄送物品" width="180">
            </el-table-column>
            <el-table-column prop="number" label="物品数量" width="120">
            </el-table-column>
            <el-table-column prop="addressee" label="收件人" width="120">
            </el-table-column>
            <el-table-column prop="userPhone" label="用户电话" width="120">
            </el-table-column>
            <el-table-column prop="addresseePhone" label="收件电话" width="120">
            </el-table-column>
            <el-table-column prop="address" label="收件地址" width="120">
            </el-table-column>
            <el-table-column prop="carName" label="车辆名称" width="120">
            </el-table-column>
            <el-table-column prop="description" label="物流描述" width="300">
            </el-table-column>
            <el-table-column prop="operate" label="操作" width="200">
              <template slot-scope="scope">
                <el-button type="success" @click="getMethod('updateButton',scope.row)">修改</el-button>
                <el-button type="danger" @click="getMethod('delete',scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      
        
        <!-- 增添表单 -->
        <el-dialog
          title=""
          :visible.sync="dialogVisible"
          width="50%"
          center>
          <el-form ref="form" :rules="rules" :model="form" label-width="100px">
            <el-form-item label="订单编号" prop="orderId">
              <el-input v-model="form.orderId"></el-input>
            </el-form-item>
            <el-form-item label="用户姓名" prop="userName">
                <el-input v-model="form.userName"></el-input>
            </el-form-item>
            <el-form-item label="寄送物品" prop="goodName">
                <el-input v-model="form.goodName"></el-input>
            </el-form-item>
            <el-form-item label="物品数量" prop="number">
                <el-input v-model="form.number"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" prop="userPhone">
                <el-input v-model="form.userPhone"></el-input>
            </el-form-item>
            <el-form-item label="收件人" prop="addressee">
                <el-input v-model="form.addressee"></el-input>
            </el-form-item>
            <el-form-item label="收件电话" prop="addresseePhone">
                <el-input v-model="form.addresseePhone"></el-input>
            </el-form-item>
            <el-form-item label="收件地址" prop="address">
                <el-input v-model="form.address"></el-input>
            </el-form-item>
            <el-form-item label="车辆名称" prop="carName">
                <el-input v-model="form.carName"></el-input>
            </el-form-item>
            <el-form-item label="物流描述" prop="description">
              <el-input v-model="form.description"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer" v-show="addDialogVisible">
            <el-button @click="addDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="getMethod('addAction ')">确定</el-button>
          </span>
          <span slot="footer" class="dialog-footer" v-show="updateDialogVisible">
            <el-button @click="updateDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="updateAction">确定</el-button>
          </span>
        </el-dialog>
      </div>
</template>

<script>
import { logisticRule,logisticForm,logisticGroup } from "@/js/logistics.js";
import { getEasyMethod } from "@/utils/common.js";
export default {
  data() {
    return {
        orderId:'',
        tableData: [],
        dialogVisible: false,
        addDialogVisible: false,
        updateDialogVisible: false,
        form: logisticForm,
        rules: '',
        pageSet: {
          pageNumber: 0,
          pageSize: 30,
          pageTotal: 0,
        }
    }
  },
  created() {
    this.rules = logisticRule();
    this.getMethod('select');
  },
  methods: {
    getMethod(type,row){
      var group = logisticGroup();
      getEasyMethod(this,type,row,group.methodGroup,group.msgGroup);
    }
  },
}
</script>

<style>
.mgb20 {
    margin-bottom: 20px;
}
</style>