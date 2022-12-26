package ua.hryshko.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "gas_meters")
public class GasMeter {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "meter_reading",nullable = false)
    private Long reading;

    @Column(nullable = false)
    private Float payment;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "owner_id",nullable = false)
    private User owner;


//    @OneToMany(fetch = FetchType.EAGER,mappedBy = "gasMeter",cascade = CascadeType.REMOVE)
//    private List<History> histories;

//    public List<History> getHistories() {
//        return histories;
//    }
//
//    public void setHistories(List<History> histories) {
//        this.histories = histories;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReading() {
        return reading;
    }

    public void setReading(Long reading) {
        this.reading = reading;
    }

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GasMeter gasMeter = (GasMeter) o;
        return Objects.equals(id, gasMeter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
