package com.social.model;

import com.social.model.domain.Friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FriendDao extends BaseDao<Friend> {

    /**
     * 查好友，條件：nickname_from
     */
    public List<Friend> findByNickname_from(String nickname_from) {
        String sql = "select * from Friend where nickname_from = ?";
        List<Friend> list = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1, nickname_from);
            try(ResultSet rs = ps.executeQuery();){
                while(rs.next()) {
                    Optional<Friend> friend = resultSet2Bean(rs, Friend.class);
                    list.add(friend.get());
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
