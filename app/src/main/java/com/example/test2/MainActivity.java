package com.example.test2;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/********
 * by h_Quentin
 * 2020年5月24日
 * 简单测试
********/
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private MyAdapter mAdapter;  //适配器

    private RecyclerView.LayoutManager mLayoutManager;  //布局管理器

    private String time;
    private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    private Context instance;

    private ArrayList<Data_> listData= new ArrayList<>();
    private Data_c sData;
    private Data_ iData;
    public Data_ mData=new Data_();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance=getApplication();
        initData();
        initView();
    }

    public void onClick(View v) {
        mData=new Data_();
        int id = v.getId();
        if(id == R.id.rv_add_item_btn) {
            getElect();
            getTime();
            input_data();
            mAdapter.addNewItem();
             //由于Adapter内部是直接在首个Item位置做增加操作，增加完毕后列表移动到首个Item位置
            mLayoutManager.scrollToPosition(0);
        } /*else if(id == R.id.rv_del_item_btn){
            mAdapter.deleteItem();
            // 由于Adapter内部是直接在首个Item位置做删除操作，删除完毕后列表移动到首个Item位置
            mLayoutManager.scrollToPosition(0);
        }*/
    }

    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyAdapter(getData());
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               // mAdaptergetLayoutPosition()
                System.out.print(position);
                iData=listData.get(listData.size()-position-1);
                sData=new Data_c(iData);
                Intent intent = new Intent(MainActivity.this,Show_data.class);
               intent.putExtra("data_data",sData);
                System.out.println(sData.getTime());
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,"long click " + position + " item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        // 设置Item添加和移除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置Item之间间隔样式
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        /*for(int i = 0; i < 20; i++) {
            data.add(i + temp);
        }*/

        return data;
    }
    private void input_data() {

        /*
         * AlertDialog 弹出提示框
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View view = View.inflate(this, R.layout.dinput, null);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(view);
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);//调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
        final EditText Tem = (EditText) view.findViewById(R.id.Tem);
        final EditText Hum = (EditText) view.findViewById(R.id.Hum);
        final EditText Pm = (EditText) view.findViewById(R.id.Pm);
        Button button = (Button) view.findViewById(R.id.btsure);
        Button button2 = (Button) view.findViewById(R.id.btcen);

        button.setOnClickListener(new View.OnClickListener() {
            String Tem_ = "";
            String Hum_ = "";
            String Pm_ = "";
            String msg = "";

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Tem_ = Tem.getText().toString();
                Hum_ = Hum.getText().toString();
                Pm_ = Pm.getText().toString();

                if (check()) {
                    mData.setTem(Tem_+"℃");
                    mData.setHum(Hum_+"%");
                    mData.setPm(Pm_);
                    alertDialog.dismiss();
                }else{

                }


            }

            private boolean check() {

                if ("".equals(Tem_) || "".equals(Hum_) || "".equals(Pm_)) {
                    msg = "输入不能为空！";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT)
                            .show();
                    return false;
                }else {
                    return true;
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener()

        {
            String msg;
            @Override
            public void onClick (View v){
                // TODO Auto-generated method stub
                msg = "输入被取消，相关数据将置为空";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT)
                        .show();
                alertDialog.dismiss();
            }

        });
        listData.add(mData);
    }
    private void getElect() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent receiver = instance.registerReceiver(null, filter);
        int level = receiver.getIntExtra("level", 0);//获取当前电量
        int scale = receiver.getIntExtra("scale", 0);//获取总电量
        int status = receiver.getIntExtra("status", 0);//获取充电状态
        int voltage = receiver.getIntExtra("voltage", 0);//获取电压(mv)
        int temperature = receiver.getIntExtra("temperature", 0);//获取温度(数值)
        double t = temperature / 10.0;  //运算转换,电池摄氏温度，默认获取的非摄氏温度值
        String status_battery;
        if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
            status_battery = "正在充电";
        } else {
            status_battery = "正在放电";
        }

        mData.setLevel(String.valueOf(level)+"%");
        mData.setScale(String.valueOf(scale)+"%");
        mData.setStatus(status_battery);
        mData.setVoltage(String.valueOf(voltage)+"mv");
        mData.setTemperature(String.valueOf(t)+"℃");
    }
    private void getTime(){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        time = dateFormatter.format(Calendar.getInstance().getTime());//获取当前时间
        mData.setTime(time);
    }
}


