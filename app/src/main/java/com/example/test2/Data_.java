package com.example.test2;


/********
 * by h_Quentin
 * 2020年5月24日
 * 简单测试
 ********/
public class Data_ {
    protected String Time;//时间
    protected String Tem;//温度
    protected String Hum;//湿度
    protected String Pm;//Pm2.5
    protected String Level;//当前电量
    protected String Scale ;//总电量
    protected String Status;//充电状态
    protected String Voltage ;//电压(mv)
    protected String Temperature ;//温度(数值)

    public Data_ (){
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setTem(String tem) {
        Tem = tem;
    }

    public void setHum(String hum) {
        Hum = hum;
    }

    public void setPm(String pm) {
        Pm = pm;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setVoltage(String voltage) {
        Voltage = voltage;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public  Data_(String time, String tem, String hum, String pm, String level, String scale, String status, String voltage, String temperature) {
        Time=time;
        Tem = tem;
        Hum = hum;
        Pm = pm;
        Level=level;
        Scale=scale;
        Status=status;
        Voltage=voltage;
        Temperature=temperature;
    }

    public String getTime() {
        return Time;
    }

    public String getTem()
    {
        return Tem;
    }

    public String getHum() {
        return Hum;
    }

    public String getPm() {
        return Pm;
    }

    public String getLevel() {
        return Level;
    }

    public String getScale() {
        return Scale;
    }

    public String getStatus() {
        return Status;
    }

    public String getVoltage() {
        return Voltage;
    }

    public String getTemperature() {
        return Temperature;
    }

}

