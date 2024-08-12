package az.edu.turing.profileapp.dao.entity;


import az.edu.turing.profileapp.model.enums.SocialMediaType;
import az.edu.turing.profileapp.model.enums.Status;
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
@Table(name = "PROFILES")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String mailAddress;

    String password;

    @Enumerated(EnumType.STRING)
    Status status;

    @Enumerated(EnumType.STRING)
    SocialMediaType socialMediaType;

    String image;

    @Version
    Long version;
}
