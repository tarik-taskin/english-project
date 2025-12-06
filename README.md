# English Learning App Backend

A simplified Spring Boot backend application for managing English learning vocabulary decks using spaced repetition concepts.

## Features
- **Word Management**: Add, update, delete, and list words.
- **Deck Management**: Manage spaced repetition decks (Levels 1-8).
- **Spaced Repetition**: Track "Last Repeat Date" for decks to schedule reviews.

## Technologies
- **Java 17**
- **Spring Boot 3.5.3** (Web, Data JPA)
- **PostgreSQL** (Database)
- **Lombok** (Boilerplate reduction)
- **Maven** (Build tool)

## Setup & Run

### Prerequisites
- Java 17+ installed
- Maven installed (or use `./mvnw`)
- PostgreSQL installed and running

### Database Configuration
Ensure your `src/main/resources/application.properties` (or `yaml`) is configured for your local PostgreSQL instance:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Running the App
```bash
mvn spring-boot:run
```
The application will start on `http://localhost:8080`.

---

## API Documentation

### Deck Controller (`/deck`)

#### 1. Get All Decks
Retrieves a list of all decks with their last repeat dates.
- **URL**: `/deck/getAll`
- **Method**: `GET`
- **Response**: `List<DeckDto>`
```json
[
  {
    "deckID": 1,
    "sonTekrarTarihi": "2023-10-27T00:00:00.000+00:00"
  }
]
```

#### 2. Update Deck Last Repeat Date
Updates the "last repeated" date of a specific deck to today.
- **URL**: `/deck/updateLastRepeatDate/{id}`
- **Method**: `PUT`
- **Path Variables**:
    - `id` (Long): ID of the deck (1-8)
- **Response**: `Boolean` (true if successful)

### Word Controller (`/word`)

#### 1. Save New Word
Adds a new Turkish-English word pair.
- **URL**: `/word/save`
- **Method**: `POST`
- **Body**:
```json
{
  "english": "Apple",
  "turkish": "Elma"
}
```
- **Response**: `WordDto`
```json
{
  "wordID": 101,
  "english": "Apple",
  "turkish": "Elma",
  "eklenmeTarihi": "2023-10-27T00:00:00.000+00:00",
  "desteNo": 1
}
```

#### 2. Get All Words
Retrieves all words in the system.
- **URL**: `/word/getAll`
- **Method**: `GET`
- **Response**: `List<WordDto>`

#### 3. Update Word Translation
Updates the English/Turkish text of an existing word.
- **URL**: `/word/update/{id}`
- **Method**: `PUT`
- **Path Variables**: `id` (Long)
- **Body**:
```json
{
  "english": "Updated Apple",
  "turkish": "Güncellenmiş Elma"
}
```
- **Response**: `WordDto`

#### 4. Raise Deck Level
Moves a word up to the next deck (e.g., Deck 1 -> Deck 2). Max deck is 8.
- **URL**: `/word/raiseDeck/{id}`
- **Method**: `PUT`
- **Path Variables**: `id` (Long)
- **Response**: `WordDto` (with updated `desteNo`)

#### 5. Drop Deck Level
Moves a word down to the previous deck (e.g., Deck 2 -> Deck 1). Min deck is 1.
- **URL**: `/word/dropDeck/{id}`
- **Method**: `PUT`
- **Path Variables**: `id` (Long)
- **Response**: `WordDto` (with updated `desteNo`)

#### 6. Delete Word
Deletes a word permanently.
- **URL**: `/word/delete/{id}`
- **Method**: `DELETE`
- **Path Variables**: `id` (Long)
- **Response**: `Boolean`
