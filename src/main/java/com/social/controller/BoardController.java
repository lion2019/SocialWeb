package com.social.controller;

import com.social.model.domain.BaseResponse;
import com.social.model.domain.Board;
import com.social.model.domain.BoardRequest;
import com.social.model.domain.User;
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

/**
 * 留言板
 */
@WebServlet(urlPatterns = {"/board.do"})
public class BoardController extends BaseController {

    private BoardService boardService = new BoardService();

    /**
     * 新增留言
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 設定 ContentType 格式
        response.setContentType("application/json;");
        // 設定 response 編碼
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        BaseResponse baseResponse = null;

        try {
            BoardRequest requestBean = reqParam2Bean(request, BoardRequest.class)
                    .orElseThrow(() -> new BaseException(ResponseEnum.parameter_empty));

            // 取得 user 登入資料
            User userInfo = (User) request.getSession().getAttribute("userInfo");
            requestBean.setNickname(userInfo.getNickname());

            // 將接收前端資料的物件轉換成 dao 使用的物件
            Board board = requestBean.convert2Entity();

            if(boardService.addBoard(board)){
                baseResponse = new BaseResponse(ResponseEnum.OK);
            }else{
                baseResponse = new BaseResponse(ResponseEnum.insert_error);
            }

        } catch (SQLException e){
            e.printStackTrace();
            //FIXME  duplicate 未判斷
            ResponseEnum responseEnum = e.getErrorCode() == 23506 ? ResponseEnum.user_not_found : ResponseEnum.insert_error;
            baseResponse = new BaseResponse(responseEnum);
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
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            List<Board> list = boardService.findAll();
            JSONArray jsonObject = JSONArray.fromObject(list);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    /**
     * 修改留言板
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 設定 ContentType 格式
        response.setContentType("application/json;");
        // 設定 response 編碼
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        BaseResponse baseResponse = null;

        try {
            // 取得 user 登入資料
            User userInfo = (User) request.getSession().getAttribute("userInfo");

            // 取得前端輸入的json資料
            JSONObject jsonObject = requestParse2Json(request);

            Board board = new Board();
            board.setNickname(userInfo.getNickname());
            board.setRoom_number(jsonObject.getInt("room_number"));
            board.setMessage(jsonObject.getString("message"));


            int orig_room_number = jsonObject.getInt("orig_room_number");

            System.out.println("room_number:"+orig_room_number);
            if(boardService.update(board, orig_room_number)){
                baseResponse = new BaseResponse(ResponseEnum.OK);
            }else{
                baseResponse = new BaseResponse(ResponseEnum.delete_error);
            }

        } catch (SQLException e){
            e.printStackTrace();
            baseResponse = new BaseResponse(ResponseEnum.delete_error);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }finally{
            JSONObject output = JSONObject.fromObject(baseResponse);
            response.getWriter().println(output);
        }
    }

    /**
     * 刪除 board
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 設定 ContentType 格式
        response.setContentType("application/json;");
        // 設定 response 編碼
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        BaseResponse baseResponse = null;

        try {
            JSONObject jsonObject = requestParse2Json(request);

            int room_number = jsonObject.getInt("room_number");

            System.out.println("room_number:"+room_number);
            if(boardService.delete(room_number)){
                baseResponse = new BaseResponse(ResponseEnum.OK);
            }else{
                baseResponse = new BaseResponse(ResponseEnum.delete_error);
            }

        } catch (SQLException e){
            e.printStackTrace();
            baseResponse = new BaseResponse(ResponseEnum.delete_error);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }finally{
            JSONObject output = JSONObject.fromObject(baseResponse);
            response.getWriter().println(output);
        }
    }
}
