package com.gxcy.utls;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全局统一返回结果类
 * @param <T>
 */
@Data
public class Result<T> {
    @ApiModelProperty("是否成功")
    private Boolean success;//是否成功
    @ApiModelProperty("状态码")
    private Integer code;//状态码
    @ApiModelProperty("返回消息")
    private String message;//返回消息
    @ApiModelProperty("data")
    private T data;

    /**
     * 私有化构造方法，禁止在其他类创建对象
     */
    private Result(){

    }

    /**
     * 成功执行，不返回数据
     * @param <T>
     */
    public static <T> Result<T> ok(){
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("执行成功");
        return result;
    }

    /**
     * 执行成功，返回数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data){
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("执行成功");
        result.setData(data);
        return result;
    }

    /**
     * 失败
     * @return
     */
    public static <T> Result<T> error(){
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("执行失败");
        return result;
    }

    /**
     * 设置是否成功
     * @param success
     * @return
     */
    public Result<T> success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    /**
     * 设置状态码
     * @param code
     * @return
     */
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    /**
     * 设置返回消息
     * @param message
     * @return
     */
    public Result<T> message(String message){
        this.setMessage(message);
        return this;
    }

    /**
     * 是否存在
     * @return
     */
    public static<T> Result<T> exist(){
        Result<T> result = new Result<>();
        result.setSuccess(false);//存在该数据
        //由于vue-element-admin模板在响应数据时验证状态码是否是200，如果不是200，则报错
        result.setCode(ResultCode.SUCCESS);//执行成功，但存在该数据
        result.setMessage("该数据存在");
        return result;
    }

}
