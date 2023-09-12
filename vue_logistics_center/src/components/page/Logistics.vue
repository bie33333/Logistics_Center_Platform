<template>
    <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
        <el-input v-model="id" placeholder="请输入订单编号关键字" suffix-icon="el-icon-search" style="width: 300px;"></el-input>

        <el-select v-model="orderStatus" filterable placeholder="请选择订单状态" style="margin-left: 10px;">
          <el-option
                v-for="item in status"
                :key="item.value"
                :label="item.label"
                :value="item.value">
          </el-option>
        </el-select>
    
        <el-button type="success" style="margin-left: 10px;">搜索</el-button>
<<<<<<< HEAD
        <el-button type="info" @click="getMethod('resetButton')">重置</el-button>
=======
        <el-button type="info" @click="resetParam">重置</el-button>
>>>>>>> origin/master
        <el-button size="medium" type="primary" style="margin-left: 10px;" @click="getMethod('addButton')">增添新物流订单</el-button>
      
        <div>
          <el-table :data="tableData" :header-cell-style="{ background:'orange',color:'black'}" border>
            <el-table-column prop="id" label="订单编号" width="120">
            </el-table-column>
            <el-table-column prop="userAccount" label="用户账号" width="120">
            </el-table-column>
            <el-table-column prop="userName" label="用户姓名" width="120">
            </el-table-column>
            <el-table-column prop="userPhone" label="用户电话" width="120">
            </el-table-column>
            <el-table-column prop="goodName" label="寄送物品" width="180">
            </el-table-column>
            <el-table-column prop="goodId" label="物品编号" width="120">
            </el-table-column>
            <el-table-column prop="goodNumber" label="物品数量" width="120">
            </el-table-column>
            <el-table-column prop="addressee" label="收件人" width="120">
            </el-table-column>
            <el-table-column prop="addressPhone" label="收件电话" width="120">
            </el-table-column>
            <el-table-column prop="address" label="收件地址" width="180">
            </el-table-column>
            <el-table-column prop="carId" label="车辆编号" width="120">
            </el-table-column>
            <el-table-column prop="carName" label="车辆名称" width="120">
            </el-table-column>
            <el-table-column prop="orderStatus" label="订单状态" width="120">
            </el-table-column>
            <el-table-column prop="price" label="订单价格" width="120">
            </el-table-column>
            <el-table-column prop="orderDescribe" label="物流描述" width="300">
            </el-table-column>
            <el-table-column prop="operate" label="操作" width="200">
              <template slot-scope="scope">
                <el-button type="success" @click="getMethod('updateButton',scope.row)">修改</el-button>
<<<<<<< HEAD
                <el-button type="danger" @click="getMethod('delete',scope.row)">删除</el-button>
=======
                <el-popconfirm 
                    title="确认要删除吗?"
                    @confirm="getMethod('delete')"
                    style="margin-left: 10px;">
                    <el-button slot="reference" type="danger">删除</el-button>
                </el-popconfirm>
>>>>>>> origin/master
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
            <el-form-item label="订单编号" prop="id">
              <el-input v-model="form.id"></el-input>
            </el-form-item>
            <el-form-item label="用户账号" prop="userAccount">
              <el-input v-model="form.userAccount"></el-input>
          </el-form-item>
            <el-form-item label="用户姓名" prop="userName">
                <el-input v-model="form.userName"></el-input>
            </el-form-item>
            <el-form-item label="寄送物品" prop="goodName">
                <el-input v-model="form.goodName"></el-input>
            </el-form-item>
            <el-form-item label="物品编号" prop="goodId">
              <el-input v-model="form.goodId"></el-input>
          </el-form-item>
            <el-form-item label="物品数量" prop="goodNumber">
                <el-input v-model="form.goodNumber"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" prop="userPhone">
                <el-input v-model="form.userPhone"></el-input>
            </el-form-item>
            <el-form-item label="收件人" prop="addressee">
                <el-input v-model="form.addressee"></el-input>
            </el-form-item>
            <el-form-item label="收件电话" prop="addressPhone">
                <el-input v-model="form.addressPhone"></el-input>
            </el-form-item>
            <el-form-item label="收件地址" prop="address">
                <el-input v-model="form.address"></el-input>
            </el-form-item>
            <el-form-item label="车辆编号" prop="carId">
              <el-input v-model="form.carId"></el-input>
          </el-form-item>
            <el-form-item label="车辆名称" prop="carName">
                <el-input v-model="form.carName"></el-input>
            </el-form-item>
            <el-form-item label="订单状态" prop="orderStatus">
              <el-select v-model="form.orderStatus" placeholder="请选择仓库">
                <el-option label="待发货" value="待发货"></el-option>
                <el-option label="运送中" value="运送中"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="订单价格" prop="price">
              <el-input v-model="form.price"></el-input>
          </el-form-item>
            <el-form-item label="物流描述" prop="orderDescribe">
              <el-input v-model="form.orderDescribe"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer" v-show="addDialogVisible">
            <el-button @click="addDialogVisible = false">取消</el-button>
<<<<<<< HEAD
            <el-button type="primary" @click="getMethod('addAction ')">确定</el-button>
          </span>
          <span slot="footer" class="dialog-footer" v-show="updateDialogVisible">
            <el-button @click="updateDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="updateAction">确定</el-button>
=======
            <el-button type="primary" @click="getMethod('addAction')">确定</el-button>
          </span>
          <span slot="footer" class="dialog-footer" v-show="updateDialogVisible">
            <el-button @click="updateDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="getMethod('updateAction')">确定</el-button>
>>>>>>> origin/master
          </span>
        </el-dialog>

      <!-- 分页 -->
      <div>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage3"
          :page-size="pageSize"
          layout="prev, pager, next, jumper"
          :total="pageNum">
        </el-pagination>
      </div>
    </div>
</template>

<script>
import { logisticRule,logisticForm,logisticGroup } from "@/js/logistics.js";
import { getEasyMethod } from "@/utils/common.js";
export default {
<<<<<<< HEAD
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
=======
    data() {
  return {
      id:'',
      tableData: logisticList(),
      status:'',
      dialogVisible: false,
      addDialogVisible: false,
      updateDialogVisible: false,
      form: logisticForm
  }
},
created() {
  rules = logisticRule();
},
methods: {
  resetParam(){
      this.id = '';
      this.orderStatus = '';
  },

  resetForm() {
      this.$refs.form.resetFields();
  },
},

mounted: {
},
>>>>>>> origin/master
}
</script>

<style>
.mgb20 {
    margin-bottom: 20px;
}
</style>