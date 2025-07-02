# Gym Check-in System 🏋️‍♂️

Ein webbasiertes System zur Verwaltung von Mitgliedern, Trainern, Kursen und individuellen Trainingseinheiten in einem Fitnessstudio.  
Das Backend basiert auf Spring Boot.  
Ein React-Frontend ist geplant, um das System perspektivisch als Full-Stack-Anwendung zu erweitern.

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
- JUnit 5 & Mockito (für Unit-Tests)

---

## 📁 Features

- CRUD für alle zentralen Entitäten: `Member`, `Trainer`, `TrainingSession`, `CourseType`, `CourseSession`, `CourseBooking`, `CheckIn`, `TrainTrainer`
- Eingabeverifizierung mit `@Valid`, `@NotNull` etc.
- Globale Fehlerbehandlung mit sprechenden HTTP-Statuscodes
- Trennung von Entities, DTOs, Services, Mappers und Controllern
- Unterstützung für 1:1 Personal Trainings mit Zeit und Dauer
- Kursmanagement mit CourseType, CourseSession und CourseBooking
- `TrainTrainer`: Modelliert das Verhältnis zwischen Ausbildern und auszubildenden Trainern (1:n-Beziehung)
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

## 🛠 Projektstatus

- [x] Member-API (CRUD, Validierung, Fehlerhandling)
- [x] Trainer-API (CRUD, Validierung, Fehlerhandling)
- [x] TrainingSession-API (inkl. PATCH/PUT mit UpdateHelper)
- [x] TrainTrainer-Entität und Struktur
- [x] CRUD für TrainTrainer
- [x] CRUD für CourseType, CourseSession, CourseBooking
- [ ] Teilnehmer-API pro Kurs-Session (inkl. Buchungsstatus)
- [ ] Spezialisierte GET-Endpunkte (z. B. Teilnehmer eines Kurses)
- [x] Unit-Tests für Service-Schicht (**Angefangen**)
- [ ] Swagger/OpenAPI-Dokumentation
- [ ] Authentifizierung mit Spring Security (optional)
- [ ] Pagination & Filterung für Listen
- [ ] Frontend mit React + TypeScript (in Planung)

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

#### Member
- `GET /api/members`
- `POST /api/members`
- `PATCH /api/members/{id}`

#### Trainer
- `GET /api/trainers`
- `GET /api/trainers/{id}/trainees` *(geplant)*

#### TrainingSession
- `POST /api/training-sessions`
- `PATCH /api/training-sessions/{id}`

#### CourseSession
- `GET /api/course-sessions/{id}/participants` *(geplant)*

---

## 👨‍💻 Autor

**Roy Wellner**  
2025 – Eigenständiges Lern- und Praxisprojekt im Bereich Java-Backend-Entwicklung

## 📄 Lizenz
MIT License