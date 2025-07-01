# Gym Check-in System 🏋️‍♂️

Ein System zur Verwaltung von Mitgliedern, Trainern, Kursen und individuellen Trainingseinheiten in einem Fitnessstudio – entwickelt mit Spring Boot (Backend) – mit geplantem React (Frontend).

---

## 📌 Motivation

- Praktische Anwendung moderner Backend-Technologien: Spring Boot, JPA, REST, DTO-Pattern, MapStruct
- Entwicklung einer strukturierten API mit sauberer Schichtenarchitektur
- Erweiterung des Tech-Stacks um React (geplant)

---

## ⚙️ Tech-Stack

- Java 21
- Spring Boot
- Spring Web (REST)
- Spring Data JPA
- Maven
- H2 / PostgreSQL (konfigurierbar)
- MapStruct (für DTO-Mapping)
- Lombok
- Bean Validation (`javax.validation`)
- Exception Handling mit `@ControllerAdvice`

---

## 📁 Features

- CRUD für Mitglieder, Trainer und TrainingSessions
- Eingabeverifizierung mit `@Valid`, `@NotNull` etc.
- Globale Fehlerbehandlung mit sprechenden HTTP-Statuscodes
- Trennung von Entities, DTOs, Services, Mappers und Controllern
- Unterstützung für 1:1 Personal Trainings mit Zeit und Dauer
- Kursmanagement (CourseType, Session, Booking) [in Arbeit]
- Trainerausbildung: Trainer können andere Trainer ausbilden (`TrainTrainer`)
- PATCH- und PUT-Unterstützung für Teil-/Vollupdates
- Dateninitialisierung für Entwicklungszwecke

---

## 🧱 Architekturüberblick

- **Entities**: Member, Trainer, CheckIn, CourseType, CourseSession, CourseBooking, TrainingSession, TrainTrainer
- **DTOs**: Für alle Requests und Responses, inkl. Validierung
- **Mapper**: MapStruct zur Konvertierung zwischen DTO und Entity
- **Services**: Geschäftslogik + Fehlerbehandlung
- **Controller**: RESTful API mit sinnvollen Statuscodes
- **Exception Handling**: Einheitlich über `@ControllerAdvice`

---

## 🚦 Status

Das Backend befindet sich in aktiver Entwicklung.  
Folgende Funktionen sind bereits umgesetzt:

- [x] Member-API (CRUD, Validierung, Fehlerhandling)
- [x] Trainer-API (CRUD, Validierung, Fehlerhandling)
- [x] TrainingSession-API (inkl. PATCH/PUT mit UpdateHelper)
- [x] TrainTrainer-Entität und Struktur
- [ ] CRUD für TrainTrainer
- [ ] CourseType-, CourseSession-, CourseBooking, TrainTrainer-API
- [ ] Spezialisierte GET-Endpunkte (z. B. Teilnehmer eines Kurses)
- [ ] Unit-Tests für Service-Schicht
- [ ] React-Frontend (in Planung)

---

## 🚀 Getting Started

```bash
# Repository klonen
git clone https://github.com/dein-nutzername/gym-checkin-system.git
cd gym-checkin-system

# Starten mit Maven
./mvnw spring-boot:run
```

## 🧪 Datenbankkonfiguration

- **Standard:** H2-In-Memory-Datenbank  
  → Zugriff über: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

- **Optional:** PostgreSQL konfigurierbar über `application.properties`

---

## 🔌 Beispiel-Endpunkte

- `GET /api/members`
- `POST /api/trainers`
- `PATCH /api/training-sessions/{id}`
- `GET /api/trainers/{id}/trainees` *(geplant für TrainTrainer)*
- `GET /api/course-sessions/{id}/participants` *(geplant: Teilnehmerliste mit Status)*

### Fehlerbehandlung (HTTP-Statuscodes)

- `400 Bad Request` – Ungültige Eingabe
- `404 Not Found` – Ressource nicht gefunden
- `409 Conflict` – Konflikt (z. B. doppelte E-Mail)

---

## 👨‍💻 Autor

**Roy**  
2025 – Projekt zur praktischen Anwendung von Spring Boot und React
Lizenz: MIT *(optional anpassbar)*

---

## 📎 Technische To-Do-Liste

- [ ] CRUD für verbleibende Entitäten (`CourseType`, `CourseSession`, `CourseBooking`, `TrainTrainer`)
- [ ] Teilnehmer-API pro Kurs-Session (inkl. Buchungsstatus)
- [ ] Swagger/OpenAPI-Dokumentation
- [ ] Authentifizierung mit Spring Security (optional)
- [ ] Pagination & Filterung für Listen
- [ ] Unit-Tests für Services
- [ ] Frontend mit React + TypeScript