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
                    <el-input v-model="param.id" placeholder="username">
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
                    <el-button type="success" @click="addAccount">注册</el-button>
                </div>               
            </el-form>
        </div>

        <!-- 增添用户表单 -->
        <el-dialog
          title=""
          :visible.sync="centerDialogVisible"
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
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取消</el-button>
            <el-button type="primary">确认</el-button>
          </span>
        </el-dialog>
    </div>
</template>

<script>
import { userLogin } from "@/api/login";
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
            param: {
                role: '',
                id: '',
                password: '',
            },
            rules: {
                managerId: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
            },

            centerDialogVisible: false,

            

            form:{
                userName:'',
                userPassword:'',
                confirmedPassword:'',
                userAccount:'',
                userPhone:'',
                userAge:'',
                userSex:'',
                userAddress:'',
            },

            rules: {
                userName: [
                    { required: true, message: '请输入用户姓名', trigger: 'blur' },
                    { min: 2, max: 10, message: '长度在2至10个字符之间', trigger: 'blur' },
                ],

                userPassword: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 6, max: 10, message: '长度在6至15个字符之间', trigger: 'blur' }
                ],

                confirmedPassword: [
                    { validator: validatePass2, trigger: 'blur' }
                ],

                userAccount: [
                    { required: true, message: '请输入账户', trigger: 'blur' },
                    { min: 3, max: 10, message: '长度在3至10个字符之间', trigger: 'blur' },
                    { validator: checkDuplicate, trigger: 'blur' }
                ],

                userPhone: [
                    { required: true, message: '请输入电话', trigger: 'blur' },
                    { min: 11, max: 11, message: '长度为11位', trigger: 'blur' },
                ],

                userAge: [
                    { required: true, message: '请输入年龄', trigger: 'blur' },
                ],

                userSex:[
                { required: true, message: '请选择性别', trigger: 'change' },
                ],

                userAddress: [
                    { required: true, message: '请输入住址', trigger: 'blur' },
                ],
            }
        };
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
            managerLogin(this.param).then(res=>{
                if(res.status==200) {
                    if(res.data){
                        this.$message.success('登录成功');
                        localStorage.setItem('managerId', this.param.id);
                        // this.$router.push('/index/dashboard');
                    } 
                    else{
                        this.$message.error('登录失败');
                    }
                }else{
                    this.$message.error('服务器错误');
                }
            })
        },
        handleUserLogin(){
            memberLogin(this.param).then(res=>{
                if(res.status==200) {
                    if(res.data){
                        this.$message.success('登录成功');
                        localStorage.setItem('memberId', this.param.id);
                        // this.$router.push('/memberView');
                    } 
                    else{
                        this.$message.error('登录失败');
                    }
                }else{
                    this.$message.error('服务器错误');
                }
            })
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