package com.fenibook.repository;

import com.fenibook.model.Friendship;
import com.fenibook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    /**
     * Trova le richieste di amicizia inviate a un utente (user2) con un certo stato.
     * Usato per trovare le richieste in attesa (PENDING).
     */
    List<Friendship> findByUser2AndStatus(User user, Friendship.FriendshipStatus status);

    /**
     * Trova tutte le amicizie ACCETTATE per un dato utente, usando una query JPQL esplicita
     * per massima chiarezza e robustezza.
     * @param user L'utente per cui cercare gli amici.
     * @param status Lo stato di amicizia da cercare (es. ACCEPTED).
     * @return una lista di amicizie.
     */
    @Query("SELECT f FROM Friendship f WHERE (f.user1 = :user OR f.user2 = :user) AND f.status = :status")
    List<Friendship> findAcceptedFriendshipsForUser(@Param("user") User user, @Param("status") Friendship.FriendshipStatus status);
}