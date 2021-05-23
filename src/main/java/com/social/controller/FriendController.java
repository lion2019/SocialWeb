package com.social.controller;

import com.social.domain.*;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;
import com.social.service.FriendService;
import net.sf.json.JSONArray;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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

            // 驗証不可加自己為好友
            if(friend.getNickname_to().equals(userInfo.getNickname())){
                baseResponse = new BaseResponse(ResponseEnum.friend_not_self);
                return;
//                throw new BaseException(ResponseEnum.friend_not_self);
            }

            friend.setNickname_from(userInfo.getNickname());
            friend.setCreate_date(Timestamp.valueOf(LocalDateTime.now()));

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
        } catch (BaseException e){
            e.printStackTrace();
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
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try {
            User userInfo = (User) req.getSession().getAttribute("userInfo");

            List<Friend> list = friendService.findByNickname_from(userInfo.getNickname());

            List<FriendResponse> output = FriendResponse.convert(list);

            // 線上使用者
            List<OnlineUser> sessionList = (List<OnlineUser>) getServletContext()
                    .getAttribute("onlineUser");

            // 判斷朋友上線狀態
            output.forEach(o->{
                final String friendNickname = o.getNickname_to();
                boolean b = sessionList.stream().anyMatch(o1 -> o1.getNickname().equals(friendNickname));
                o.setStatus(b? "上線":"下線");
            });

            JSONArray jsonObject = JSONArray.fromObject(output);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
