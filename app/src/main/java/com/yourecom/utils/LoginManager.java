package com.yourecom.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginManager {
    public static final String prefLogin = "pref_login";
    private static final String is_login = "IS_LOGIN";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public LoginManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(prefLogin, 0);
        editor = pref.edit();
    }

    public boolean isLogin() {
        return pref.getBoolean(is_login, false);
    }

    public void setLogin(boolean login) {
        editor.putBoolean(is_login, login);
        editor.commit();
    }


}
