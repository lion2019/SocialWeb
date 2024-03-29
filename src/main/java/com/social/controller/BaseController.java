package com.social.controller;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Optional;

/**
 * 基礎 controller 給所有 controller 繼承
 */
public abstract class BaseController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BaseController() {
        super();
    }

    /**
     * FIXME 未驗証 param 輸入的值
     * 將前端傳來的的值放入 java 物件
     * 若 request parameter key 和 bean field name 相同時，則可使用
     */
    protected <T> Optional<T> reqParam2Bean(HttpServletRequest req, Class<T> cls) throws IOException, ServletException,
            IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // 如果沒有 param 一樣會有 Enumeration 物件，非 null
        Enumeration<String> parameterNames = req.getParameterNames();

        // 若有 request param 則 new cls object
        T obj = parameterNames.hasMoreElements() ? cls.newInstance() : null;

        // 遍歷 request param
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = req.getParameter(key);

            try {
                // PropertyDescriptor 描述 Java Bean 通過一對訪問器方法導出的一個屬性。
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(key, cls);

                // 獲取應該用於寫入屬性值的方法。
                Method writeMethod = propertyDescriptor.getWriteMethod();
                System.out.println("param:" + req.getParameter(key) + ", getPropertyType:" + propertyDescriptor.getPropertyType());
                // 判斷屬性型別
                if (propertyDescriptor.getPropertyType() == Character.class) {
                    /*
                    invoke
                    在具有指定參數的指定對像上，調用此Method對象表示的基礎方法。
                    各個參數將自動解包以匹配原始形式參數，並且原始參數和引用參數都必鬚根據需要進行方法調用轉換。
                    如果該方法正常完成，則將其返回的值返回給invoke的調用者；否則，返回false。
                     */
                    writeMethod.invoke(obj, value.charAt(0));
                } else {
                    writeMethod.invoke(obj, value);
                }
            } catch (IntrospectionException e) {
                System.err.println("key:"+key+"; value:"+value);
                System.err.println("該 屬性 沒有set方法:"+key);
                throw e;
            }
        }

        System.out.println(obj);

        return Optional.ofNullable(obj);
    }

    /**
     * 從 request 取值解析成 json 物件
     */
    protected JSONObject requestParse2Json(HttpServletRequest request) throws IOException {
        // 1. get received JSON data from request
        BufferedReader br =
                new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));

        StringBuilder jsonStr = new StringBuilder();
        String temp;
        while((temp = br.readLine()) != null){
            jsonStr.append(temp);
        }
        System.out.println("json data["+jsonStr+"]");

        // json parse string
        return JSONObject.fromObject(jsonStr.toString());
    }

}