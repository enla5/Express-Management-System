package com.yg.cn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class CodeService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    public String sendCode(String phone){
        Random random=new Random();
        String code="";
        for (int i=0;i<6;i++){
            code+=random.nextInt(10);
        }
        //将验证码存入redis
        String codeKey="codeKey"+phone;
        //存入验证码 存活1天
        redisTemplate.opsForValue().set(codeKey,code,60, TimeUnit.DAYS);
        return code;
    }

    /**
     * 校验验证码
     * @param code
     * @param phone
     * @return
     */
    public String checkCode(String code,String phone){
        //从redis取出验证码
        String codeKey="codeKey"+phone;
        String codeRedis = String.valueOf(redisTemplate.opsForValue().get(codeKey));
        if (codeRedis==null){
            return "验证码已失效！";
        }else if (codeRedis.equalsIgnoreCase(code)){
            return "1";
        }else{
            return "验证码错误！";
        }
    }
}
