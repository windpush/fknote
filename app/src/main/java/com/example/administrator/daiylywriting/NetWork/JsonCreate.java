package com.example.administrator.daiylywriting.NetWork;

import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2015/3/11.
 */
public class JsonCreate {
    public RequestParams dmCreate(String dmVaule,String dmColor) {
        RequestParams jsonVaule;
        jsonVaule = new RequestParams();

//        包装Json值
        jsonVaule.put("dm",dmVaule );
        jsonVaule.put("color",dmColor );
//     返回JsonList
        return jsonVaule;
    }
}
