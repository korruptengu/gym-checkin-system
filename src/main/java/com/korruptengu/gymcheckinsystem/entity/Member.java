package com.korruptengu.gymcheckinsystem.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberState state = MemberState.ACTIVE;


    public Member(String firstname, String lastname) {
        if (firstname == null || firstname.isBlank()) throw new IllegalArgumentException("Firstname must not be null or blank");
        if (lastname == null || lastname.isBlank()) throw new IllegalArgumentException("Lastname must not be null or blank");
        this.firstname = firstname.trim().replaceAll("\\s{2,}", " ");
        this.lastname = lastname.trim().replaceAll("\\s{2,}", " ");
    }

    public Member(String firstname, String lastname, MemberState state) {
        if (firstname == null || firstname.isBlank()) throw new IllegalArgumentException("Firstname must not be null or blank");
        if (lastname == null || lastname.isBlank()) throw new IllegalArgumentException("Lastname must not be null or blank");
        if (state == null) throw new NullPointerException("state must not be null");
        this.firstname = firstname.trim().replaceAll("\\s{2,}", " ");
        this.lastname = lastname.trim().replaceAll("\\s{2,}", " ");
        this.state = state;
    }
}
