# --- FASE 1: BUILD ---
# Utilizziamo un'immagine Docker ufficiale che contiene Maven e JDK 17.
# Questa fase serve solo per compilare il progetto e creare il file .jar.
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Impostiamo la directory di lavoro all'interno del container.
WORKDIR /app

# Copiamo prima il file pom.xml per sfruttare la cache di Docker.
# Se le dipendenze non cambiano, questo strato non verrà rieseguito.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamo il resto del codice sorgente.
COPY src ./src

# Eseguiamo il build di Maven per compilare il codice e creare il pacchetto.
# Saltiamo i test per velocizzare il processo di build dell'immagine.
RUN mvn clean install -DskipTests


# --- FASE 2: RUN ---
# Partiamo da un'immagine base molto più leggera, che contiene solo il Java Runtime (JRE).
# Questo riduce drasticamente le dimensioni dell'immagine finale.
FROM --platform=linux/amd64 eclipse-temurin:17-jre-jammy

# Creiamo un utente non-root per eseguire l'applicazione, per una maggiore sicurezza.
RUN useradd -m -s /bin/bash fenibook
USER fenibook
WORKDIR /home/fenibook/app

# Copiamo il file .jar, compilato nella fase 'build', nella nostra immagine finale.
COPY --from=build /app/target/*.jar app.jar

# Esponiamo la porta 8080, su cui gira di default la nostra applicazione Spring Boot.
EXPOSE 8080

# Comando che viene eseguito all'avvio del container.
ENTRYPOINT ["java", "-jar", "app.jar"]