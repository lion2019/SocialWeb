package com.social.controller;

import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;
import com.social.model.domain.*;
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

    /**
     * 新增好友
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        BaseResponse baseResponse = null;

        try {
            Friend friend = reqParam2Bean(request, Friend.class)
                    .orElseThrow(() -> new BaseException(ResponseEnum.parameter_empty));

            User userInfo = (User) request.getSession().getAttribute("userInfo");

            // 驗証不可加自己為好友
            if(friend.getNickname_to().equals(userInfo.getNickname())){
                baseResponse = new BaseResponse(ResponseEnum.friend_not_self);
                return;
            }

            friend.setNickname_from(userInfo.getNickname());
            friend.setCreate_date(Timestamp.valueOf(LocalDateTime.now()));

            if(friendService.addFriend(friend)){
                baseResponse = new BaseResponse(ResponseEnum.OK);
            }else{
                baseResponse = new BaseResponse(ResponseEnum.insert_error);
            }

        } catch (SQLException e){
            e.printStackTrace();
            // FIXME 未統一針對 db error code 處理
            //FIXME  duplicate 未判斷
            ResponseEnum responseEnum = e.getErrorCode() == 23506 ? ResponseEnum.user_not_found : ResponseEnum.insert_error;
            baseResponse = new BaseResponse(responseEnum);
        } catch (BaseException e){
            e.printStackTrace();
            baseResponse = new BaseResponse(ResponseEnum.insert_error);
        } catch (InstantiationException | IllegalAccessException | IOException
                | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }finally{
            JSONObject output = JSONObject.fromObject(baseResponse);
            response.getWriter().println(output);
        }
    }

    /**
     * 好友清單
     */
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
