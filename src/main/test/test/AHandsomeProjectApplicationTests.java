package test;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonbTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import static org.aspectj.lang.reflect.DeclareAnnotation.Kind.Method;


@SpringBootTest()
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class AHandsomeProjectApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    MockMvc mockMvc;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @BeforeEach
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    private static final Log log = LogFactory.get();
    String str = "[{\"name\":\"张三\",\"age\":\"1\"},{\"name\":\"李四\",\"age\":\"4\"}]";

    @Test
    void contextLoads() throws Exception {
        BigDecimal bigDecimal = new BigDecimal("1.23");
        log.info("bigDecimal:{}", bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP));


//        List<test> list = JSON.parseArray(str, test.class);
//        List<test> tests = JSON.parseArray(str, test.getyourclass(list));
        StringUtils.isNoneEmpty();
//读取一个json文件
        File file = new File("src/main/test/test/json");
        String json = new String();
        try {
            String s = FileUtils.readFileToString(file, "utf-8");
            json = s;
            test parseObject = JSON.parseObject(s, test.class);
            //反射获取对象字段
            Field[] testfiels = parseObject.getClass().getDeclaredFields();
            log.info("读取的文件内容为：" + JSON.toJSONString(testfiels));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//			//执行一个post请求
//            MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/test/test").accept(MediaType.APPLICATION_JSON).content(json).contentType(MediaType.APPLICATION_JSON);
//            ResultActions perform = mockMvc.perform(mockHttpServletRequestBuilder);
//            perform.andDo(MockMvcResultHandlers.print());
//        } catch (Exception e) {
//			e.printStackTrace();
//		}

    }

    @Test
    @Transactional
    void test() {
        try {
            Class<?> aClass = Class.forName("test.AOP.AOPIMP.TESTAOP");
            log.info("读取的文件内容为：" + JSON.toJSONString(aClass.getName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //修改final修饰的变量
    @Test
    void testTransactional() {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        try {
            String s = new String("abc");
            transactionManager.getTransaction(defaultTransactionDefinition);
            Class aClass = String.class;
            Field values = aClass.getDeclaredField("value");
            int valuemodifiers = values.getModifiers();
            Field moedifiers = Field.class.getDeclaredField("modifiers");
            moedifiers.setAccessible(true);
            moedifiers.setInt(values, valuemodifiers & ~Modifier.FINAL);
            values.setAccessible(true);
            char[] chars = new char[1];
            chars[0] = 'a';
            String s1 = new String("b");
            BeanUtils.copyProperties(s1, s);
            log.info("moedifiers：" + JSON.toJSONString(moedifiers));
            log.info("打印内容：" + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试线程变量的传递
    @Test
    void testThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("thread:{}", Thread.currentThread().getName());
            }
        });
        thread.start();
    }

    //动态代理
    @Test
    void testDynamicProxy() {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("test.AOP.AOPIMP.TESTAOP");
        } catch (ClassNotFoundException e) {

        }
    }

    //有道翻译api测试
    @Test
    void testYoudao()  throws Exception {
        String string = new String("测试");
        String path = "https://fanyi.youdao.com/translate?&i=";
        //请求url
        String url = path + string+"&doctype=json";
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpget对象
        HttpGet httpGet = new HttpGet(url);
        //执行请求
        try {
            CloseableHttpResponse execute = httpClient.execute(httpGet);
                //获取响应结果
            String result = EntityUtils.toString(execute.getEntity(), "utf-8");
            //解析json
            Object jsonObject = JSON.parseObject(result);
            //获取translateResult翻译结果
            Object translateResult = ((JSONObject) jsonObject).get("translateResult");
            //获取第一个翻译结果
            Object object = ((JSONArray) translateResult).get(0);
            //获取翻译结果
            Object o = ((JSONArray) object).get(0);
            //获取翻译结果
            Object target = ((JSONObject) o).get("tgt");
            //获取翻译结果的内容
            log.info("翻译结果：" + JSON.toJSONString(target));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Test
    void google() throws Exception {
        String string = new String("a text ");
        String path = "http://translate.google.cn/translate_a/single?client=gtx&dt=t&dj=1&ie=UTF-8&sl=auto&tl=zh_TW&q=calculate";
        //请求url
        String url = path + string+"&doctype=xml";
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpget对象
        HttpGet httpGet = new HttpGet(url);

        //执行请求
        try {
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            //获取响应结果
            String result = EntityUtils.toString(execute.getEntity(), "utf-8");
            //解析xml
            Document parse = DocumentHelper.parseText(result);
            //获取translation标签
            Element rootElement = parse.getRootElement();
            //获取翻译结果
            Element translation = rootElement.element("translation");
            //获取翻译结果的内容
            String text = translation.getText().trim();
            log.info("翻译结果：" + text);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    void TestMap(){
        HashMap<String, String> hashMap = new HashMap<>();
        Object o = new Object();
        hashMap.put("a","a");
        hashMap.put("a","b");
        System.out.println(hashMap.get("a"));
    }
}
