<template>
    <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
        <el-input v-model="name" placeholder="请输入车辆名称关键字" suffix-icon="el-icon-search" style="width: 300px;"></el-input>
    
        <el-select v-model="status" filterable placeholder="请选择车辆状态" style="margin-left: 10px;">
          <el-option
                v-for="item in status"
                :key="item.value"
                :label="item.label"
                :value="item.value">
          </el-option>
        </el-select>
    
        <el-button type="success" style="margin-left: 10px;">搜索</el-button>
        <el-button type="info" @click="resetParam">重置</el-button>
        <el-button size="medium" type="primary" style="margin-left: 10px;" @click="getMethod('addButton')">增添新车辆</el-button>
      
        <div>
          <el-table :data="tableData" :header-cell-style="{ background:'orange',color:'black'}" border>
            <el-table-column prop="id" label="车辆编号" width="180">
            </el-table-column>
            <el-table-column prop="name" label="车辆名称" width="120">
            </el-table-column>
            <el-table-column prop="status" label="车辆状态" width="120">
            </el-table-column>
            <el-table-column prop="describe" label="车辆描述" width="500">
            </el-table-column>
            <el-table-column prop="operate" label="操作">
              <template slot-scope="scope">
                <el-button type="success" @click="getMethod('updateButton',scope.row)">修改</el-button>
                <el-button type="danger" @click="getMethod('delete',scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      
        
        <!-- 增添表单 -->
        <el-dialog ref="add"
          title=""
          :visible.sync="dialogVisible"
          width="50%"
          center>
          <el-form ref="form" :rules="rules" :model="form" label-width="100px">
            <el-form-item label="车辆编号" prop="id">
              <el-input v-model="form.id"></el-input>
            </el-form-item>
            <el-form-item label="车辆名称" prop="name">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="车辆状态" prop="status">
                <el-select v-model="form.status" placeholder="车辆状态">
                  <el-option label="待出车" value="0"></el-option>
                  <el-option label="出车中" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="物品描述" prop="describe">
              <el-input v-model="form.describe"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer" v-show="addDialogVisible">
            <el-button @click="addDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="getMethod('addAction')">确定</el-button>
          </span>
          <span slot="footer" class="dialog-footer" v-show="updateDialogVisible">
            <el-button @click="updateDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="getMethod('updateAction')">确定</el-button>
          </span>
        </el-dialog>
      </div>
</template>

<script>
import { carRules, carForm, statusList,selectCar, carGroup } from "@/js/car.js";
import { getEasyMethod } from "@/utils/common.js";
export default {
    data() {
      return {
          name:'',
          status:'',
          tableData: [],
          status: statusList(),
          dialogVisible: false,
          addDialogVisible: false,
          updateDialogVisible: false,
          form: carForm(),
          rules: '',
          pageSet: {
            pageNumber: 0,
            pageSize: 30,
            pageTotal: 0,
          }
      }
},
created() {
  this.rules = carRules(this.form);
  this.selectCars();
},
  methods: {
    selectCars() {
      selectCar(this.pageSet).then(res => {
        this.tableData = res.data.list;
      })
    },
    //重置搜索框
    resetParam(){
        this.name = '';
        this.status = '';
    },

    getMethod(type, row){
      var group = carGroup(this.selectCars);
      getEasyMethod(this, type, row, group.methodGroup, group.msgGroup);
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