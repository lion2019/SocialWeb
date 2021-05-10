package com.social.dao;

import com.social.domain.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BoardDao extends BaseDao<Board> {

//    public boolean insert(Board board){
//        String sql = "inest into board ";
//        try(Connection conn = dataSource.getConnection();
//            PreparedStatement ps = conn.prepareStatement(sql);){
//            System.out.println(sql);
//            setParameter(ps, t);
//            return ps.executeUpdate() == 1;
//        }
//    }
}
