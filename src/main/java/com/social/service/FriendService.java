package com.social.service;

import com.social.dao.BoardDao;
import com.social.dao.FriendDao;
import com.social.domain.Board;
import com.social.domain.Friend;

import java.sql.SQLException;
import java.util.List;

public class FriendService {

    private FriendDao dao = new FriendDao();

    public boolean addFriend(Friend friend) throws SQLException {
        try{
            return dao.insert(friend);
        }catch (SQLException e){
            throw e;
        }
    }

    public List<Friend> findAll() throws Exception {
        try{
            return dao.findAll();
//        }catch (SQLException e){
//            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}
