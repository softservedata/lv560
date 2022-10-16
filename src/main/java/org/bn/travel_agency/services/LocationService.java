package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.Location;

public interface LocationService {
	Location findLocationByName(String name);

}
