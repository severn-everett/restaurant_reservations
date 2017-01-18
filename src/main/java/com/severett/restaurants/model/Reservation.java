package com.severett.restaurants.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="RESERVATION")
public class Reservation extends AuditableEntity implements Serializable {

    private static final long serialVersionUID = -3060520479808724179L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq_gen")
    @SequenceGenerator(name = "reservation_seq_gen", sequenceName = "reservation_seq")
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RESTAURANT_TABLE_ID")
    private RestaurantTable restaurantTable;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GUEST_ID")
    private Guest guest;

    @Column(name="START_TIME", nullable=false)
    private Date startTime;

    @Column(name="END_TIME", nullable=false)
    private Date endTime;

    protected Reservation() {
    }

    public Reservation(RestaurantTable restaurantTable, Guest guest, Date startTime, Date endTime) {
        this.restaurantTable = restaurantTable;
        this.guest = guest;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RestaurantTable getRestaurantTable() {
        return this.restaurantTable;
    }

    public void setRestaurantTable(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
    }
    
    public Guest getGuest() {
        return this.guest;
    }
    
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
