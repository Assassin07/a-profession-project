package test.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequestMapping("/test")
public class Testone {

//建一个post测试接口
    @GetMapping("/test")
    public String test( testtwo object  ) {
        Log log = LogFactory.get();
        //获取object的json字符串
        log.info("获取object的json字符串：" + JSON.toJSONString(object));
        return "这是一个测试接口";
    }


}
