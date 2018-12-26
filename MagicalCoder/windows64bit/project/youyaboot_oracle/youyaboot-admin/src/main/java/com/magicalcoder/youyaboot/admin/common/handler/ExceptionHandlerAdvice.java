package com.magicalcoder.youyaboot.admin.common.handler;

import com.magicalcoder.youyaboot.core.common.exception.BusinessException;
import com.magicalcoder.youyaboot.core.serialize.CommonReturnCode;
import com.magicalcoder.youyaboot.core.serialize.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
    @Value("${spring.profiles.active}")
    private String active;

    /**
     * 获取异常的堆栈信息
     *
     * @param t
     * @return
     */
    private static String getStackTrace(Throwable t)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try
        {
            t.printStackTrace(pw);
            return sw.toString();
        }
        finally
        {
            pw.close();
        }
    }

    @ExceptionHandler
    public ResponseMsg handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        if(e instanceof BusinessException) {
            logger.error("BusinessException：" + e.getMessage(), e);
            return new ResponseMsg(((BusinessException) e).getErrorCode(),((BusinessException) e).getDesc());
        }else {
            logger.error("Exception：" + e.getMessage(), e);
            return new ResponseMsg(CommonReturnCode.EXCEPTION);
        }
    }
}