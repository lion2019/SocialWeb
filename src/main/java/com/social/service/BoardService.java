package com.social.service;

import com.social.dao.BoardDao;
import com.social.domain.Board;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;

import java.sql.SQLException;
import java.util.List;

public class BoardService {

    private BoardDao dao = new BoardDao();

    public boolean addBoard(Board board) throws SQLException {
        try{
            return dao.insert(board);
        }catch (SQLException e){
            throw e;
        }
    }

    public List<Board> findAll() throws Exception {
        try{
            return dao.findAll();
//        }catch (SQLException e){
//            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}
