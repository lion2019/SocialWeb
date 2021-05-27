package com.social.service;

import com.social.dao.FriendDao;
import com.social.domain.Friend;

import java.sql.SQLException;
import java.util.List;

/**
 * 好友服務
 */
public class FriendService {

    private FriendDao dao = new FriendDao();

    public boolean addFriend(Friend friend) throws SQLException {
        dao.insert(friend);

        Friend friend1 = new Friend();
        friend1.setNickname_to(friend.getNickname_from());
        friend1.setNickname_from(friend.getNickname_to());
        friend1.setCreate_date(friend.getCreate_date());

        return dao.insert(friend1);
    }

    public List<Friend> findAll() throws Exception {
        return dao.findAll();
    }

    public List<Friend> findByNickname_from(String nickname_from){
        return dao.findByNickname_from(nickname_from);
    }
}
