package com.severett.restaurants.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="RESTAURANT_TABLE")
public class RestaurantTable implements Serializable {

    private static final long serialVersionUID = 3355578631079182599L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_table_seq_gen")
    @SequenceGenerator(name = "restaurant_table_seq_gen", sequenceName = "restaurant_table_seq")
    private Short id;

    @Column(name="CAPACITY", nullable=false)
    private Short capacity;

    @OneToMany(mappedBy="restaurantTable")
    private Set<Reservation> reservations;

    protected RestaurantTable() {
    }

    public RestaurantTable(Short capacity) {
        this.capacity = capacity;
    }

    public Short getId() {
        return this.id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getCapacity() {
        return this.capacity;
    }

    public void setCapacity(Short capacity) {
        this.capacity = capacity;
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
