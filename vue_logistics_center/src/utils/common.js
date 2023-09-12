export const handleRes =  function(object, successMeg, successAction){
    return res=>{
        if(res.data.status==200){
            object.$message.success(successMeg);
            successAction();
        }else{
            object.$message.error(res.data.msg);
        }
    }
}

export const handleDel = function(object, delMsg, delAction){
    object.$confirm(delMsg, '提示', {type: 'warning'})
    .then(delAction).catch(()=>{});
}

export const handleAddButton = function(object){
    object.dialogVisible = true;
    object.addDialogVisible = true;
    object.updateDialogVisible = false;
    object.$nextTick(() => {
        object.$refs.form.resetFields();
    })
}

export const handleUpdateButton = function(object, row){
    object.dialogVisible = true;
    object.updateDialogVisible = true;
    object.addDialogVisible = false;
    object.$nextTick(() => {
        object.$refs.form.resetFields();
        for (var key of Object.keys(row)) {
            object.form[key] = row[key];
        }
    })
}

export const handleReset = function(object){
  for (var key of Object.keys(object.search)) {
    object.search[key] = "";
}
}

export const getEasyMethod = function(object, type, row, methodGroup, msgGroup){
    {
        var select = function(){
            methodGroup.select(object.pageSet).then(res=>{
                object.tableData = res.data.list;
                console.log(object.tableData);
            })
        }
        if(type==='select'){
          return select();
        }else if(type==="addButton"){
          return handleAddButton(object);
        }else if(type==='updateButton'){
          return handleUpdateButton(object,row);
        }else if(type==='delete'){
          return handleDel(object, msgGroup.deleteConfirm, ()=>{
            methodGroup.delete(row).then(handleRes(object,msgGroup.deleteSuccess,select))
          })
        }else if(type==='addAction'){
          return methodGroup.add(object.form).then(handleRes(object, msgGroup.addSuccess,select));
        }else if(type==='updateAction'){
          return methodGroup.update(object.form).then(handleRes(object,msgGroup.updateSuccess, select));
        }else if(type==='resetButton'){
          return handleReset(object)
        }
      }
}