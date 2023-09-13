export const loginRule = function() {
    return {
        userName: [
            { required: true, message: '请输入用户姓名', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在2至10个字符之间', trigger: 'blur' },
        ],

        userPassword: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 10, message: '长度在6至15个字符之间', trigger: 'blur' }
        ],

        userconfirmedPassword: [
            // { validator: validatePass2, trigger: 'blur' }
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
    }
}

export const loginForm = function() {
    return {
        userName:'',
        userPassword:'',
        confirmedPassword:'',
        userAccount:'',
        userPhone:'',
        userAge:'',
        userSex:'',
        userAddress:'',
    }
}

import request from '../utils/request';

export const managerLogin = query=>{
    return request({
        url:"adminLogin",
        method:'get',
        params:query
    });
};

export const userLogin = query=>{
    return request({
        url:"userLogin",
        method:'get',
        params:query
    });
};