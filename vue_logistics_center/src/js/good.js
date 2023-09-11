export const goodList = function() {
    return [{
        goodId:'16548kda',
        goodName:'冰箱',
        location:'仓库1',
        number:'10',
        description: '普通的冰箱'
      }]
}

export const goodRules = function() {
  return {
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

export const locationList = function() {
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