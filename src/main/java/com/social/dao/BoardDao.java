package com.social.dao;

import com.social.domain.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardDao extends BaseDao<Board> {

    /**
     * 修改留言板
     * @param board 要變更的值
     * @param orig_room_number 原始 room_number
     */
    public int update(Board board, Integer orig_room_number) throws SQLException {
        String sql = "update board set nickname =?, message=?, room_number=? where room_number = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1, board.getNickname());
            ps.setString(2, board.getMessage());
            ps.setInt(3, board.getRoom_number());
            ps.setInt(4, orig_room_number);
            return ps.executeUpdate();
        }
    }

    /**
     * 刪除留言版, 條件：room_number
     */
    public int deleteByRoomNumber(Integer room_number) throws SQLException {
        String sql = "delete board where room_number = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setInt(1, room_number);
            return ps.executeUpdate();
        }
    }
}
