export const carList = function() {
    return [{
        carId:'165askda',
        carName:'测试车辆1',
        state:'待出车',
        description: '测试车辆'
      }]
}

export const stateList = function() {
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
        carId: [
            { required: true, message: '请输入车辆编号', trigger: 'blur' },
            { min: 2, max: 10, message: '长度在3至10个字符之间', trigger: 'blur' },
            { validator: checkDuplicate, trigger: 'blur' }
        ],

        carName: [
            { required: true, message: '请输入车辆名称', trigger: 'blur' },
        ],

        state:[
        { required: true, message: '请选择车辆状态', trigger: 'change' },
        ],

        description: [
            { required: true, message: '请输入车辆描述', trigger: 'blur' },
        ],
    }
}

export const carForm = function() {
    return {
        carId:'',
        carName:'',
        state:'',
        description:''
    }
}

import request from '../utils/request';

export const selectAllCar = query=>{
    return request({
        url:"selectAllCar",
        method:'get',
        params:query
    });
};

export const addCar = query=>{
    return request({
        url:"addCar",
        method:'post',
        params:query
    });
};

export const modifyCar = query=>{
    return request({
        url:"modifyCar",
        method:'post',
        params:query
    });
};

export const deleteCar = query=>{
    return request({
        url:"deleteCar",
        method:'post',
        params:query
    });
};