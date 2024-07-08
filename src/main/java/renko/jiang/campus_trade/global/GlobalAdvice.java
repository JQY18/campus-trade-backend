package renko.jiang.campus_trade.global;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import renko.jiang.campus_trade.pojo.result.Result;

@RestControllerAdvice
public class GlobalAdvice {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

}
