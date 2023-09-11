<template>
    <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
        <el-input v-model="orderId" placeholder="请输入订单编号关键字" suffix-icon="el-icon-search" style="width: 300px;"></el-input>
    
        <el-button type="success" style="margin-left: 10px;">搜索</el-button>
        <el-button type="info" @click="resetParam">重置</el-button>
        <el-button size="medium" type="primary" style="margin-left: 10px;" @click="add">增添新物流订单</el-button>
      
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
                <el-button type="success" @click="update(scope.row)">修改</el-button>
                <el-button type="danger">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      
        
        <!-- 增添表单 -->
        <el-dialog
          title=""
          :visible.sync="centerDialogVisible"
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
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取消</el-button>
            <el-button type="primary">确定</el-button>
          </span>
        </el-dialog>
      </div>
</template>

<script>
import { logisticList,logisticRule,logisticForm } from "@/js/logostics.js";
export default {
    data() {

let checkDuplicate =(rule,value,callback) => {
  // if(this.form.userid){
  //   return callback();
  // }
  // this.$axios.get(this.$httpUrl+"/user/find/?username="+this.form.username).then(res=>res.data).then(res => {
  //   if(res.code == 200){
  //     callback(new Error('Account already exist'));
  //   }else{
  //     callback();
  //   }
  // })
}


  return {
      orderId:'',
      tableData: logisticList(),
      centerDialogVisible: false,
      form: logisticForm
  }
},
created() {
  rules = logisticRule();
},
methods: {
  resetParam(){
      this.orderId = '';
  },
  add(){
      this.form.orderId='';
      this.centerDialogVisible = true;
      this.$nextTick(() => {
      this.resetForm();
    })
  },
  resetForm() {
      this.$refs.form.resetFields();
  },
  update(row){
          this.centerDialogVisible = true;
          this.$nextTick(() => {
            this.resetForm();
            this.form.orderId = row.orderId;
            this.form.userName = row.userName;
            this.form.userPhone = row.userPhone;
            this.form.goodName = row.goodName;
            this.form.number = row.number;
            this.form.addressee = row.addressee;
            this.form.addresseePhone = row.addresseePhone;
            this.form.address = row.address;
            this.form.carName = row.carName;
            this.form.description = row.description;
          }) 
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