<template>
    <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
        <el-input v-model="username" placeholder="请输入用户名关键字" suffix-icon="el-icon-search" style="width: 300px;"
                ></el-input>
        <el-button type="success" style="margin-left: 10px;">搜索</el-button>
        <el-button type="info" @click="resetParam">重置</el-button>
        <el-button size="medium" type="primary" style="margin-left: 10px;" @click="addAccount">增添新账户</el-button>
      
        <div>
          <el-table :data="tableData" :header-cell-style="{ background:'orange',color:'black'}" border>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="account" label="账户" width="180">
            </el-table-column>
            <el-table-column prop="age" label="年龄" width="100">
            </el-table-column>
            <el-table-column prop="sex" label="性别" width="100">
            </el-table-column>
            <el-table-column prop="phone" label="联系电话" width="180">
            </el-table-column>
            <el-table-column prop="address" label="地址" width="300">
            </el-table-column>
            <el-table-column prop="operate" label="操作">
              <template slot-scope="scope">
                <el-button type="success" @click="update(scope.row)">修改</el-button>
                <el-button type="danger">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      
        
        <!-- 增添用户表单 -->
        <el-dialog
          title=""
          :visible.sync="centerDialogVisible"
          width="50%"
          center>
          <el-form ref="form" :rules="rules" :model="form" label-width="100px">
            <el-form-item label="用户姓名" prop="name">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="用户账户" prop="account">
                <el-input v-model="form.account"></el-input>
              </el-form-item>
            <el-form-item label="用户密码" prop="password" >
              <el-input v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="confirmedPassword">
              <el-input v-model="form.confirmedPassword" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" prop="phone">
                <el-input v-model="form.phone"></el-input>
              </el-form-item>
            <el-form-item label="年龄" prop="address">
              <el-input v-model="form.age"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-select v-model="form.sex" placeholder="请选择性别">
                  <el-option label="男" value="男"></el-option>
                  <el-option label="女" value="女"></el-option>
                </el-select>
              </el-form-item>
            <el-form-item label="住址" prop="address">
                <el-input v-model="form.address"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取消</el-button>
            <el-button type="primary">确认</el-button>
          </span>
        </el-dialog>

    </div>
        
</template>

<script>
import { memberList, dataRule, dataForm } from "@/js/member.js";
export default {
    data() {

        var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请确认密码'));
        } else if (value !== this.form.password) {
          callback(new Error('两次输入的密码不一致!'));
        } else {
          callback();
        }
      };

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
            username:'',
            tableData: memberList(),
            centerDialogVisible: false,
            form: dataForm,
        }
    },
    created() {
      rules = dataRule();
    },
    methods: {
        resetParam(){
            this.username = '';
        },
        addAccount(){
            this.form.account='';
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
            this.form.name = row.name;
            this.form.account = row.account;
            this.form.password = row.password;
            this.form.phone = row.phone;
            this.form.age = row.age;
            this.form.sex = row.sex;
            this.form.address = row.address;
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