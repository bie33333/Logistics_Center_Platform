import request from '../utils/request';

export const managerLogin = query=>{
    return request({
        url:"userLogin",
        method:'get',
        params:query
    });
};