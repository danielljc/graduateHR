package edu.nju.master.graduate.utils;


import edu.nju.master.graduate.dto.ResponseDto;
import edu.nju.master.graduate.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResultUtil {
    public final static String CODE = "code";
    public final static String MSG = "msg";
    public final static String DATA = "data";
    private static Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    /**
     * 封装只有结果的返回数据。
     *
     * @return
     */
    public static ResponseDto getResult() {
        ResponseDto result = new ResponseDto();
        result.setCode("OK");
        result.setMsg("success");
        return result;
    }

    /**
     * 封装包含信息的返回数据
     *
     * @param msg
     * @return
     */
    public static ResponseDto getResult(String msg) {
        ResponseDto result = new ResponseDto();
        result.setMsg(msg);
        return result;
    }

    /**
     * 封装包含内容的返回数据。
     *
     * @return
     */
    public static ResponseDto getResult(Object obj) {
        ResponseDto result = getResult();
        result.setData(obj);
        return result;
    }

    public static ResponseDto getResultEx(Exception e) {
        logger.error(e.getMessage());
        ResponseDto result = new ResponseDto();
        result.setCode("ERROR");
        if (e instanceof BusinessException) {
            BusinessException be = (BusinessException) e;
            result.setMsg(be.getMessage());
            logger.error(be.getMessage(), be);
        } else {
            result.setMsg(e.getMessage());
            logger.error("系统错误", e);
        }
        return result;
    }
}
