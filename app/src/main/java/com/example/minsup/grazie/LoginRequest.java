package com.example.minsup.grazie;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Minsub on 2018-05-10.
 */

public class LoginRequest extends StringRequest {

    final static private String URL = "http://gguoops.cafe24.com/Login_count.php";
    private Map<String, String> parameters;

    public LoginRequest(String userSno, String userPassword, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userSno", userSno);
        parameters.put("userPassword", userPassword);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }

}
