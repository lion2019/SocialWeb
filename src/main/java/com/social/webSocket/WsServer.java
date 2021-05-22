package com.social.webSocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/webSocket/chat/{roomNo}")
public class WsServer {

    // 使用map來收集session，key為roomName，value為同一個房間的使用者集合
    // concurrentMap的key不存在時報錯，不是返回null
    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap();
    private static final String openRoom="-1";//公頻號碼

    @OnOpen
    public void connect(@PathParam("roomName") String roomNo, Session session) throws Exception {

        if(roomNo == "null") roomNo = openRoom;
        // 將session按照房間名來儲存，將各個房間的使用者隔離
        if (!rooms.containsKey(roomNo)) {
            // 建立房間不存在時，建立房間
            Set<Session> room = new HashSet<>();
            // 新增使用者
            room.add(session);
            rooms.put(roomNo, room);
        } else {
            // 房間已存在，直接新增使用者到相應的房間
            rooms.get(roomNo).add(session);
        }
        System.out.println("a client has connected!");
    }

    @OnClose
    public void disConnect(@PathParam("roomNo") String roomNo, Session session) {
        rooms.get(roomNo).remove(session);
        System.out.println("a client has disconnected!");
    }

    @OnMessage
    public void receiveMsg(@PathParam("roomNo") String roomNo,
                           String msg, Session session) throws Exception {
        // 此處應該有html過濾
        //msg = session.getId() + ":" + msg;
        System.out.println(msg);
        // 接收到資訊後進行廣播
        broadcast(roomNo, msg);
    }

    // 按照房間名進行廣播
    public static void broadcast(String roomNo, String msg) throws Exception {
        for (Session session : rooms.get(roomNo)) {
            session.getBasicRemote().sendText(msg);
        }
    }

}
