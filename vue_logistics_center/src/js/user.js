export const dataUser = function(){
    return {
        account: 'unKnow',
        name: 'unKnow',
        age: 20,
        phone: "18100000000",
        gender: 1,
        address: ''
    }
};

export const UserList = function() {
    return [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }];
}

export const dataForm = function() {
    return {
        userName:'',
        userPassword:'',
        userConfirmedPassword:'',
        userAccount:'',
        userPhone:'',
        userAge:'',
        userSex:'',
        userAddress:'',
    };
}

const getValidatePass = function(form){
    return (rule, value, callback) => {
        if (value === '') {
        callback(new Error('请确认密码'));
        } else if (value !== form.userPassword) {
        callback(new Error('两次输入的密码不一致!'));
        } else {
        callback();
        }
    };
}

export const dataRule = function(thing) {
    return {
        userName: [
            { required: true, message: '请输入用户姓名', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在2至10个字符之间', trigger: 'blur' },
        ],

        userPassword: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 10, message: '长度在6至15个字符之间', trigger: 'blur' }
        ],

        userConfirmedPassword: [
            { validator: getValidatePass(thing), trigger: 'blur' }
            // { validator: thing, trigger: 'blur' }
        ],

        userAccount: [
            { required: true, message: '请输入账户', trigger: 'blur' },
            { min: 3, max: 10, message: '长度在3至10个字符之间', trigger: 'blur' },
            // { validator: checkDuplicate, trigger: 'blur' }
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
    };
}

import request from '../utils/request';

export const userGroup = function() {
    return {
        methodGroup: {
            add: query=>{return request({url:"insertUser", method:'post', params:query})},
            delete: query=>{return request({url:"deleteUser",method:'post',params:query})},
            update: query=>{return request({url:"updateUser",method:'post',params:query})},
            select: query=>{return request({url:"selectUser",method:'get',params:query})},
            lookup: query=>{return request({url:"lookupUser",method:'get',params:query})},
        },
        msgGroup: {
            deleteConfirm:  '确定要删除此用户吗？', 
            deleteSuccess: "删除成功", 
            addSuccess: "增加车辆成功",
            updateSuccess: "修改成功",
          }
    }
}

export const selectAll = query => {return request({url: "selectAll",method:'get',params: query,})}

export const selectAllUser = query=>{return request({url:"selectUser",method:'get',params:query})};

export const addUser = query=>{return request({url:"insertUser", method:'post', params:query})};

export const updateUser = query=>{return request({url:"updateUser", method:'post', params:query})};

export const deleteUser = query=>{return request({url:"deleteUser", method:'post', params:query})};