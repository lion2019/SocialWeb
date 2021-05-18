package com.social.controller;

import com.social.domain.*;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;
import com.social.service.BoardService;
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
import java.util.List;

@WebServlet(urlPatterns = {"/board.do"})
public class BoardController extends BaseController {

    private BoardService boardService = new BoardService();

    /**
     * 新增留言
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        BaseResponse baseResponse = null;

        try {
            BoardRequest boardRequest = reqParam2Bean(request, BoardRequest.class)
                    .orElseThrow(() -> new BaseException(ResponseEnum.parameter_empty));

            User userInfo = (User) request.getSession().getAttribute("userInfo");
            boardRequest.setNickname(userInfo.getNickname());
            Board board = boardRequest.convert2Entity();

            if(boardService.addBoard(board)){
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

    /**
     * 留言板清單
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Board> list = boardService.findAll();
            JSONArray jsonObject = JSONArray.fromObject(list);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
