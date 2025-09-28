package com.fenibook.service.impl;

import com.fenibook.model.Friendship;
import com.fenibook.model.User;
import com.fenibook.repository.FriendshipRepository;
import com.fenibook.repository.UserRepository;
import com.fenibook.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.LocalDateTime;

@Service
public class FriendshipServiceImpl implements FriendshipService {
	
	

    @Autowired 
    private FriendshipRepository friendshipRepository;
    @Autowired 
    private UserRepository userRepository;

    @Override
    public void sendFriendRequest(Long requesterId, Long recipientId) {
        User requester = userRepository.findById(requesterId).orElseThrow(() -> new RuntimeException("Requester not found"));
        User recipient = userRepository.findById(recipientId).orElseThrow(() -> new RuntimeException("Recipient not found"));
        
        Friendship friendship = new Friendship();
        friendship.setUser1(requester);
        friendship.setUser2(recipient);
        friendship.setStatus(Friendship.FriendshipStatus.PENDING);
        friendshipRepository.save(friendship);
    }

    @Override
    public void acceptFriendRequest(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId).orElseThrow(() -> new RuntimeException("Friendship not found"));
        friendship.setStatus(Friendship.FriendshipStatus.ACCEPTED);
        friendshipRepository.save(friendship);
    }

    @Override
    public void declineFriendRequest(Long friendshipId) {
        // Potresti anche cancellare la riga, ma tenerla come 'DECLINED' può essere utile
        Friendship friendship = friendshipRepository.findById(friendshipId).orElseThrow(() -> new RuntimeException("Friendship not found"));
        friendship.setStatus(Friendship.FriendshipStatus.DECLINED);
        friendshipRepository.save(friendship);
    }

	@Override
	public List<User> getFriends(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getPendingRequests(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

 // ... all'interno della classe FriendshipServiceImpl

/*
    @Override
    public List<User> getFriends(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        List<Friendship> friendships = friendshipRepository.findAcceptedFriendshipsForUser(
                user, 
                Friendship.FriendshipStatus.ACCEPTED
        );
    	
    	
        return acceptedFriendships.stream()
                .flatMap(friendship -> Stream.of(friendship.getUser1(), friendship.getUser2()))
                .filter(friendUser -> !friendUser.getId().equals(userId))
                .distinct()
                .collect(Collectors.toList());
    }     

    
    @Override
    public List<User> getPendingRequests(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return friendshipRepository.findByUser2AndStatus(user, Friendship.FriendshipStatus.PENDING).stream()
            .map(Friendship::getUser1)
            .collect(Collectors.toList());
    }*/
}