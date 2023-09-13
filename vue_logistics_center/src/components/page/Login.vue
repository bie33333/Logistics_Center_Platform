<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">企业物流中心平台</div>
            <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
                <el-form-item>
                    <el-select v-model="param.role" clearable placeholder="登录角色">
                    <el-option key="0" label="manager" value="manager"></el-option>
                    <el-option key="1" label="user" value="user"></el-option>
                </el-select>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input v-model="param.account" placeholder="username">
                        <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
                    </el-input>
                </el-form-item>
                
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="param.password" @keyup.enter.native="submitForm()">
                        <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                </div>
                <div class="login-btn">
                    <el-button type="success" @click="getMethod('addButton')">注册</el-button>
                </div>               
            </el-form>
        </div>

        <!-- 增添用户表单 -->
        <el-dialog
          title=""
          :visible.sync="dialogVisible"
          width="50%"
          center>
          <el-form ref="form" :rules="rules" :model="form" label-width="100px">
            <el-form-item label="用户账户" prop="userAccount">
                <el-input v-model="form.userAccount"></el-input>
              </el-form-item>
            <el-form-item label="用户密码" prop="userPassword" >
              <el-input v-model="form.userPassword"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="confirmedPassword">
              <el-input v-model="form.confirmedPassword" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="用户姓名" prop="userName">
                <el-input v-model="form.userName"></el-input>
              </el-form-item>
            <el-form-item label="联系电话" prop="userPhone">
                <el-input v-model="form.userPhone"></el-input>
              </el-form-item>
            <el-form-item label="年龄" prop="userAge">
              <el-input v-model="form.userAge"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="userSex">
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
            <el-button type="primary" @click="getMethod('addAction')">确定</el-button>
          </span>
        </el-dialog>
    </div>
</template>

<script>
import { loginRule, loginForm, managerLogin, userLogin } from "@/js/login.js";

export default {
    data() {

        return {
            param: {
                role: '',
                account: '',
                password: '',
            },
            dialogVisible: false,
            addDialogVisible: false,
            form: loginForm(),
            rules: '',
        };
    },
    created(){
        this.rules = loginRule();
    },
    methods:{
        submitForm(){
            if (this.param.role == "manager") {
                this.handleManagerLogin();
            }else if(this.param.role == "user"){
                this.handleUserLogin();
            }else{
                this.$message('请选择角色');
            }
            
        },
        handleManagerLogin(){
            console.log('managerLogin');
            var manager = {
                account : this.param.account, password: this.param.password
            }
            console.log(manager);
            managerLogin(manager).then(res=>{
                var result = res.data
                if(result.status==200) {
                    this.$message.success('登录成功');
                    localStorage.setItem('manager', JSON.stringify(result.data));
                    console.log(JSON.parse(localStorage.getItem('user')));
                    console.log(JSON.parse(localStorage.getItem('manager')));
                    this.$router.push('/home');
                }else{
                    this.$message.error(result.msg);
                }
            })
        },
        handleUserLogin(){
            console.log('userLogin');
            var user = {
                userAccount : this.param.account, userPassword: this.param.password
            }
            userLogin(user).then(res=>{
                var result = res.data;
                console.log(result);
                if(result.status===200) {
                    this.$message.success('登录成功');
                    localStorage.setItem('user', JSON.stringify(result.data));
                    console.log(JSON.parse(localStorage.getItem('user')));
                    console.log(JSON.parse(localStorage.getItem('manager')));
                    this.$router.push('/home');
                }else{
                    this.$message.error(result.msg);
                }
            })
        },
    }
}
</script>

<style>
.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    /* background-image: url(../../assets/img/login-bg.jpg); */
    background-size: 100%;
}
.ms-title {
    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 20px;
    color: #000000;
    border-bottom: 1px solid #ddd;
}
.ms-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 350px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.3);
    overflow: hidden;
}
.ms-content {
    padding: 30px 30px;
}
.login-btn {
    text-align: center;
}
.login-btn button {
    width: 100%;
    height: 36px;
    margin-bottom: 10px;
}
.login-tips {
    font-size: 12px;
    line-height: 30px;
    color: #000000;
}
</style>