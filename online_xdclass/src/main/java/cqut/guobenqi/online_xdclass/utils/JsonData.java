package cqut.guobenqi.online_xdclass.utils;

public class JsonData {
    //状态码
    private Integer code;

    private Object data;

    private String msg;

    public JsonData() {
    }

    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    //成功不用返回数据
    public static JsonData buildSuccess(){
        return new JsonData(0,null,null);
    }
    //成功返回数据
    public static JsonData buildSuccess(Object data){
        return new JsonData(0,data,null);
    }
    //s失败
    public static  JsonData buildError(String msg){
        return new JsonData(-1,null,msg);
    }
    public static  JsonData buildError(Integer code,String msg){
        return new JsonData(code,null,msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
