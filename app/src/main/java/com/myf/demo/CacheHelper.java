package com.myf.demo;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by MaoYouFeng on 2016/5/25.
 */
public class CacheHelper {

    private Context mContext;
    public static final Type newsListType = new TypeToken<List<CookBook.TngouBean>>() {}.getType();
    private static String fileName="list_cookbook.txt";
    public CacheHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void savaList(List<CookBook.TngouBean> list){
        saveObject(obj2Str(list));
    }
    public List<CookBook.TngouBean> loadList(){
        return str2Obj((String) readObject());
    }

    private List<CookBook.TngouBean> str2Obj(String content){
        return new GsonBuilder().create().fromJson(content, newsListType);
    }
    private String obj2Str(List<CookBook.TngouBean> list){
        return new GsonBuilder().create().toJson(list,newsListType);
    }

    private boolean saveObject(Serializable ser) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = mContext.openFileOutput(fileName, mContext.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    private Serializable readObject() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = mContext.openFileInput(fileName);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return null;
    }
}