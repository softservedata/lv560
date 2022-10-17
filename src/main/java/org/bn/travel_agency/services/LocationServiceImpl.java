package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.Location;
import org.bn.travel_agency.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
	@Autowired
	private LocationRepository locationRepository;

	@Override
	public void save(Location location) {
		locationRepository.save(location);
	}

	@Override
	public Location findLocationByName(String name) {
		return locationRepository.getLocationByName(name);
	}
}
