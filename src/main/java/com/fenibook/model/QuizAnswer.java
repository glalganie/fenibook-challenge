package com.fenibook.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_answers")
@Data
@NoArgsConstructor
public class QuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "answer", nullable = false, length = 1)
    private char answer;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "answered_at")
    private LocalDateTime answeredAt;

    @PrePersist
    protected void onPersist() {
        answeredAt = LocalDateTime.now();
    }
}