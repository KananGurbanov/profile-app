package az.edu.turing.profileapp.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Builder
@Table(name = "USERS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "NAME")
    String name;

    @Column(name = "AGE")
    Integer age;

    @Column(name = "IMAGE", columnDefinition = "TEXT")
    String image;
}
