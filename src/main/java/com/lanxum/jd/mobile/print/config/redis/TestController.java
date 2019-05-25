package com.lanxum.jd.mobile.print.config.redis;

import com.alibaba.fastjson.JSON;
import com.lanxum.jd.mobile.print.common.base.controller.BaseController;
import com.lanxum.jd.mobile.print.common.constant.Constant;
import com.lanxum.jd.mobile.print.common.utils.DateUtils;
import com.lanxum.jd.mobile.print.common.web.ResponseMap;
import com.lanxum.jd.mobile.print.modules.busmobprintstatus.entity.BusMobprintStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author JiangJunpeng
 * @date 2019/4/29
 */
@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    private RedisService redisService;

    @RequestMapping("/save")
    public ResponseEntity<Map<String, Object>> cancelPrinting() {

        BusMobprintStatus busMobprintStatus = new BusMobprintStatus();
        busMobprintStatus.setTaskId("12345");
        busMobprintStatus.setPrintStatus("2");
        busMobprintStatus.setLog("success loading");
        busMobprintStatus.setCreateTime(DateUtils.getNow());
        BusMobprintStatus busMobprintStatus2 = new BusMobprintStatus();
        busMobprintStatus2.setTaskId("123452222");
        busMobprintStatus2.setPrintStatus("22222");
        busMobprintStatus2.setLog("success loading2222");
        busMobprintStatus2.setCreateTime(DateUtils.getNow());

        List<BusMobprintStatus> list = new ArrayList<>();
        list.add(busMobprintStatus);
        list.add(busMobprintStatus2);

        System.out.println(JSON.toJSONString(list));

        String str = JSON.toJSONString(list);

        Jedis jedis = new Jedis("localhost");
        String res = jedis.set("busmob", str);

        if(!"".equals(res)){
            List<BusMobprintStatus> busMobprintStatusList = JSON.parseArray(jedis.get("busmob"), BusMobprintStatus.class);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.SUCCESS_STATE, "success save data", busMobprintStatusList).getResponseMap());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMap(Constant.PARAMETE_ERROR, "failed save data", "").getResponseMap());
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.set("test", "测试中文");
        System.out.println(jedis.get("test"));
    }
}
