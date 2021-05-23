package com.social.controller;

import com.social.domain.Board;
import com.social.domain.OnlineUser;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<OnlineUser> sessionList = (List<OnlineUser>) getServletContext()
                    .getAttribute("onlineUser");

//            List<Board> list = boardService.findAll();
            JSONArray jsonObject = JSONArray.fromObject(sessionList);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().print(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
