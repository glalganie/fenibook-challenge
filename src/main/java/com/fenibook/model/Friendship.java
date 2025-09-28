package com.fenibook.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "friendships", uniqueConstraints = @UniqueConstraint(columnNames = {"user1_id", "user2_id"}))
@Data
@NoArgsConstructor
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user1_id", nullable = false) // Chi invia la richiesta
    private User user1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user2_id", nullable = false) // Chi riceve la richiesta
    private User user2;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FriendshipStatus status;

    @Column(name = "requested_at", updatable = false)
    private LocalDateTime requestedAt;

    @PrePersist
    protected void onCreate() {
        requestedAt = LocalDateTime.now();
    }

    public enum FriendshipStatus { PENDING, ACCEPTED, DECLINED, BLOCKED }

	public void setUser1(User requester) {
		// TODO Auto-generated method stub
		
	}

	public void setStatus(FriendshipStatus pending) {
		// TODO Auto-generated method stub
		
	}

	public void setUser2(User recipient) {
		// TODO Auto-generated method stub
		
	}

	public Object getUser2() {
		// TODO Auto-generated method stub
		return null;
	}



	
}