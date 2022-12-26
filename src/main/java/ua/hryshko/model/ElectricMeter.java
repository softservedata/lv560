package ua.hryshko.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "electric_meters")
public class ElectricMeter {

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


//    @OneToMany(fetch = FetchType.EAGER,mappedBy = "electricMeter",cascade = CascadeType.REMOVE)
//    private List<History> histories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

//    public List<History> getHistories() {
//        return histories;
//    }
//
//    public void setHistories(List<History> histories) {
//        this.histories = histories;
//    }


}
