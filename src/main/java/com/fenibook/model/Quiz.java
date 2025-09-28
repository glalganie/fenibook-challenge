package com.fenibook.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "quizzes")
@Data
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(name = "option_a", nullable = false, columnDefinition = "TEXT")
    private String optionA;
    @Column(name = "option_b", nullable = false, columnDefinition = "TEXT")
    private String optionB;
    @Column(name = "option_c", nullable = false, columnDefinition = "TEXT")
    private String optionC;
    @Column(name = "option_d", nullable = false, columnDefinition = "TEXT")
    private String optionD;

    @Column(name = "correct_answer", nullable = false, length = 1)
    private char correctAnswer; // 'A', 'B', 'C', 'D'

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}