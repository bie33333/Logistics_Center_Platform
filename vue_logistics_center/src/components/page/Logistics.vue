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
            <el-table-column prop="description" label="物流描述" width="120">
            </el-table-column>
            <el-table-column prop="operate" label="操作" width="200">
              <el-button type="success">修改</el-button>
              <el-button type="danger">删除</el-button>
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

      tableData: [{
      orderId:'123test',
      userName:'A',
      userPhone:'12345678901',
      goodName:'GoodA',
      number:'15',
      addressee:'B',
      addresseePhone:'98745612300',
      address:'陕西省西安市',
      carName:'测试车辆1',
      description: '测试车辆'
    }],

      centerDialogVisible: false,
      form:{
          orderId:'',
          userName:'',
          userPhone:'',
          goodName:'',
          number:'',
          addressee:'',
          addresseePhone:'',
          address:'',
          carName:'',
          description:''
      },

      rules: {
            orderId: [
              { required: true, message: '请输入订单编号', trigger: 'blur' },
              { min: 2, max: 10, message: '长度在2至10个字符之间', trigger: 'blur' },
              { validator: checkDuplicate, trigger: 'blur' }
            ],

            userName: [
              { required: true, message: '请输入用户名称', trigger: 'blur' },
              { min: 6, max: 10, message: '长度在2至15个字符之间', trigger: 'blur' }
            ],

            userPhone: [
                    { required: true, message: '请输入联系电话', trigger: 'blur' },
                    { min: 11, max: 11, message: '长度为11位', trigger: 'blur' },
            ],

            goodName: [
              { required: true, message: '请输入物品名称', trigger: 'blur' },
              { min: 6, max: 10, message: '长度在2至15个字符之间', trigger: 'blur' }
            ],

            number: [
              { required: true, message: '请输入物品数量', trigger: 'blur' },
            ],

            addressee: [
              { required: true, message: '请输入收件人', trigger: 'blur' },
            ],

            addresseePhone: [
                    { required: true, message: '请输入收件电话', trigger: 'blur' },
                    { min: 11, max: 11, message: '长度为11位', trigger: 'blur' },
            ],

            address: [
              { required: true, message: '请输入收件地址', trigger: 'blur' },
            ],

            carName: [
              { required: true, message: '请输入车辆名称', trigger: 'blur' },
              { min: 6, max: 10, message: '长度在2至15个字符之间', trigger: 'blur' }
            ],

            description: [
              { required: true, message: '请输入物流描述', trigger: 'blur' },
            ],
      }
      
  }
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