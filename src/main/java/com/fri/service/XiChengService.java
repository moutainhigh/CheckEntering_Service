package com.fri.service;

import com.alibaba.fastjson.JSONObject;
import com.fri.exception.NoMessageException;
import com.fri.model.CheckAddress;
import com.fri.model.CheckPersonJs;
import com.fri.model.CheckPersonJsDetail2;
import com.fri.pojo.bo.app.request.CheckPersonJsDetailRequest;
import com.fri.pojo.bo.xicheng.request.*;
import com.fri.pojo.bo.xicheng.response.*;

import java.util.List;
import java.util.Map;

public interface XiChengService {
    //2.请求核查对象历史统计信息
    List<CheckInfoHistoryResponse> checkInfoHistory(CheckInfoHistoryResquest data);

    //5.请求人的基本信息
    CheckPersonBasicInfoResponse checkPersonBasicInfo(String IDCard, String deviceNo) throws NoMessageException;

    //6.请求人的警示信息
    List<CheckPersonJs> checkPersonJs(String IDCard, String deviceNo) ;

    //7.请求人的警示详细信息
    List<CheckPersonJsDetail2> checkPersonJsDetail(CheckPersonJsDetailRequest request) throws NoMessageException;

    //9.请求境外人员基本信息(ocr)
    CheckForeignPersonBasicReponse checkForeignPersonBasicInfo(CheckForeignPersonInfoRequest request) throws NoMessageException;

    //10.请求境外人员的警示信息(ocr)
    List<CheckPersonJs> checkForeignPersonJsInfo(CheckForeignPersonInfoRequest request);

    //12.请求核查对象轨迹信息
    List<CheckInfoListResponse> checkInfoList(CheckInfoListRequest data);

    //13.请求民警核查记录
    List<CheckInfoListPoliceResponse> checkInfoListPolice(CheckInfoListPoliceRequest data);

    //15.请求境内人员照片信息
    CheckPersonPhotoResponse checkPersonPhoto(String deviceNo,String IDCard);

    //18.请求核查地点接口
    String checkAddress(String deviceNo, String parentId);

    //25.人脸识别接口
    List<CheckPersonFaceResponse> checkPersonFace(String BASE64img);

    //7.市局的警示详细信息
    List<CheckPersonJsDetail2> checkLocalJsDetail(CheckPersonJsDetailRequest request);
    //西城公安提供警示信息接口（优先级大于市局接口）
    List<CheckPersonJs4XiCheng> checkPersonJs4XiCheng(Map data,String checkType);

    //录入接口（境内）
    boolean upLoad(UploadRequest uploadRequest,String deviceNo);
    //录入接口（境外）
    boolean upLoadForeign(UploadRequest uploadRequest,String deviceNo);

    SsoResponse Ssologin(String deviceNo);
}
