-- Impostazioni iniziali per una nuova creazione pulita
DROP DATABASE IF EXISTS fenibook_db;
CREATE DATABASE fenibook_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE fenibook_db;

-- ####################################################################
-- #                       MODULO CORE & UTENTI                       #
-- ####################################################################

CREATE TABLE `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL COMMENT 'Contiene l''hash BCrypt',
  `role` ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
  `score` INT NOT NULL DEFAULT 0,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `books` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `isbn` VARCHAR(20) UNIQUE,
  `title` VARCHAR(255) NOT NULL,
  `author` VARCHAR(100),
  `genre` VARCHAR(50),
  `publication_date` DATE,
  `cover_image_url` VARCHAR(255),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `user_library` (
  `user_id` BIGINT NOT NULL,
  `book_id` BIGINT NOT NULL,
  `status` ENUM('READ', 'TO_READ') NOT NULL,
  `added_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`, `book_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`book_id`) REFERENCES `books`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB;

-- ####################################################################
-- #                      MODULO SOCIAL & COMMUNITY                   #
-- ####################################################################

CREATE TABLE `friendships` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user1_id` BIGINT NOT NULL,
  `user2_id` BIGINT NOT NULL,
  `status` ENUM('PENDING', 'ACCEPTED', 'DECLINED') NOT NULL,
  `requested_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user1_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`user2_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  UNIQUE KEY `uk_friendship` (`user1_id`, `user2_id`)
) ENGINE=InnoDB;

CREATE TABLE `comments` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `book_id` BIGINT NOT NULL,
  `content` TEXT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`book_id`) REFERENCES `books`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `comment_likes` (
  `comment_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`comment_id`, `user_id`),
  FOREIGN KEY (`comment_id`) REFERENCES `comments`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB;


-- ####################################################################
-- #                    MODULO SFIDE & GAMIFICATION                   #
-- ####################################################################

-- Le nostre sfide di lettura (es. "Leggi 5 libri")
CREATE TABLE `reading_challenges` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT,
  `goal_count` INT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- I tuoi quiz basati sui libri
CREATE TABLE `quizzes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `book_id` BIGINT NOT NULL,
  `creator_id` BIGINT NOT NULL,
  `recipient_id` BIGINT,
  `question` TEXT NOT NULL,
  `option_a` TEXT NOT NULL,
  `option_b` TEXT NOT NULL,
  `option_c` TEXT NOT NULL,
  `option_d` TEXT NOT NULL,
  `correct_answer` CHAR(1) NOT NULL,
  `status` VARCHAR(30) DEFAULT 'PENDING',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`book_id`) REFERENCES `books`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`creator_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`recipient_id`) REFERENCES `users`(`id`) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE `quiz_answers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `quiz_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `answer` CHAR(1) NOT NULL,
  `is_correct` BOOLEAN,
  `answered_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`quiz_id`) REFERENCES `quizzes`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB;