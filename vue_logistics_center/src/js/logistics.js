export const logisticList = function() {
    return [{
        orderId:'123test',
        userName:'A',
        userPhone:'12345678901',
        goodName:'GoodA',
        number:'15',
        addressee:'B',
        addresseePhone:'98745612300',
        address:'陕西省西安市',
        carName:'测试车辆1',
        description: '测试物流信息'
      }]
}

export const logisticRule = function() {
    return {
        orderId: [
          { required: true, message: '请输入订单编号', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在2至10个字符之间', trigger: 'blur' },
          // { validator: checkDuplicate, trigger: 'blur' }
        ],

        userName: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 6, max: 10, message: '长度在2至15个字符之间', trigger: 'blur' }
        ],

        userPhone: [
                { required: true, message: '请输入联系电话', trigger: 'blur' },
                { min: 11, max: 11, message: '长度为11位', trigger: 'blur' },
        ],

        goodName: [
          { required: true, message: '请输入物品名称', trigger: 'blur' },
        ],

        number: [
          { required: true, message: '请输入物品数量', trigger: 'blur' },
        ],

        addressee: [
          { required: true, message: '请输入收件人', trigger: 'blur' },
        ],

        addresseePhone: [
                { required: true, message: '请输入收件电话', trigger: 'blur' },
                { min: 11, max: 11, message: '长度为11位', trigger: 'blur' },
        ],

        address: [
          { required: true, message: '请输入收件地址', trigger: 'blur' },
        ],

        carName: [
          { required: true, message: '请输入车辆名称', trigger: 'blur' },
        ],

        description: [
          { required: true, message: '请输入物流描述', trigger: 'blur' },
        ],
  }
}

export const logisticForm = function() {
    return  {
        orderId:'',
        userName:'',
        userPhone:'',
        goodName:'',
        number:'',
        addressee:'',
        addresseePhone:'',
        address:'',
        carName:'',
        description:''
    }
}

import request from '../utils/request';

export const logisticGroup = function() {
  return {
      methodGroup: {
          add: query=>{return request({url:"insertOrder", method:'post', params:query})},
          delete: query=>{return request({url:"deleteOrder",method:'post',params:query})},
          update: query=>{return request({url:"updateOrder",method:'post',params:query})},
          select: query=>{return request({url:"selectOrder",method:'get',params:query})}
      },
      msgGroup: {
          deleteConfirm:  '确定要删除此车辆吗？', 
          deleteSuccess: "删除成功", 
          addSuccess: "增加车辆成功",
          updateSuccess: "修改成功",
        }
  }
}