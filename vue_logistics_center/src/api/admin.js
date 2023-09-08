import request from '../utils/request';

export const selectAllAdmin = query=>{
    return request({
        url:"selectAllAdmin",
        method:'get',
        params:query
    });
};

export const addAdmin = query=>{
    return request({
        url:"addAdmin",
        method:'post',
        params:query
    });
};

export const modifyAdmin = query=>{
    return request({
        url:"modifyAdmin",
        method:'post',
        params:query
    });
};

export const deleteAdmin = query=>{
    return request({
        url:"deleteAdmin",
        method:'post',
        params:query
    });
};