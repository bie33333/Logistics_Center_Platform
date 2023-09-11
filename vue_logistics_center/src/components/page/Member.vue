<template>
    <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
        <el-input v-model="username" placeholder="请输入用户名关键字" suffix-icon="el-icon-search" style="width: 300px;"
                ></el-input>
        <el-button type="success" style="margin-left: 10px;">搜索</el-button>
        <el-button type="info" @click="resetParam">重置</el-button>
        <el-button size="medium" type="primary" style="margin-left: 10px;" @click="addAccount">增添新账户</el-button>
      
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
                <el-button type="success" @click="update(scope.row)">修改</el-button>
                <el-button type="danger" @click="del(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      
        
        <!-- 增添用户表单 -->
        <el-dialog ref="add"
          title=""
          :visible.sync="addDialogVisible"
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
          <span slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="handleAdd">确认</el-button>
          </span>
        </el-dialog>

        <!-- 修改用户表单 -->
        <el-dialog ref="update"
          title=""
          :visible.sync="updateDialogVisible"
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
          <span slot="footer" class="dialog-footer">
            <el-button @click="updateDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="handleUpdate">确认</el-button>
          </span>
        </el-dialog>
    </div>
        
</template>

<script>
import { dataRule, dataForm, selectAllUser, addUser, updateUser } from "@/js/user.js";
export default {
    data() {
        return {
            username:'',
            tableData: [],
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
      this.selectUser();
      this.rules = dataRule(this.form);
    },
    methods: {
        //select用户
        selectUser(){
          selectAllUser(this.pageSet).then(res => {
            this.tableData = res.data.list;
          })
        },

        //重置搜索栏
        resetParam(){
            this.username = '';
        },

        /**
         * 用户表单
         */
        //增加按钮
        addAccount(){
            this.form.account='';
            this.addDialogVisible = true;
            this.$nextTick(() => {
              this.$refs.form.resetFields();
          })
        },
        //增加用户
        handleAdd(){
          this.$refs.form.validate((valid)=>{
            if(valid){
              console.log(this.form)
              addUser(this.form).then(res => {
                console.log(res);
              })
              this.addDialogVisible = false;
            }else{
              alert("error");
            }
          })
        },
        //更新按钮
        update(row){
          this.updateDialogVisible = true;
          this.$nextTick(() => {
            this.$refs.form.resetFields();
            this.form.userName = row.userName;
            this.form.userAccount = row.userAccount;
            this.form.userPassword = row.userPassword;
            this.form.userPhone = row.userPhone;
            this.form.userAge = row.userAge;
            this.form.userSex = row.userSex;
            this.form.userAddress = row.userAddress;
          }) 
        },
        //更新用户
        handleUpdate(){
          this.$refs.form.validate((valid)=>{
            if(valid){
              console.log("更新");
              console.log(this.form);
              this.updateDialogVisible = false;
            }else{
              alert("error");
            }
          })
        },
        //删除按钮
        del(row){
          this.$confirm('确定要删除此用户吗？', '提示', {
                type: 'warning'
          }).then(()=>{
            console.log("删除");
            console.log(row);
          }).catch(()=>{});
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