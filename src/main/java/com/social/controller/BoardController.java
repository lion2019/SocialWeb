package com.social.controller;

import com.social.domain.Board;
import com.social.domain.BoardRequest;
import com.social.domain.RegisterRequest;
import com.social.domain.User;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;
import com.social.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/board.do"})
public class BoardController extends BaseController {

    private BoardService boardService = new BoardService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            BoardRequest boardRequest = reqParam2Bean(request, BoardRequest.class)
                    .orElseThrow(() -> new BaseException(ResponseEnum.parameter_empty));

            User userInfo = (User) request.getSession().getAttribute("userInfo");
            boardRequest.setNickname(userInfo.getNickname());
            Board board = boardRequest.convert2Entity();

            boardService.addBoard(board);

//            resp.sendRedirect(req.getContextPath()+"/main.jsp");

            // FIXME ServletException 內容可調使用 enum
//			User output = user.filter(registerService::addUser).orElseThrow(()->new ServletException("user add error!!"));

//			user.ifPresent(registerService::addUser);
//			req.setAttribute("userInfo", user);

//			resp.setContentType("application/json;");
//			resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

//			JSONObject output = JSONObject.fromObject(user);
//			resp.getWriter().println(output);
        } catch (SQLException e){
            e.printStackTrace();
            String errorMsg = e.getErrorCode() == 23506? "user 不存在!":ResponseEnum.insert_error.getMessage();
            request.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("/board.jsp").forward(request, response);
            return;
//			System.err.println("err code:"+e.getCode());
//			System.err.println("err msg:"+e.getMessage());
//			throw e;
        } catch (InstantiationException | IllegalAccessException | IOException
                | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
