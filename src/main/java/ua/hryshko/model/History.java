package ua.hryshko.model;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "last_reading")
    private Long lastReading;

    @Column(name = "new_reading")
    private Long newReading;

    @Column(name = "consumed")
    private Long consumed;

    @Column(name = "balance_before_paid")
    private Float balanceBeforePaid;

    @Column(name = "balance_after_paid")
    private Float balanceAfterPaid;

    @Column(name = "meter_id")
    private Long meterId;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLastReading() {
        return lastReading;
    }

    public void setLastReading(Long lastReading) {
        this.lastReading = lastReading;
    }

    public Long getNewReading() {
        return newReading;
    }

    public void setNewReading(Long newReading) {
        this.newReading = newReading;
    }

    public Long getConsumed() {
        return consumed;
    }

    public void setConsumed(Long consumed) {
        this.consumed = consumed;
    }

    public Float getBalanceBeforePaid() {
        return balanceBeforePaid;
    }

    public void setBalanceBeforePaid(Float balanceBeforePaid) {
        this.balanceBeforePaid = balanceBeforePaid;
    }

    public Float getBalanceAfterPaid() {
        return balanceAfterPaid;
    }

    public void setBalanceAfterPaid(Float balanceAfterPaid) {
        this.balanceAfterPaid = balanceAfterPaid;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }
}
