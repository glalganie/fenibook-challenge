package com.fenibook.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_library", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "book_id"}))
@Data
@NoArgsConstructor
public class UserLibrary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private Status status;

    @Column(name = "added_at", updatable = false)
    private LocalDateTime addedAt;
    
    @PrePersist protected void onCreate() { addedAt = LocalDateTime.now(); }
    
    public enum Status { READ, TO_READ }

	public void setUser(User user2) {
		// TODO Auto-generated method stub
		
	}

	public void setBook(Book book2) {
		// TODO Auto-generated method stub
		
	}

	public void setStatus(Status toRead) {
		// TODO Auto-generated method stub
		
	}
}