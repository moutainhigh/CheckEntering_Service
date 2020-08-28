package com.fri.pojo.bo.app.request;

import org.hibernate.validator.constraints.NotBlank;

public class CheckPersonJsDetailRequest {
    @NotBlank(message = "警示信息名称不能为空")
    private String resName;
    //核录桩设备号，除PC外，其他设备必填
    @NotBlank(message = "核录桩设备号不能为空")
    private String deviceNo;
    //预警人员身份证号
    @NotBlank(message = "预警人员身份证号不能为空")
    private String cardNumber;

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
