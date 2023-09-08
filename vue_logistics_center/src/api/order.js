import request from '../utils/request';

export const selectAllOrder = query=>{
    return request({
        url:"selectAllOrder",
        method:'get',
        params:query
    });
};

export const addOrder = query=>{
    return request({
        url:"addOrder",
        method:'post',
        params:query
    });
};

export const modifyOrder = query=>{
    return request({
        url:"modifyOrder",
        method:'post',
        params:query
    });
};

export const deleteOrder = query=>{
    return request({
        url:"deleteOrder",
        method:'post',
        params:query
    });
};