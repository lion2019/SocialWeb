package com.social.controller;

import com.social.model.domain.OnlineUser;
import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 線上使用者
 */
@WebServlet(urlPatterns = {"/onlineUser.do"})
public class OnlineUserController extends BaseController {

    /**
     * 線上使用者清單
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<OnlineUser> sessionList = (List<OnlineUser>) getServletContext()
                    .getAttribute("onlineUser");

            JSONArray jsonObject = JSONArray.fromObject(sessionList);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().print(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
