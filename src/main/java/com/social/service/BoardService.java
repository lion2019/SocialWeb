package com.social.service;

import com.social.dao.BoardDao;
import com.social.domain.Board;

import java.sql.SQLException;
import java.util.List;

/**
 * 留言板服務
 */
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
        return dao.findAll();
    }

    public boolean delete(Integer room_number) throws SQLException {
        return dao.deleteByRoomNumber(room_number) > 0;
    }

    public boolean update(Board board, Integer orig_room_number) throws SQLException {
        return dao.update(board, orig_room_number) > 0;
    }
}
