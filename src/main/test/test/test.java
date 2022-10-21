package test;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
@Data
@Component
public class test{
//    String name;
//    String age;
//    T test1;
//    private static final Log log = LogFactory.get();
//    @TESTAOP
//    void test() {
//        System.out.println("src/main/test");
//    }
//    public static <t> Class getyourclass(List<t> list){
//        Class<t> aClass = (Class<t>) list.get(0).getClass();
//        return aClass;
//    }
//    @Test
//    void testString(){
//        String s = new String("123");
//        String s2 = new String("123");
//        BigDecimal a = new BigDecimal("1.23");
//        BigDecimal b = new BigDecimal("1.2");
//        //新建一个list
//        List<String> list = new ArrayList<>();
//        List<BigDecimal> arrayLists = new ArrayList<>();
//        arrayLists.add(a);
//        BigDecimal bigDecimal = arrayLists.stream().filter(x -> x.compareTo(b) > 0).findFirst().get();
//        System.out.println(bigDecimal);
//    }
//    @Test
//    @Async
//    void threadlocaltest(){
////测试threadlocal传递线程变量
//        HashMap<String, String> stringStringHashMap = new HashMap<>();
//        stringStringHashMap.put("abc","def");
//        ThreadLocal<Map<String, String>> threadLocal = new InheritableThreadLocal<>();
//        threadLocal.set(stringStringHashMap);
//        threadLocal.get();
//        new Thread(() -> {
//            //打印线程名
//            log.info("threadLocalname：" + Thread.currentThread().getName());
//            log.info("threadLocal：" + (threadLocal.get().get("abc")));
//        }).start();
//    }
//    @Test
//    void testTOString(){
//        String s = new String("SP20220806026 SP20220806024  SP20220806023  SP20220806022  SP20220806021 " +
//                "SP20220806012  SP20220806011  SP20220806010  SP20220806009  SP20220806008  SP20220806007");
//         s="123";
//        System.out.println(s);
//
//    }
    public static void main(String[] args) {
        ArrayList<animal> animals = new ArrayList<>();

        animals.add(new animal("1"));
        animals.add(new animal("2"));
        List<animal> collect = animals.stream().filter(x -> x.getName() == "1").collect(Collectors.toList());
        animal animal = collect.get(0);
        animal.setName("3");
        animal bigDecimal = new animal();
        animals.forEach(x -> {
             //bigDecimal = bigDecimal.add(new BigDecimal("1"));

        });
        System.out.println(bigDecimal);
    }
}
