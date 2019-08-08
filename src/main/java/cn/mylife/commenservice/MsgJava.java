package cn.mylife.commenservice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangjie
 * 2018/6/10 11:22
 */
public class MsgJava {

    /**
     * 成功返回的方法
     */
    public static MsgJava success(){
        MsgJava msgJava = new MsgJava();
        msgJava.setCode(100);
        msgJava.setMsg("处理成功");
        return msgJava;
    }

    /**
     * 失败返回的方法
     */
    public static MsgJava fail(){
        MsgJava msgJava = new MsgJava();
        msgJava.setCode(200);
        msgJava.setMsg("处理失败");
        return msgJava;
    }

    /**
     * 定义链式操作
     */
    public MsgJava add(String key, Object value){
        this.getExtend().put(key,value);
        return this;
    }

    /**
     * 100表示成功  200失败
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回给用户的json
     */
    private Map<String,Object> extend = new HashMap<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
