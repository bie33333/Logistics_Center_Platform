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