export const goodList = function() {
    return [{
        goodId:'16548kda',
        goodName:'冰箱',
        warehouse:'仓库1',
        number:'10',
        description: '普通的冰箱'
      }]
}

export const goodRules = function(form) {
  return {
    goodId: [
        { required: true, message: '请输入物品编号', trigger: 'blur' },
        { min: 2, max: 10, message: '长度在3至10个字符之间', trigger: 'blur' },
    ],

    goodName: [
        { required: true, message: '请输入物品名称', trigger: 'blur' },
    ],

    warehouse:[
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

export const warehouseList = function() {
  return [
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
  ]
}

export const goodForm = function() {
    return {
        id:'',
        name:'',
        warehouse:'',
        number:0,
        good_describe:''
    }
}

import request from '../utils/request';

export const goodGroup = function(selectMethod) {
  return {
      methodGroup: {
          add: query=>{return request({url:"insertGood",method:'post',params:query})},
          delete: query=>{return request({url:"deleteGood",method:'post',params:query})},
          update: query=>{return request({url:"updateGood",method:'post',params:query})},
          select: selectMethod
      },
      msgGroup: {
          deleteConfirm:  '确定要删除此物品吗？', 
          deleteSuccess: "删除成功", 
          addSuccess: "增加物品成功",
          updateSuccess: "修改成功",
        }
  }
}

export const selectAllGood = query=>{return request({url:"selectGood",method:'get',params:query})};