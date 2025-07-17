package com.korruptengu.gymcheckinsystem.init;

import com.korruptengu.gymcheckinsystem.entity.*;
import com.korruptengu.gymcheckinsystem.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;
    private final CheckInRepository checkInRepository;
    private final CourseSessionRepository courseSessionRepository;
    private final CourseTypeRepository courseTypeRepository;

    @Override
    public void run(String... args){

        logger.info("Start der Dateninitialisierung...");

        Member member1 = new Member("Peter", "Haupt", "pH@test.de");
        Member member2 = new Member("Clara", "Lustermann", "clara.lustermann@test.com");
        memberRepository.save(member1);
        memberRepository.save(member2);

        memberRepository.saveAll(List.of(
                new Member("Clara", "Lustermann", "clara.lustermann@test.com"),
                new Member("Jonas", "Klein", "jonas.klein@test.com"),
                new Member("Miriam", "Weber", "miriam.weber@test.com"),
                new Member("Felix", "Mayer", "felix.mayer@test.com"),
                new Member("Lena", "Schmidt", "lena.schmidt@test.com"),
                new Member("Tobias", "Richter", "tobias.richter@test.com"),
                new Member("Sophie", "Baumann", "sophie.baumann@test.com"),
                new Member("Leon", "Neumann", "leon.neumann@test.com"),
                new Member("Hannah", "Vogel", "hannah.vogel@test.com"),
                new Member("Nico", "Fischer", "nico.fischer@test.com"),
                new Member("Laura", "Krämer", "laura.kraemer@test.com")
        ));

        Trainer trainer1 = new Trainer("Gustaf", "Gustafen", LocalDate.now());
        Trainer trainer2 = new Trainer("Carl", "Karlson", LocalDate.of(2024,8,1));
        trainerRepository.save(trainer1);
        trainerRepository.save(trainer2);

        CourseType courseType1 = new CourseType("Kraftausdauer Peak", "Kraftausdauer");
        CourseType courseType2 = new CourseType("Hot Yoga", "Yoga", "Erlebe das heiße Yoga Erlebnis.");
        courseTypeRepository.save(courseType1);
        courseTypeRepository.save(courseType2);

        CourseSession courseSession1 = new CourseSession(LocalDateTime.now().minusDays(2).minusHours(4), 45, courseType1, trainer2);
        courseSessionRepository.save(courseSession1);

        CheckIn checkIn1 = new CheckIn(LocalDateTime.now(), member1);
        CheckIn checkIn2 = new CheckIn(LocalDateTime.now().minusHours(2), member2);
        checkInRepository.save(checkIn1);
        checkInRepository.save(checkIn2);

    }
}
