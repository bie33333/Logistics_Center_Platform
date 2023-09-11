<template>
    <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
        <el-input v-model="carName" placeholder="请输入车辆名称关键字" suffix-icon="el-icon-search" style="width: 300px;"></el-input>
    
        <el-select v-model="state" filterable placeholder="请选择车辆状态" style="margin-left: 10px;">
          <el-option
                v-for="item in states"
                :key="item.value"
                :label="item.label"
                :value="item.value">
          </el-option>
        </el-select>
    
        <el-button type="success" style="margin-left: 10px;">搜索</el-button>
        <el-button type="info" @click="resetParam">重置</el-button>
        <el-button size="medium" type="primary" style="margin-left: 10px;" @click="add">增添新车辆</el-button>
      
        <div>
          <el-table :data="tableData" :header-cell-style="{ background:'orange',color:'black'}" border>
            <el-table-column prop="carId" label="车辆编号" width="180">
            </el-table-column>
            <el-table-column prop="carName" label="车辆名称" width="120">
            </el-table-column>
            <el-table-column prop="state" label="车辆状态" width="120">
            </el-table-column>
            <el-table-column prop="description" label="车辆描述" width="500">
            </el-table-column>
            <el-table-column prop="operate" label="操作">
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
            <el-form-item label="车辆编号" prop="carId">
              <el-input v-model="form.carId"></el-input>
            </el-form-item>
            <el-form-item label="车辆名称" prop="carName">
                <el-input v-model="form.carName"></el-input>
            </el-form-item>
            <el-form-item label="车辆状态" prop="state">
                <el-select v-model="form.state" placeholder="请选择仓库">
                  <el-option label="待出车" value="待出车"></el-option>
                  <el-option label="出车中" value="出车中"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="物品描述" prop="description">
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
import { carList,carRules,carForm,stateList } from "@/js/car.js";
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
      carName:'',
      state:'',
      tableData: carList(),
      states: stateList(),
      centerDialogVisible: false,
      form: carForm(),
  }
},
created() {
  rules = carRules();
},
methods: {
  resetParam(){
      this.carName = '';
      this.state = '';
  },
  add(){
      this.form.carId='';
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
            this.form.carId = row.carId;
            this.form.carName = row.carName;
            this.form.state = row.state;
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