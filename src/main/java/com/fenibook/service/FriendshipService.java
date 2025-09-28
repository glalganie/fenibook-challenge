package com.fenibook.service;

import com.fenibook.model.User;
import java.util.List;

public interface FriendshipService {
    void sendFriendRequest(Long requesterId, Long recipientId);
    void acceptFriendRequest(Long friendshipId);
    void declineFriendRequest(Long friendshipId);
    List<User> getFriends(Long userId);
    List<User> getPendingRequests(Long userId);
}