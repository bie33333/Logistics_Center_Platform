<template>
    <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
        <el-input v-model="search.username" placeholder="请输入用户名关键字" suffix-icon="el-icon-search" style="width: 300px;"
                ></el-input>
        <el-button type="success" style="margin-left: 10px;">搜索</el-button>
        <el-button type="info" @click="getMethod('resetButton')">重置</el-button>
        <el-button size="medium" type="primary" style="margin-left: 10px;" @click="getMethod('addButton')">增添新账户</el-button>
      
        <div>
          <el-table :data="tableData" :header-cell-style="{ background:'orange',color:'black'}" border>
            <el-table-column prop="userName" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="userAccount" label="账户" width="180">
            </el-table-column>
            <el-table-column prop="userAge" label="年龄" width="100">
            </el-table-column>
            <el-table-column prop="userSex" label="性别" width="100">
            </el-table-column>
            <el-table-column prop="userPhone" label="联系电话" width="180">
            </el-table-column>
            <el-table-column prop="userAddress" label="地址" width="300">
            </el-table-column>
            <el-table-column prop="operate" label="操作">
              <template slot-scope="scope">
                <el-button type="success" @click="getMethod('updateButton',scope.row)">修改</el-button>
                <el-button type="danger" @click="getMethod('delete',scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      
        
        <!-- 增添用户表单 -->
        <el-dialog ref="add"
          title=""
          :visible.sync="dialogVisible"
          width="50%"
          center>
          <el-form ref="form" :rules="rules" :model="form" label-width="100px">
            <el-form-item label="用户姓名" prop="userName">
              <el-input v-model="form.userName"></el-input>
            </el-form-item>
            <el-form-item label="用户账户" prop="userAccount">
                <el-input v-model="form.userAccount"></el-input>
              </el-form-item>
            <el-form-item label="用户密码" prop="userPassword" >
              <el-input v-model="form.userPassword"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="userConfirmedPassword">
              <el-input v-model="form.userConfirmedPassword" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" prop="userPhone">
                <el-input v-model="form.userPhone"></el-input>
              </el-form-item>
            <el-form-item label="年龄" prop="userAge">
              <el-input v-model="form.userAge"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-select v-model="form.userSex" placeholder="请选择性别">
                  <el-option label="男" value="男"></el-option>
                  <el-option label="女" value="女"></el-option>
                </el-select>
              </el-form-item>
            <el-form-item label="住址" prop="userAddress">
                <el-input v-model="form.userAddress"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer" v-show="addDialogVisible">
            <el-button @click="addDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="getMethod('addAction')">确认</el-button>
          </span>
          <span slot="footer" class="dialog-footer" v-show="updateDialogVisible">
            <el-button @click="updateDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="getMethod('updateAction')">确认</el-button>
          </span>
        </el-dialog>

    </div>
        
</template>

<script>
import { dataRule, dataForm, selectAllUser, addUser, updateUser, userGroup } from "@/js/user.js";
import { getEasyMethod } from "@/utils/common.js";
export default {
    data() {
        return {
            search:{
              username:'',
            },
            tableData: [],
            dialogVisible: false,
            updateDialogVisible: false,
            addDialogVisible: false,
            form: dataForm(),
            rules: '',
            pageSet: {
              pageNumber: 0,
              pageSize: 30,
              pageTotal: 0,
            }
        }
    },
    created() {
      this.rules = dataRule(this.form);
      this.getMethod('select');
    },
    methods: {
      getMethod(type , row){
        var group = userGroup();
        getEasyMethod(this,type,row,group.methodGroup,group.msgGroup);
      },
    },
}
</script>

<style>
.mgb20 {
    margin-bottom: 20px;
}
</style>