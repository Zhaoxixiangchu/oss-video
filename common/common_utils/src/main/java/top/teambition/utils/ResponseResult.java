package top.teambition.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liupengyu
 * @version 2021年07月03日 15:38
 */
@Data
public class ResponseResult {

    private ResponseResult(){

    }

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "返回的信息")
    private String message;

    @ApiModelProperty(value = "返回的数据")
    private Map<String, Object> data = new HashMap<>();

    /*提供方法*/
    public static ResponseResult ok(){
        ResponseResult result = new ResponseResult();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }

    public static ResponseResult error(){
        ResponseResult result = new ResponseResult();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("失败");
        return result;
    }

    public ResponseResult success(boolean success){
        this.success = success;
        return this;
    }

    public ResponseResult message(String message){
        this.message = message;
        return this;
    }

    public ResponseResult code(Integer code){
        this.code = code;
        return this;
    }

    public ResponseResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ResponseResult data(Map<String, Object> map){
        this.data = map;
        return this;
    }

}
