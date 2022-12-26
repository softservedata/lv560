package ua.hryshko.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "first_name",nullable = false)

    private String firstName;

    @Column(name = "last_name",nullable = false)

    private String lastName;

    @Column(nullable = false,unique = true)
//    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}",
//            message = "Must be a valid e-mail address")
//    @NotBlank
    private String email;

    @Column(name = "phone_number",unique = true)
//    @NotBlank
//    @Pattern(regexp = "^[\\+]?[(]?[0-9]{2}[)]?[0-9]{8,10}$",
//            message = "Must be a valid phone number address")
    private String phoneNumber;


    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "owner",cascade = CascadeType.REMOVE)
    private List<GasMeter> gasMeters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<GasMeter> getGasMeters() {
        return gasMeters;
    }

    public void setGasMeters(List<GasMeter> gasMeters) {
        this.gasMeters = gasMeters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return  getId() != null && getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
