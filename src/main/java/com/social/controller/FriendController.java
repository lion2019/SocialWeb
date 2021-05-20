package com.social.controller;

import com.social.domain.*;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;
import com.social.service.FriendService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/friend.do"})
public class FriendController extends BaseController {

    private FriendService friendService = new FriendService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        BaseResponse baseResponse = null;

        try {
            Friend friend = reqParam2Bean(request, Friend.class)
                    .orElseThrow(() -> new BaseException(ResponseEnum.parameter_empty));
//            Friend friend = requestBean.convert2Entity();

            User userInfo = (User) request.getSession().getAttribute("userInfo");
            friend.setNickname_from(userInfo.getNickname());


            if(friendService.addFriend(friend)){
                baseResponse = new BaseResponse(ResponseEnum.OK);
            }else{
                baseResponse = new BaseResponse(ResponseEnum.insert_error);
            }

            //            response.sendRedirect(request.getContextPath()+"/main.jsp");
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("code",ResponseEnum.OK.getCode());
//            jsonObject.put("message",ResponseEnum.OK.getCode());
        } catch (SQLException e){
            e.printStackTrace();
//            String errorMsg = e.getErrorCode() == 23506? "user 不存在!":ResponseEnum.insert_error.getMessage();
//            request.setAttribute("errorMsg", errorMsg);
            //FIXME  duplicate 未判斷
            ResponseEnum responseEnum = e.getErrorCode() == 23506 ? ResponseEnum.user_not_found : ResponseEnum.insert_error;
            baseResponse = new BaseResponse(responseEnum);

//            request.getRequestDispatcher("/board.jsp").forward(request, response);
//			System.err.println("err code:"+e.getCode());
//			System.err.println("err msg:"+e.getMessage());
//			throw e;
        } catch (InstantiationException | IllegalAccessException | IOException
                | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }finally{
            JSONObject output = JSONObject.fromObject(baseResponse);
            response.getWriter().println(output);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
