package com.infy.hotel.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HOTEL_DETAILS")
public class Hotel {
    @Id
    @Column(name = "HOTEL_ID")
    private String hotelId;
    private String hotelName;
    private String hotelLocation;
    private String about;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(hotelId, hotel.hotelId) && Objects.equals(hotelName, hotel.hotelName) && Objects.equals(hotelLocation, hotel.hotelLocation) && Objects.equals(about, hotel.about);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, hotelName, hotelLocation, about);
    }
}
