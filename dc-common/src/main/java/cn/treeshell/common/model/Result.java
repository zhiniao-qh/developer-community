package cn.treeshell.common.model;

import lombok.Data;

/**
 * 统一返回对象
 * @Author: panjing
 * @Date: 2020/3/16 22:14
 */
@Data
public class Result {

    private Boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(Boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

}