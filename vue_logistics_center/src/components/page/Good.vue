<template>
  <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
    <el-input v-model="name" placeholder="请输入物品名关键字" suffix-icon="el-icon-search" style="width: 250px;"></el-input>

    <el-select v-model="option" filterable placeholder="请选择存储仓库" style="margin-left: 10px;">
      <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.value"
            :value="item.value">
      </el-option>
    </el-select>

    <el-button type="success" style="margin-left: 10px;">搜索</el-button>
    <el-button type="info" @click="resetParam">重置</el-button>
    <el-button size="medium" type="primary" style="margin-left: 10px;" @click="add">增添新物品</el-button>
    <!-- <el-button type="info" style="margin-left: 10px;">增添新仓库</el-button>
    <el-input v-model="warehouseName" placeholder="请输入要删除的仓库名" style="width:200px; margin-left: 10px;"></el-input>
    <el-button type="danger" style="margin-left: 10px;">删除仓库</el-button> -->
  
    <div>
      <el-table :data="tableData" :header-cell-style="{ background:'orange',color:'black'}" border>
        <el-table-column prop="id" label="物品编号" width="180">
        </el-table-column>
        <el-table-column prop="name" label="物品名称" width="120">
        </el-table-column>
        <el-table-column prop="warehouse" label="储存仓库" width="120">
        </el-table-column>
        <el-table-column prop="number" label="数量" width="120">
        </el-table-column>
        <el-table-column prop="good_describe" label="物品描述" width="450">
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
        <el-form-item label="物品编号" prop="id">
          <el-input v-model="form.id"></el-input>
        </el-form-item>
        <el-form-item label="物品名称" prop="name">
            <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="储存仓库" prop="warehouse">
            <el-select v-model="form.warehouse" placeholder="请选择仓库">
              <el-option v-for="value in warehouses" :label="value" :value="value"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="物品数量" prop="number">
            <el-input v-model="form.number"></el-input>
          </el-form-item>
        <el-form-item label="物品描述" prop="good_describe">
          <el-input v-model="form.good_describe"></el-input>
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
            name:'',
            option:'',
            warehouse:'',
            warehouseName:'',
            pageSize:1,
            pageNum:10,

            tableData: [{
            id:'16548kda',
            name:'冰箱',
            warehouse:'仓库1',
            number:'10',
            good_describe: '普通的冰箱'
          }],

          warehouses:[ '仓库1', '仓库2' ,'仓库3', '仓库4'],

          options: [{
            value: '仓库1',
            label: '仓库1'
          }, {
            value: '仓库2',
            label: '仓库2'
          }, {
            value: '仓库3',
            label: '仓库3'
          }, {
            value: '仓库4',
            label: '仓库4'
          }],

            centerDialogVisible: false,
            form:{
                id:'',
                name:'',
                warehouse:'',
                number:'',
                good_describe:''
            },

            rules: {
                id: [
                    { required: true, message: '请输入物品编号', trigger: 'blur' },
                    { min: 2, max: 10, message: '长度在3至10个字符之间', trigger: 'blur' },
                    { validator: checkDuplicate, trigger: 'blur' }
                ],

                name: [
                    { required: true, message: '请输入物品名称', trigger: 'blur' },
                ],

                warehouse:[
                { required: true, message: '请选择储存位置', trigger: 'change' },
                ],

                number: [
                    { required: true, message: '请输入物品数量', trigger: 'blur' },
                ],

                good_describe: [
                    { required: true, message: '请输入物品描述', trigger: 'blur' },
                ],
            }
            
        }
    },

    methods: {
        resetParam(){
            this.name = '';
            this.option = '';
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
            this.form.warehouse = row.warehouse;
            this.form.number = row.number;
            this.form.good_describe = row.good_describe;
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

</style>