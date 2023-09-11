<template>
  <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
    <el-input v-model="goodName" placeholder="请输入物品名关键字" suffix-icon="el-icon-search" style="width: 300px;"></el-input>

    <el-select v-model="location" filterable placeholder="请选择存储仓库" style="margin-left: 10px;">
      <el-option
            v-for="item in locations"
            :key="item.value"
            :label="item.label"
            :value="item.value">
      </el-option>
    </el-select>

    <el-button type="success" style="margin-left: 10px;">搜索</el-button>
    <el-button type="info" @click="resetParam">重置</el-button>
    <el-button size="medium" type="primary" style="margin-left: 10px;" @click="add">增添新物品</el-button>
  
    <div>
      <el-table :data="tableData" :header-cell-style="{ background:'orange',color:'black'}" border>
        <el-table-column prop="goodId" label="物品编号" width="180">
        </el-table-column>
        <el-table-column prop="goodName" label="物品名称" width="120">
        </el-table-column>
        <el-table-column prop="location" label="储存仓库" width="120">
        </el-table-column>
        <el-table-column prop="number" label="数量" width="120">
        </el-table-column>
        <el-table-column prop="description" label="物品描述" width="450">
        </el-table-column>
        <el-table-column prop="operate" label="操作">
          <template slot-scope="scope">
            <el-button type="success" @click="update(scope.row)">修改</el-button>
            <el-button type="danger">删除</el-button>
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
        <el-form-item label="物品编号" prop="goodId">
          <el-input v-model="form.goodId"></el-input>
        </el-form-item>
        <el-form-item label="物品名称" prop="goodName">
            <el-input v-model="form.goodName"></el-input>
        </el-form-item>
        <el-form-item label="储存仓库" prop="location">
            <el-select v-model="form.location" placeholder="请选择仓库">
              <el-option label="仓库1" value="仓库1"></el-option>
              <el-option label="仓库2" value="仓库2"></el-option>
              <el-option label="仓库3" value="仓库3"></el-option>
              <el-option label="仓库4" value="仓库4"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="物品数量" prop="number">
            <el-input v-model="form.number"></el-input>
          </el-form-item>
        <el-form-item label="物品描述" prop="description">
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
            goodName:'',
            location:'',

            tableData: [{
            goodId:'16548kda',
            goodName:'冰箱',
            location:'仓库1',
            number:'10',
            description: '普通的冰箱'
          }],

          locations:[
            {
              value:'仓库1',
              label:'仓库1'
            },
            {
              value:'仓库2',
              label:'仓库2'
            },
            {
              value:'仓库3',
              label:'仓库3'
            },
            {
              value:'仓库4',
              label:'仓库4'
            },
          ],

            centerDialogVisible: false,
            form:{
                goodId:'',
                goodName:'',
                location:'',
                number:'',
                description:''
            },

            rules: {
                goodId: [
                    { required: true, message: '请输入物品编号', trigger: 'blur' },
                    { min: 2, max: 10, message: '长度在3至10个字符之间', trigger: 'blur' },
                    { validator: checkDuplicate, trigger: 'blur' }
                ],

                goodName: [
                    { required: true, message: '请输入物品名称', trigger: 'blur' },
                ],

                location:[
                { required: true, message: '请选择储存位置', trigger: 'change' },
                ],

                number: [
                    { required: true, message: '请输入物品数量', trigger: 'blur' },
                ],

                description: [
                    { required: true, message: '请输入物品描述', trigger: 'blur' },
                ],
            }
            
        }
    },

    methods: {
        resetParam(){
            this.goodName = '';
            this.location = '';
        },
        add(){
            this.form.goodId='';
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
            this.form.goodId = row.goodId;
            this.form.goodName = row.goodName;
            this.form.location = row.location;
            this.form.number = row.number;
            this.form.description = row.description;
          }) 
        }
    },

    mounted: {
    },
}
</script>

<style>

</style>