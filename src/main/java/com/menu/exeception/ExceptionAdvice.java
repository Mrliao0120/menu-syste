package com.menu.exeception;



import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultData errorHandler(Exception ex) {
        ResultData resultData=new ResultData();
        resultData.setCode(9999);
        resultData.setMsg("系统发生未知异常");
        log.error(getMessage(ex));
        return resultData;
    }

    /**
     * 自定义异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ServletException.class)
    public ResultData customizeHandlerException(ServletException ex) {
        ResultData resultData=new ResultData();
        resultData.setCode(ex.getCode());
        resultData.setMsg(ex.getMsg());
        log.error(getMessage(ex));
        return resultData;
    }



    private   String getMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
