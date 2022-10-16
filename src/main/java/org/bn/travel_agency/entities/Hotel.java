package org.bn.travel_agency.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column(name = "number_of_rooms")
	private int numberOfRooms;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id")
	private Location location;
	@Transient
	private String locationName;

	@Transient
	private int priceForRoom;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "hotel")
	private Set<Room> rooms;

	public Hotel() {
	}


	@Override
	public String toString() {
		return "Hotel{" +
				"id=" + id +
				", name='" + name + '\'' +
				", numberOfRooms=" + numberOfRooms +
				", location=" + location +
				", locationName=" + locationName +
				'}';
	}

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

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getPriceForRoom() {
		return priceForRoom;
	}

	public void setPriceForRoom(int priceForRoom) {
		this.priceForRoom = priceForRoom;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

}
