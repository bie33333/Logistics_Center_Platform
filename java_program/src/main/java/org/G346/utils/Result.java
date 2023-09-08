package org.G346.utils;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Result implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 响应业务状态
     */

    private Integer status;

    /**
     * 响应消息
      */

    private String msg;

    /**
     * 响应中的数据
      */

    private Object data;

    /**
     * 构建其他状态的CinemaResult对象，直接用静态方法，不需要额外创建对象了
     */

    public static Result build(Integer status, String msg, Object data) {
        return new Result(status, msg, data);
    }

    public static Result build(Integer status, String msg) {
        return new Result(status, msg, null);
    }

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result ok() {
        return new Result(null);
    }

    public static Result error(int status, String msg) {
        return new Result(status,msg, null);
    }

    public static Result error(int status, String msg, Object data) {
        return new Result(status,msg, data);
    }

    public Result(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Result(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
}
