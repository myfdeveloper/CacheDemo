package com.myf.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    CacheHelper cacheHelper;
    @Bind(R.id.textView)
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        cacheHelper = new CacheHelper(this);
    }

    @OnClick(R.id.button)
    public void save(){
        List<CookBook.TngouBean> list=new ArrayList<>();
        CookBook.TngouBean bean=new CookBook.TngouBean();
        bean.setName("test");
        list.add(bean);
        cacheHelper.savaList(list);
    }
    @OnClick(R.id.button2)
    public void load(){
        List<CookBook.TngouBean> list=cacheHelper.loadList();
        Log.d("myf","list -->"+(list==null));
        String name =list.get(0).getName();
        textView.setText(name);
    }
}
