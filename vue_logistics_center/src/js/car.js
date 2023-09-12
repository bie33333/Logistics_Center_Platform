export const statusList = function() {
    return [
        {
          value:'待出车',
          label:'待出车'
        },
        {
          value:'出车中',
          label:'出车中'
        },
      ]
}

export const carRules = function() {
    return {
        id: [
            { required: true, message: '请输入车辆编号', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在3至10个字符之间', trigger: 'blur' },
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

export const carForm = function() {
    return {
        id:'',
        name:'',
        status:'',
        describe:''
    }
}

import request from '../utils/request';

export const carGroup = function() {
    return {
        methodGroup: {
            add: query=>{return request({url:"insertCar",method:'post',params:query})},
            delete: query=>{return request({url:"deleteCar",method:'post',params:query})},
            update: query=>{return request({url:"updateCar",method:'post',params:query})},
            select: query=>{return request({url:"selectCar",method:'get',params:query})}
        },
        msgGroup: {
            deleteConfirm:  '确定要删除此车辆吗？', 
            deleteSuccess: "删除成功", 
            addSuccess: "增加车辆成功",
            updateSuccess: "修改成功",
          }
    }
}