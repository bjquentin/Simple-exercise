package com.example.test2;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/********
 * by h_Quentin
 * 2020年5月24日
 * 简单测试
 ********/
public class Show_data extends AppCompatActivity {
    private String now_Time;//当前时间
    private Data_c mData=new Data_c();

    private TextView time;
    private TextView battery;
    private TextView weatherdate;
    private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.message);

        handler.post(updateThread);
        time = (TextView) findViewById(R.id.Time);
        battery=findViewById(R.id.battery);
        weatherdate=findViewById(R.id.data_weather);
        mData=getIntent().getParcelableExtra("data_data");
        get_WeatherData();
        getElect();
    }

    Handler handler = new Handler();//创建Handler
    Runnable updateThread = new Runnable() {
        public void run() {
            handler.postDelayed(updateThread, 1000);
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
            now_Time = dateFormatter.format(Calendar.getInstance().getTime());//获取当前时间
            //System.out.println(now_Time);
            get_Time();
        }
    };

    private void getElect() {

        StringBuilder battery_m = new StringBuilder();
        battery_m.append("电池信息:\n");
        battery_m.append("\n总电量:");
        battery_m.append(mData.getScale());
        battery_m.append("\n当前电量:");
        battery_m.append(mData.getLevel());
        battery_m.append("\n充电状态:");
        battery_m.append(mData.getStatus());
        battery_m.append("\n电压:");
        battery_m.append(mData.getVoltage());
        battery_m.append("\n温度：");
        battery_m.append(mData.getTemperature());

        battery.setText(battery_m.toString());
    }
    private void get_WeatherData(){

        StringBuilder input_m = new StringBuilder();
        input_m.append("温度:\n");
        input_m.append(mData.getTem());
        input_m.append("\n湿度:\n");
        input_m.append(mData.getHum());
        input_m.append("\nPm2.5:\n");
        input_m.append(mData.Pm);

        weatherdate.setText( input_m.toString());
    }
    private void  get_Time(){
        StringBuilder time_=new StringBuilder();
        time_.append("当前时间\n");
        time_.append(now_Time);
        time_.append("\n信息获取时间：\n");
        time_.append(mData.getTime());

        time.setText(time_.toString());
    }
}
