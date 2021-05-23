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
        return dao.insert(friend);
    }

    public List<Friend> findAll() throws Exception {
        return dao.findAll();
    }

    public List<Friend> findByNickname_from(String nickname_from){
        return dao.findByNickname_from(nickname_from);
    }
}
