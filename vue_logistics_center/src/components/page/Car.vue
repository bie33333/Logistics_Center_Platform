<template>
    <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
        <el-input v-model="id" placeholder="请输入车辆编号关键字" suffix-icon="el-icon-search" style="width: 300px;"></el-input>
    
        <el-select v-model="status" filterable placeholder="请选择车辆状态" style="margin-left: 10px;">
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
            <el-form-item label="车辆编号" prop="id">
              <el-input v-model="form.id"></el-input>
            </el-form-item>
            <el-form-item label="车辆名称" prop="name">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="车辆状态" prop="status">
                <el-select v-model="form.status" placeholder="请选择车辆状态">
                  <el-option label="待出车" value="待出车"></el-option>
                  <el-option label="出车中" value="出车中"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="物品描述" prop="describe">
              <el-input v-model="form.describe"></el-input>
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
      status:'',
      pageSize:1,
      pageNum:10,

      tableData: [{
      id:'165askda',
      name:'测试车辆1',
      status:'待出车',
      describe: '测试车辆'
    }],

    states:[
      {
        value:'待出车',
        label:'待出车'
      },
      {
        value:'出车中',
        label:'出车中'
      },
    ],

      centerDialogVisible: false,
      form:{
          id:'',
          name:'',
          status:'',
          describe:''
      },

      rules: {
          id: [
              { required: true, message: '请输入车辆编号', trigger: 'blur' },
              { min: 2, max: 10, message: '长度在3至10个字符之间', trigger: 'blur' },
              { validator: checkDuplicate, trigger: 'blur' }
          ],

          name: [
              { required: true, message: '请输入车辆名称', trigger: 'blur' },
          ],

          status:[
          { required: true, message: '请选择车辆状态', trigger: 'change' },
          ],

          describe: [
              { required: true, message: '请输入车辆描述', trigger: 'blur' },
          ],
      }
      
  }
},

methods: {
  resetParam(){
      this.id = '';
      this.status = '';
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
            this.form.name = row.name;
            this.form.status = row.status;
            this.form.describe = row.describe;
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