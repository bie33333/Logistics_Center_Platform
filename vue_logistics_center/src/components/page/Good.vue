<template>
  <div style="margin-bottom: 5px;margin-top: 5px;border-radius: 30%;">
    <el-input v-model="search.name" placeholder="请输入物品名关键字" suffix-icon="el-icon-search" style="width: 300px;"></el-input>

    <el-select v-model="search.warehouse" filterable placeholder="请选择存储仓库" style="margin-left: 10px;">
      <el-option
            v-for="item in warehouses"
            :key="item.value"
            :label="item.value"
            :value="item.value">
      </el-option>
    </el-select>

    <el-button type="success" style="margin-left: 10px;">搜索</el-button>
    <el-button type="info" @click="getMethod('resetButton')">重置</el-button>
    <el-button size="medium" type="primary" style="margin-left: 10px;" @click="getMethod('addButton')">增添新物品</el-button>
  
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
            <el-button type="success" @click="getMethod('updateButton',scope.row)">修改</el-button>
<<<<<<< HEAD
            <el-button type="danger" @click="getMethod('delete',scope.row)">删除</el-button>
=======
            <el-popconfirm 
                    title="确认要删除吗?"
                    @confirm="getMethod('delete')"
                    style="margin-left: 10px;">
                    <el-button slot="reference" type="danger">删除</el-button>
            </el-popconfirm>
>>>>>>> origin/master
          </template>
        </el-table-column>
      </el-table>
    </div>
  
    
    <!-- 增添表单 -->
    <el-dialog
      title=""
      :visible.sync="dialogVisible"
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
              <el-option label="仓库1" value="仓库1"></el-option>
              <el-option label="仓库2" value="仓库2"></el-option>
              <el-option label="仓库3" value="仓库3"></el-option>
              <el-option label="仓库4" value="仓库4"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="物品数量" prop="number">
            <el-input v-model="form.number"></el-input>
          </el-form-item>
        <el-form-item label="物品描述" prop="good_describe">
          <el-input v-model="form.good_describe"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer"  v-show="addDialogVisible">
        <el-button @click="addDialogVisible = false;dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="getMethod('addAction')">确定</el-button>
      </span>
      <span slot="footer" class="dialog-footer"  v-show="updateDialogVisible">
        <el-button @click="updateDialogVisible = false;dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="getMethod('updateAction')">确定</el-button>
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
        :total="pageNumber">
      </el-pagination>
    </div>
    
  </div>
</template>

<script>
import { goodRules, warehouseList, goodForm, selectAllGood, goodGroup, select} from "@/js/good.js";
import { getEasyMethod } from "@/utils/common.js";
export default {
    data() {
        return {
            search: {
              name:'',
              warehouse:''
            },
            tableData: [],
            warehouses: warehouseList(),
            dialogVisible: false,
            addDialogVisible: false,
            updateDialogVisible: false,
            form: goodForm(),
            rules: '',
            pageSet: {
              pageNumber: 0,
              pageSize: 30,
              pageTotal: 0,
            }
        }
    },
    created() {
      this.rules = goodRules(this.form);
      this.getMethod('select');
    },
    methods: {
      getMethod(type, row){
        var group = goodGroup();
        getEasyMethod(this, type, row, group.methodGroup, group.msgGroup);
      }
    },

    mounted: {
    },
}
</script>

<style>

</style>