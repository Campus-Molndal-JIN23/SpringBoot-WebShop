# Film- och Användarhanteringssystem

## Beskrivning

Detta projekt är ett Film- och Användarhanteringssystem som använder sig av Spring Boot för att skapa en säker och skalbar applikation. Systemet tillåter användare att registrera sig, logga in, hantera filmer, och utföra administrativa uppgifter som att hämta användarinformation.

## Funktioner

### Användarhantering

- **Registrera användare:** Användare kan registrera sig genom att ange användarnamn och lösenord. Lösenorden lagras säkert med hjälp av BCrypt.

- **Logga in:** Autentisera användare och generera JWT-token för säkra sessioner.

### Filmhantering

- **Hämta alla filmer:** Visa en lista över alla tillgängliga filmer.

- **Hämta en film genom ID:** Hämta detaljerad information om en specifik film baserat på dess ID.

- **Skapa en ny film:** Endast autentiserade användare eller admin kan lägga till nya filmer i systemet.

- **Ta bort en film:** Endast autentiserade användare eller admin har rättigheter att ta bort filmer från systemet.

### Administrativa Åtgärder

- **Hämta alla användare:** Endast användare med ADMIN-roll kan hämta information om alla registrerade användare.
- **Ta bort en användare:** Endast användare med ADMIN-roll kan ta bort en användare.


## Installation

För att köra projektet lokalt, följ dessa steg:

1. **Klona projektet:**

    ```bash
    git clone https://github.com/AhmadAlhayel/Film--och-Anv-ndarhanteringssystem.git
    ```

2. **Bygg och kör projektet med Maven:**

    ```bash
    cd film-user-system
    mvn clean install
    java -jar target/film-user-system.jar
    ```

3. **Applikationen körs på [http://localhost:8080](http://localhost:8080).**

## Teknologier

- **Spring Boot:** Ett kraftfullt ramverk för att bygga Java-baserade webbapplikationer.
- **Spring Security:** Hantering av autentisering och behörighetskontroller.
- **Spring Data JPA:** En del av Spring Data-projektet för att underlätta dataåtkomst.
- **JWT (JSON Web Tokens):** För att hantera autentiseringsmekanismen säkert.
- **Maven:** Ett populärt verktyg för att hantera projektberoenden och bygga projekt.

## API-endpunkter
-Alla kan göra det
- **Registrera användare:**
    ```http
    POST /auth/register
    ```
-Alla kan göra det
- **Logga in:**
    ```http
    POST /auth/login
    ```
-Alla kan göra det
- **Hämta alla filmer:**
    ```http
    GET /film/hämta-films
    ```
-Alla kan göra det
- **Hämta en film genom ID:**
    ```http
    GET /film/hämta{id}
    ```
-Du måsta login
- **Skapa en ny film (autentiserad användare krävs):**
    ```http
    POST /film/post-film
    ```
-Du måsta login
- **Ta bort en film (autentiserad användare krävs):**
    ```http
    DELETE /film/delete{id}
    ```

- **Hämta alla användare (endast för användare med ADMIN-roll):**
    ```http
    GET /admin/hämta-users
    ```
- **Ta bort en användare (endast för användare med ADMIN-roll):**
    ```http
    GET /admin/delete{userId}
    ```

## Bidragande

Om du vill bidra till projektet, följ dessa steg:

1. Forka projektet.
2. Skapa en egen gren (`git checkout -b feature/din-funktion`).
3. Commita dina ändringar (`git commit -am 'Lägg till en ny funktion'`).
4. Pusha till grenen (`git push origin feature/din-funktion`).
5. Öppna en pull-förfrågan.


