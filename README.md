# Gym Check-in System 🏋️‍♂️

Ein webbasiertes System zur Verwaltung von Mitgliedern, Trainern, Kursen und individuellen Trainingseinheiten in einem Fitnessstudio.  
Das Backend basiert auf Spring Boot.  
Ein React-Frontend wird aktuell parallel entwickelt, um das System perspektivisch als Full-Stack-Anwendung zu erweitern.

---

## 📌 Motivation

- Praktische Anwendung moderner Backend-Technologien: Spring Boot, JPA, REST, DTO-Pattern, MapStruct
- Entwicklung einer strukturierten, robusten API mit sauberer Schichtenarchitektur

---

## ⚙️ Tech-Stack

- Java 21
- Spring Boot
- Spring Web (REST)
- Spring Data JPA
- Spring Security (Basic Auth)
- Maven
- H2 / PostgreSQL (konfigurierbar)
- MapStruct (für DTO-Mapping)
- Lombok
- Bean Validation (`javax.validation`)
- Exception Handling mit `@ControllerAdvice` + einheitlichem `ApiError`
- JUnit 5 & Mockito (für Unit-Tests)
- GitHub Actions (CI/CD) ![CI](https://github.com/korruptengu/gym-checkin-system/actions/workflows/ci.yml/badge.svg)

---

## 📁 Features

- Authentifizierung mit Basic Auth + rollenbasierte Autorisierung (`ADMIN`, `MEMBER`, `TRAINER`, `SERVICE`)
- Nutzerverwaltung über `AppUser`, verknüpft mit `Member` oder `Trainer`
- Personal Trainings mit Zeit/Dauer & Buchungssystem für Gruppenkurse
- Validierung aller Nutzereingaben mit `@Valid`, `@NotNull` etc.
- Einheitliches Fehlerformat mit `ApiError` für alle Exceptions
- Trennung von Entities, DTOs, Services, Mappers und Controllern
- PATCH- und PUT-Unterstützung für Teil-/Vollupdates
- Initialisierung von Testdaten über DataLoader
- Automatisierte Builds und Tests mit GitHub Actions

---

## 🧱 Architekturüberblick

- **Schichten:** Entity → DTO → Mapper → Service → Controller
- **DTOs:** Für alle Requests/Responses (POST, PUT, PATCH, Response)
- **Exception Handling:** Einheitlich über `@ControllerAdvice` + `ApiError`
- **Validierung:** Technische & fachliche Regeln über RequestValidator & Custom Exceptions
- **Security:** Rollenbasierter Zugriff via `@PreAuthorize` im Controller

---

## 🛠 Projektstatus

- [x] Member-API (CRUD, Validierung, Fehlerhandling)
- [x] Trainer-API (CRUD, Validierung, Fehlerhandling)
- [x] AppUser + Security (Basic Auth, Rollen, Passwort-Hashing)
- [x] TrainingSession-API (inkl. PATCH/PUT mit UpdateHelper)
- [x] TrainTrainer-Struktur + API
- [x] CRUD für CourseType, CourseSession, CourseBooking
- [ ] Teilnehmer-API pro Kurs-Session (inkl. Buchungsstatus)
- [x] Einheitliches Fehlerformat (`ApiError`) in GlobalExceptionHandler
- [x] Unit-Tests für Service-Schicht (laufender Ausbau)
- [ ] Swagger/OpenAPI-Dokumentation
- [ ] Pagination & Filterung für Listen
- [ ] Frontend mit React + TypeScript (in Planung)

---

## 🚀 Getting Started
### 🔐 Test-Login (Basic Auth)

Ein Administrator ist vordefiniert für den Zugriff auf geschützte Endpunkte:

- **Benutzername:** `admin`
- **Passwort:** `admin123`
- **Rolle:** `ADMIN`
```bash
# Repository klonen
git clone https://github.com/dein-nutzername/gym-checkin-system.git
cd gym-checkin-system

# Starten mit Maven
./mvnw spring-boot:run
```

## 🧪 Datenbankkonfiguration

- **Standard:** H2-In-Memory-Datenbank  → [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **Optional:** PostgreSQL konfigurierbar über `application.properties`

---

## 🔌 Beispiel-Endpunkte (Auszug)

#### Member
- `GET /api/members`
- `POST /api/members`
- `PATCH /api/members/{id}`

#### Trainer
- `GET /api/trainers`

#### AppUser
- `POST /api/app-users`
- `GET /api/app-users/{id}`

#### TrainingSession
- `POST /api/training-sessions`
- `PATCH /api/training-sessions/{id}`

---

## 👨‍💻 Autor

**Roy Wellner**  
2025 – Eigenständiges Lern- und Praxisprojekt im Bereich Java-Backend-Entwicklung

## 📄 Lizenz
MIT License
