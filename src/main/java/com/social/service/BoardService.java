package com.social.service;

import com.social.dao.BoardDao;
import com.social.domain.Board;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;

import java.sql.SQLException;

public class BoardService {

    private BoardDao dao = new BoardDao();

    public boolean addBoard(Board board) throws SQLException {
        try{
            return dao.insert(board);
        }catch (SQLException e){
            throw e;
        }
    }
}
