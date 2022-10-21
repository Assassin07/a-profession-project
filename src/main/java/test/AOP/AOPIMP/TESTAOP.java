package test.AOP.AOPIMP;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Aspect
@Component
public class TESTAOP {
    private static final Log log = LogFactory.get();

    private static List<String> list = new ArrayList<String>();
    @Around("@annotation(test.AOP.TESTAOP)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
    log.info("TESTAOP");
        System.out.println("TESTAOP");
        Object o = new Object();
        o=pjp;
        String s = JSON.toJSONString(o);
        String s1 = JSON.toJSONString(pjp.proceed());
        log.info(s);
        log.info(s1);
        return pjp.proceed();
    }
}
