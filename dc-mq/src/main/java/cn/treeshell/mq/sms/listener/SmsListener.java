package cn.treeshell.mq.sms.listener;

import cn.treeshell.mq.sms.utils.SmsUtil;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听短信验证码
 * @Author: panjing
 * @Date: 2020/4/5 21:26
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    // 模板编号
    @Value("${aliyun.sms.templateCode}")
    private String templateCode;

    // 签名
    @Value("${aliyun.sms.signName}")
    private String signName;

    @RabbitHandler
    public void executeSms(Map<String, String> map) {
        String mobile = map.get("mobile");
        String checkCode = map.get("checkCode");
        String param = "{\"code\":\"" + checkCode + "\"}";
        try {
            smsUtil.sendSms(mobile, templateCode, signName, param);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
