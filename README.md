
<img width="400" height="400" alt="logo-fenibook-challenge-completo" src="https://github.com/user-attachments/assets/b213fdf4-6e24-4fc9-b865-8271dbcc7925" />


# Fenibook Challenge: la lettura rinasce, la tua avventura inizia.

**Fenibook Challenge** è una piattaforma web interattiva progettata per promuovere la lettura, trasformandola in un'attività sociale, stimolante e moderna. Ogni utente può creare la propria libreria virtuale, partecipare a sfide di lettura, interagire con una community di lettori e, in futuro, sfidare amici con quiz basati sui libri.

Questo repository contiene il **Minimum Viable Product (MVP)** del progetto, sviluppato per la presentazione a **TBR S.p.A.**

---

## ✨ Features (Funzionalità MVP e Visione Futura)

- 👤 **Registrazione & Login Sicuro**
  - Registrazione utente con validazione lato server.
  - Login sicuro gestito da Spring Security con password cifrate (BCrypt).
  - Ruoli utente differenziati (`USER`, `ADMIN`).

- 📚 **Libreria Personale**
  - Aggiungi libri al tuo scaffale virtuale come "Da Leggere" o "Letto".
  - Tieni traccia delle tue letture in una dashboard personale.

- 🏆 **Sfide di Lettura (Core della Visione)**
  - *(MVP)* Una base per creare e partecipare a sfide di lettura (es. "Leggi 5 libri in un mese").
  -  Sistema di punti, badge e classifiche per la gamification.

- 🧠 **Quiz tra Lettori**
  - Crea sfide quiz basate sui libri.
  - Invia quiz ad amici o rendili pubblici.
  - Rispondi, guadagna punti e scala la classifica.

- 💬 **Community & Interazione**
  - Commenta i libri e metti like ai commenti altrui.
  - Cerca altri lettori e invia richieste di amicizia.
  - Unisciti a "Club del Libro 2.0" per discussioni e sfide di gruppo.

  ## 🚀 Visione Futura (Funzionalità Innovative)

L'MVP è solo l'inizio. La visione a lungo termine di Fenibook Challenge include:
*   **🏆 Sistema di Sfide e Gamification:** Per trasformare la lettura in un gioco a punti.
*   **🤝 Club del Libro 2.0:** Per creare micro-community e sfide di squadra.
*   **💡 Libreria Emozionale (AI):** Per suggerire libri in base allo stato d'animo.
*   **📱 Scanner Magico (AR):** Per collegare l'esperienza digitale al mondo dei libri fisici.


- 🔐 **Pannello di Amministrazione**
  - Un'area `/admin` protetta per la gestione completa del catalogo libri (Creare, Leggere, Aggiornare, Eliminare).
  - Gestione di base degli utenti.

---

## 📸 Screenshots

![Pagine Login e Register](https://github.com/user-attachments/assets/bdb7f24b-2f96-437d-ac62-c2c47128002b)

![dashboard](https://github.com/user-attachments/assets/41a4d6bf-e5d8-4297-960f-cce287d7429a)

![sceglilibri](https://github.com/user-attachments/assets/81850c7f-fc7e-4e34-aea5-b14a8fe86060)




---

## 💻 Tech Stack

| Tecnologia                    | Ruolo                                        |
|-------------------------------|----------------------------------------------|
| **Spring Boot**               | Backend MVC, API REST, sicurezza             |
| **Thymeleaf**                 | Template Engine lato server-side             |
| **MySQL 8 & H2**              | Database relazionale (Produzione & Sviluppo) |
| **HTML5 / CSS3 / Bootstrap 5**| Interfaccia utente responsive e moderna      |
| **JavaScript**                | Interattività client-side                    |
| **Spring Security**           | Autenticazione & Autorizzazione su ruoli     |
| **Docker & Docker Compose**   | Containerizzazione per un ambiente consistente |

---

## 🔒 Sicurezza

- Autenticazione gestita interamente da Spring Security.
- Password degli utenti archiviate in modo sicuro utilizzando l'hashing BCrypt.
- Accesso alle rotte `/admin/**` riservato esclusivamente agli utenti con ruolo `ADMIN`.
- Protezione CSRF abilitata di default da Spring Security per tutte le richieste POST.

---

## 📐 Design e Architettura

- **Architettura Monolitica Modulare:** Semplice da sviluppare e deployare per l'MVP, ma strutturata in package (model, repository, service, controller) per garantire manutenibilità e una futura scalabilità.
- **Design Pattern Service/ServiceImpl:** Codice disaccoppiato e testabile grazie alla separazione tra interfacce e implementazioni.
- **Layout Responsive:** Interfaccia utente progettata per essere pienamente funzionale su dispositivi desktop e mobili.

---

## 🚀 Esecuzione e Build

### Avvio Rapido in Sviluppo (con H2 In-Memory)
Il modo più semplice per avviare il progetto.

```bash
# Esegui direttamente da terminale 
./mvnw spring-boot:run
 
App: http://localhost:8080
H2 Console: http://localhost:8080/h2-console

 (JDBC URL: jdbc:h2:mem:fenibook_db)
Avvio in Ambiente di Produzione-Simile (con Docker)
Questo avvia l'applicazione e un database MySQL in container separati.

# Assicurati di avere Docker e Docker Compose installati
docker-compose up --build
 
App: http://localhost:8080
MySQL: accessibile su localhost:3306

# Crea il file JAR eseguibile nella cartella /target
./mvnw clean package

# Esegui il JAR (richiede Java 17 installato)
java -jar target/fenibook-challenge-0.0.1-SNAPSHOT.jar
 

🧑‍💻 Autore
Questo progetto è stato ideato e sviluppato da Phoenix Dev come proposta per TBR S.p.A.

