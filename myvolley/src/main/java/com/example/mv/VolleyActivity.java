package com.example.mv;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VolleyActivity extends FragmentActivity implements View.OnClickListener {
    @InjectView(R.id.tv_loadImage)
    TextView tvLoadImage;
    @InjectView(R.id.image_loader)
    ImageView imag_loader;
    @InjectView(R.id.tv_networkimageview)
    TextView tvNetworkimageview;
    @InjectView(R.id.img_networkimageview)
    NetworkImageView imgNetworkimageview;
    @InjectView(R.id.img_networkimageview2)
    NetworkImageView imgNetworkimageview2;
    @InjectView(R.id.tv_getJson)
    TextView tvGetJson;
    @InjectView(R.id.tv_postJson)
    TextView tvPostJson;
    @InjectView(R.id.tv_yasuo)
    TextView tvYasuo;
    @InjectView(R.id.image_yasuo)
    ImageView imageYasuo;
    @InjectView(R.id.image_original)
    ImageView imageOriginal;
    //

    private String url_pic = "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";
    private String url_json = "http://101.200.183.103:9999/a1/login?uid=test&passwd=test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        tvLoadImage.setOnClickListener(this);
        tvNetworkimageview.setOnClickListener(this);
        tvGetJson.setOnClickListener(this);
        tvPostJson.setOnClickListener(this);
        tvYasuo.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        RequestQueue mQueue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        int id = v.getId();
        switch (id) {
            case R.id.tv_loadImage:
                ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imag_loader, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                imageLoader.get(url_pic, imageListener);
                break;
            case R.id.tv_networkimageview:
                //���ع̶���ߵ�ͼƬ
                imgNetworkimageview.setDefaultImageResId(R.mipmap.ic_launcher);
                imgNetworkimageview.setErrorImageResId(R.mipmap.weekscheme_browse_pic_);
                imgNetworkimageview.setImageUrl(url_pic, imageLoader);
                //��������ԭͼ��С
                imgNetworkimageview2.setDefaultImageResId(R.mipmap.ic_launcher);
                imgNetworkimageview2.setErrorImageResId(R.mipmap.weekscheme_browse_pic_);
                imgNetworkimageview2.setImageUrl(url_pic, imageLoader);
                break;
            case R.id.tv_getJson:
                //get��ʽ��������
                mQueue.add(new JsonObjectRequest(Request.Method.GET, url_json, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        L.d("get-----" + jsonObject);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        L.d("get-----" + volleyError);
                    }
                }));
                break;
            case R.id.tv_postJson:
                //post��ʽ��������
                mQueue.add(new JsonObjectRequest(Request.Method.POST, url_json, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        L.d("post-----" + jsonObject);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        L.d("post-----" + volleyError);
                    }
                }));
                break;
            case R.id.tv_yasuo:
                //ѹ����Դ�ļ��µ�ͼƬ�������ù̶����
                Bitmap bitmap = YasuoBitmap.yasuoBitmapFromResources(getResources(), R.mipmap.yasuo, 30, 100);
                imageYasuo.setImageBitmap(bitmap);
                //����ԭͼ
                ImageLoader.ImageListener imageListener2 = ImageLoader.getImageListener(imageOriginal, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                imageLoader.get(url_pic, imageListener2);
                break;
        }
    }

}
