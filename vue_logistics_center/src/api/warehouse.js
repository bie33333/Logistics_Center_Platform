import request from '../utils/request';

export const selectAllWarehouse = query=>{
    return request({
        url:"selectAllWarehouse",
        method:'get',
        params:query
    });
};

export const addWarehouse = query=>{
    return request({
        url:"addWarehouse",
        method:'post',
        params:query
    });
};

export const modifyWarehouse = query=>{
    return request({
        url:"modifyWarehouse",
        method:'post',
        params:query
    });
};

export const deleteWarehouse = query=>{
    return request({
        url:"deleteWarehouse",
        method:'post',
        params:query
    });
};