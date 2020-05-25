package com.example.test2;

import android.os.Parcel;
import android.os.Parcelable;


/********
 * by h_Quentin
 * 2020年5月24日
 * 简单测试
 ********/
public class Data_c extends Data_ implements Parcelable {
    public Data_c(Data_ mdata){
        setTime(mdata.Time);
        setTem(mdata.Tem);
        setHum(mdata.Hum);
        setPm(mdata.Pm);
        setLevel(mdata.Level);
        setScale(mdata.Scale);
        setStatus(mdata.Status);
        setVoltage(mdata.Voltage);
        setTemperature(mdata.Temperature);
    }
    public Data_c(){

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Time);
        parcel.writeString(Tem);
        parcel.writeString(Hum);
        parcel.writeString(Pm);
        parcel.writeString(Level);
        parcel.writeString(Scale);
        parcel.writeString(Status);
        parcel.writeString(Voltage);
        parcel.writeString(Temperature);
    }

    public static final Parcelable.Creator<Data_c> CREATOR = new Parcelable.Creator<Data_c>(){
        @Override
        public Data_c createFromParcel(Parcel parcel) {
            Data_c data = new Data_c();
            data.Time = parcel.readString();
            data.Tem = parcel.readString();
            data.Hum = parcel.readString();
            data.Pm = parcel.readString();
            data.Level = parcel.readString();
            data.Scale = parcel.readString();
            data.Status = parcel.readString();
            data.Voltage = parcel.readString();
            data.Temperature = parcel.readString();
            return data;
        }

        @Override
        public Data_c[] newArray(int i) {
            return new Data_c[i];
        }
    };

}
