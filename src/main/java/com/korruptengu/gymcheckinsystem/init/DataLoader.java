package com.korruptengu.gymcheckinsystem.init;

import com.korruptengu.gymcheckinsystem.entity.*;
import com.korruptengu.gymcheckinsystem.enums.UserRole;
import com.korruptengu.gymcheckinsystem.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args){

        logger.info("Start der Dateninitialisierung...");

        // === MEMBER 1 ===
        AppUser memberUser1 = AppUser.builder()
                .username("anna.berg")
                .password(encoder.encode("pass123"))
                .uRole(UserRole.MEMBER)
                .firstname("Anna")
                .lastname("Berg")
                .email("anna.berg@test.com")
                .build();

        Member member1 = new Member();
        member1.setJoinDate(LocalDate.of(2023, 1, 15));
        member1.setAppUser(memberUser1);
        memberUser1.setMember(member1);

        // === MEMBER 2 ===
        AppUser memberUser2 = AppUser.builder()
                .username("lukas.meier")
                .password(encoder.encode("pass123"))
                .uRole(UserRole.MEMBER)
                .firstname("Lukas")
                .lastname("Meier")
                .email("lukas.meier@test.com")
                .build();

        Member member2 = new Member();
        member2.setJoinDate(LocalDate.of(2023, 3, 5));
        member2.setAppUser(memberUser2);
        memberUser2.setMember(member2);

        // === TRAINER 1 ===
        AppUser trainerUser1 = AppUser.builder()
                .username("sven.fischer")
                .password(encoder.encode("pass123"))
                .uRole(UserRole.TRAINER)
                .firstname("Sven")
                .lastname("Fischer")
                .email("sven.fischer@test.com")
                .build();

        Trainer trainer1 = new Trainer(LocalDate.of(2022, 10, 1));
        trainer1.setAppUser(trainerUser1);
        trainerUser1.setTrainer(trainer1);

        // === TRAINER 2 ===
        AppUser trainerUser2 = AppUser.builder()
                .username("nina.schulz")
                .password(encoder.encode("pass123"))
                .uRole(UserRole.TRAINER)
                .firstname("Nina")
                .lastname("Schulz")
                .email("nina.schulz@test.com")
                .build();

        Trainer trainer2 = new Trainer(LocalDate.of(2021, 6, 20));
        trainer2.setAppUser(trainerUser2);
        trainerUser2.setTrainer(trainer2);

        // === Speichern ===
        appUserRepository.saveAll(List.of(memberUser1, memberUser2, trainerUser1, trainerUser2));

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


        // === ADMIN ===
        AppUser admin = new AppUser();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("admin123"));
        admin.setFirstname("Max");
        admin.setLastname("Adminmann");
        admin.setEmail("admin@gym.local");
        admin.setURole(UserRole.ADMIN);

        appUserRepository.save(admin);
    }
}
