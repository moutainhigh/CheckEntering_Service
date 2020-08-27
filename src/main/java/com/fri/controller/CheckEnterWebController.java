package com.fri.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fri.common.Result;
import com.fri.model.PoliceLoginRecord;
import com.fri.pojo.bo.pinen.VerifyIDCardRequest;
import com.fri.pojo.bo.pinen.VerifyImageRequest;
import com.fri.pojo.bo.pinen.VerifyOcrRequest;
import com.fri.pojo.bo.xicheng.response.CheckPersonBasicInfoResponse;
import com.fri.pojo.bo.xicheng.response.CheckPersonFaceResponse;
import com.fri.pojo.bo.xicheng.response.CheckPersonPhotoResponse;
import com.fri.service.CheckEnterService;
import com.fri.service.XiChengService;
import com.fri.utils.UserUtil;
import com.fri.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/check")
public class CheckEnterWebController {
    public static Logger log = LoggerFactory.getLogger(CheckEnterWebController.class);
    @Autowired
    CheckEnterService checkEnterService;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    XiChengService xiChengService;
    /**
     * 人证核验+手工输入
     */
    @PostMapping("/idcard")
    public String verifyIDCard(@Valid @RequestBody VerifyIDCardRequest request) {
        //设备号未绑定报错
        if (UserUtil.getUserMap().get(request.getDeviceNo()) == null) {
            return ResponseUtil.fail("1", "核录桩未绑定");
        }
        log.info("核录桩传送身份证信息接收:{},{}", request.toString(), LocalDateTime.now());

        Map map = checkEnterService.verifyIDCard(request);
        log.info("核录桩传送身份证信息推送完毕:{}", LocalDateTime.now());
        if (map == null || map.isEmpty()) {
            return ResponseUtil.fail();
        }
        return ResponseUtil.ok(map);
    }

    //TODO 人像相似列表请求 接口有问题
    @PostMapping("/face")
    public String verifyImage(@Valid @RequestBody VerifyImageRequest request) {

        //设备号未绑定报错
        if (UserUtil.getUserMap().get(request.getDeviceNo()) == null) {
            return ResponseUtil.fail("1", "核录桩未绑定");
        }
        log.info("核录桩传送身份证信息接收:{},{}", request.toString(), LocalDateTime.now());
        Map map = checkEnterService.verifyFacePhoto(request);
        if (map == null || map.isEmpty()) {
            return ResponseUtil.fail();
        }
        log.info("核录桩传送身份证信息:{},{}", request.toString(), LocalDateTime.now());
        return ResponseUtil.ok();
    }

    //ocr证件查询
    @PostMapping("/ocr")
    public String verifyOcr(@Valid @RequestBody VerifyOcrRequest request) {
        //设备号未绑定报错
        if (UserUtil.getUserMap().get(request.getDeviceNo()) == null) {
            return ResponseUtil.fail("1", "核录桩未绑定");
        }
        log.info("核录桩传送OCR证件信息接收:{},{}", request.toString(), LocalDateTime.now());
        checkEnterService.verifyOcr(request);
        log.info("核录桩传送OCR证件信息推送完毕:{}", LocalDateTime.now());
        return ResponseUtil.ok();
    }

    public static void main(String[] args) {
        String s = "{\n" +
                "    \"status\": 0,\n" +
                "    \"msg\": \"OK\",\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"similaritydegree\": \"0.98391753\",\n" +
                "            \"idnumber\": \"14012891225116111X\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"similaritydegree\": \"0.9025098681\",\n" +
                "            \"idnumber\": \"210188888840050\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"similaritydegree\": \"0.9018577337\",\n" +
                "            \"idnumber\": \"888888888888888888\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"similaritydegree\": \"0.8954774737\",\n" +
                "            \"idnumber\": \"14223323233011\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"similaritydegree\": \"0.8945494294\",\n" +
                "            \"idnumber\": \"身份证号码5\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Map returnMap = JSON.parseObject(s, Map.class);
        List<JSONObject> checkPersonFaceResponses = (List) returnMap.get("results");
        for (JSONObject checkPersonFaceResponseb : checkPersonFaceResponses) {
            CheckPersonFaceResponse checkPersonFaceResponse = JSON.parseObject(checkPersonFaceResponseb.toString(), CheckPersonFaceResponse.class);
            System.out.println(checkPersonFaceResponse.getIdnumber());
        }
    }

}