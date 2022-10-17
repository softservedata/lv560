package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.Location;

public interface LocationService {
	void save(Location location);

	Location findLocationByName(String name);

}
