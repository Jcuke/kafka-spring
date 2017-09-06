package vn.com.nguacon.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.com.nguacon.kafka.producer.Producer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangjw on 2017/9/5 0005.
 */
@RestController
@EnableAutoConfiguration
public class MessageController {

    //@Autowired
    //private MessageChannel toKafka;
    //@Autowired
    //public MessageController(@Qualifier("toKafka") MessageChannel toKafka) {
    //    this.toKafka = toKafka;
    //}

    @Autowired
    Producer producer;

    public void send(String message) {
        //Message<?> content = new GenericMessage<String>(message);
        producer.send(message);
    }

    @RequestMapping(value = "/put/{msg}", method = RequestMethod.GET)
    @ResponseBody
    public Object put(@PathVariable String msg, HttpServletRequest request){
        Map map = new HashMap();
        map.put("a", 23);
        System.out.println(msg  );
        send(msg + "------" + System.nanoTime()+"纳秒   " +System.currentTimeMillis()  +"毫秒");
        //System.out.println(request);
        //Producer producer = context.getBean("kafkaProducer", Producer.class);

        //producer.send(msg+"================\n");
        return map;
    }
}
