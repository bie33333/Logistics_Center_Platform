<template>
    <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
        <el-input v-model="id" placeholder="请输入订单编号关键字" suffix-icon="el-icon-search" style="width: 300px;"></el-input>

        <el-select v-model="orderStatus" filterable placeholder="请选择订单状态" style="margin-left: 10px;">
          <el-option
                v-for="item in states"
                :key="item.value"
                :label="item.label"
                :value="item.value">
          </el-option>
        </el-select>
    
        <el-button type="success" style="margin-left: 10px;">搜索</el-button>
        <el-button type="info" @click="resetParam">重置</el-button>
        <el-button size="medium" type="primary" style="margin-left: 10px;" @click="add">增添新物流订单</el-button>
      
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
                <el-button type="success" @click="update(scope.row)">修改</el-button>
                <el-popconfirm 
                    title="确认要删除吗?"
                    @confirm="del()"
                    style="margin-left: 10px;">
                    <el-button slot="reference" type="danger">删除</el-button>
                </el-popconfirm>
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
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取消</el-button>
            <el-button type="primary">确定</el-button>
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
      id:'',
      orderStatus:'',
      pageSize:1,
      pageNum:10,

      tableData: [{
      id:'123test',
      userAccount:'Test',
      userName:'A',
      userPhone:'12345678901',
      goodName:'GoodA',
      goodId:'asdxzc',
      goodNumber:'15',
      orderStatus:'待发货',
      addressee:'B',
      addressPhone:'98745612300',
      address:'陕西省西安市',
      carId:'qwerty',
      carName:'测试车辆1',
      price:'120',
      orderDescribe: '测试物流信息'
    }],

    states:[
      {
        value:'待发货',
        label:'待发货'
      },
      {
        value:'运送中',
        label:'运送中'
      },
    ],

      centerDialogVisible: false,
      form:{
          id:'',
          userName:'',
          userAccount:'',
          userPhone:'',
          goodId:'',
          goodName:'',
          goodNumber:'',
          addressee:'',
          addressPhone:'',
          address:'',
          carId:'',
          carName:'',
          orderStatus:'',
          price:'',
          orderDescribe:''
      },

      rules: {
            id: [
              { required: true, message: '请输入订单编号', trigger: 'blur' },
              { min: 2, max: 10, message: '长度在2至10个字符之间', trigger: 'blur' },
              { validator: checkDuplicate, trigger: 'blur' }
            ],

            userAccount:[
              { required: true, message: '请输入用户账户', trigger: 'blur' },
              { min: 6, max: 10, message: '长度在2至10个字符之间', trigger: 'blur' }
            ],

            userName: [
              { required: true, message: '请输入用户名称', trigger: 'blur' },
              { min: 6, max: 10, message: '长度在2至15个字符之间', trigger: 'blur' }
            ],

            userPhone: [
                    { required: true, message: '请输入联系电话', trigger: 'blur' },
                    { min: 11, max: 11, message: '长度为11位', trigger: 'blur' },
            ],

            goodId: [
              { required: true, message: '请输入物品编号', trigger: 'blur' },
              { min: 2, max: 10, message: '长度在2至10个字符之间', trigger: 'blur' },
            ],

            goodName: [
              { required: true, message: '请输入物品名称', trigger: 'blur' },
            ],

            goodNumber: [
              { required: true, message: '请输入物品数量', trigger: 'blur' },
            ],

            addressee: [
              { required: true, message: '请输入收件人', trigger: 'blur' },
            ],

            addressPhone: [
                    { required: true, message: '请输入收件电话', trigger: 'blur' },
                    { min: 11, max: 11, message: '长度为11位', trigger: 'blur' },
            ],

            address: [
              { required: true, message: '请输入收件地址', trigger: 'blur' },
            ],

            carId: [
              { required: true, message: '请输入车辆编号', trigger: 'blur' },
            ],

            carName: [
              { required: true, message: '请输入车辆名称', trigger: 'blur' },
            ],

            orderStatus:[
                { required: true, message: '请选择订单状态', trigger: 'change' },
            ],

            price: [
              { required: true, message: '请输入订单价格', trigger: 'blur' },
            ],

            orderDescribe: [
              { required: true, message: '请输入物流描述', trigger: 'blur' },
            ],
      }
      
  }
},

methods: {
  resetParam(){
      this.id = '';
      this.orderStatus = '';
  },
  add(){
      this.form.id='';
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
            this.form.id = row.id;
            this.form.userName = row.userName;
            this.form.userAccount = row.userAccount;
            this.form.userPhone = row.userPhone;
            this.form.goodId = row.goodId;
            this.form.goodName = row.goodName;
            this.form.goodNumber = row.goodNumber;
            this.form.addressee = row.addressee;
            this.form.addressPhone = row.addressPhone;
            this.form.address = row.address;
            this.form.carId = row.carId;
            this.form.carName = row.carName;
            this.form.orderStatus = row.orderStatus;
            this.form.price = row.price;
            this.form.orderDescribe = row.orderDescribe;
          }) 
  },
  del(){
      alert("删除成功");
  },
  handleSizeChange(val) {
    console.log(`每页 ${val} 条`);
  },
  handleCurrentChange(val) {
    console.log(`当前页: ${val}`);
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