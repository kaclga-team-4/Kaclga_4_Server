package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "users",
    indexes = @Index(name = "profile_id_index", columnList = "profile_id"))
@SequenceGenerator(name = "USERS_SEQ_GENERATOR", sequenceName = "USERS_SEQ", initialValue = 1, allocationSize = 1)
class User extends BaseTimeEntity {
    @Id
    @Column(columnDefinition = "NUMERIC(19, 0)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ_GENERATOR")
    Long id = 0L;

    @Column(nullable = false, updatable = false, unique = true)
    String username;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserRole userRole;

    @JoinColumn(name = "profile_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE])
    Profile profile;

    userInformation: UserInformation,
    ) :  {
    @Embedded
    UserInformation information  = userInformation;
        protected set

    @Column(unique = true)
        String fcmToken = null;
    protected set

    constructor(
        id: Long = 0L,
        username: String,
        email: String?,
        nickname: String,
        phoneNumber: String? = null,
        gender: Gender,
        birthday: LocalDate,
        socialType: SocialType,
        userRole: UserRole = UserRole.ROLE_USER,
        profile: Profile,
    ) : this(
        id = id,
        username = username,
        socialType = socialType,
        userRole = userRole,
        userInformation = UserInformation(email, nickname, phoneNumber, gender, birthday),
        profile = profile,
        )

    val email: String?
        get() = information.email

    val nickname: String
    get() = information.nickname

    val phoneNumber: String?
        get() = information.phoneNumber

    val gender: Gender
    get() = information.gender

    val birthday: LocalDate
    get() = information.birthday

    fun changePhoneNumber(phoneNumber: String) {
        information = information.copy(phoneNumber = phoneNumber)
    }

    fun changeEmail(email: String) {
        information = information.copy(email = email)
    }

    fun changeInformation(nickname: String) {
        information = information.copy(nickname = nickname)
    }

    fun changeProfile(profileName: String, originFileName: String, profileColor: ProfileColor) {
        profile.changeProfile(profileName, originFileName, profileColor)
    }

    fun changeFcmToken(fcmToken: String?) {
        this.fcmToken = fcmToken
    }

    fun getAgeRange(): Int {
        val now = LocalDate.now()
        var age = now.year - birthday.year
        if (birthday.plusYears(age.toLong()) > now) {
            age -= 1
        }
        return age / 10
    }
}
