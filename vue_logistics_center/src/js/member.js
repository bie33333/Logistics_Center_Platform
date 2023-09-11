export const memberList = function() {
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

export const dataRule = function() {
    return {
        name: [
            { required: true, message: '请输入用户姓名', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在2至10个字符之间', trigger: 'blur' },
        ],

        password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 10, message: '长度在6至15个字符之间', trigger: 'blur' }
        ],

        confirmedPassword: [
            { validator: validatePass2, trigger: 'blur' }
        ],

        account: [
            { required: true, message: '请输入账户', trigger: 'blur' },
            { min: 3, max: 10, message: '长度在3至10个字符之间', trigger: 'blur' },
            { validator: checkDuplicate, trigger: 'blur' }
        ],

        phone: [
            { required: true, message: '请输入电话', trigger: 'blur' },
            { min: 11, max: 11, message: '长度为11位', trigger: 'blur' },
        ],

        age: [
            { required: true, message: '请输入年龄', trigger: 'blur' },
        ],

        sex:[
        { required: true, message: '请选择性别', trigger: 'change' },
        ],

        address: [
            { required: true, message: '请输入住址', trigger: 'blur' },
        ],
    }
}

export const dataForm = function() {
    return {
        name:'',
        password:'',
        confirmedPassword:'',
        account:'',
        phone:'',
        age:'',
        sex:'',
        address:'',
    }
}