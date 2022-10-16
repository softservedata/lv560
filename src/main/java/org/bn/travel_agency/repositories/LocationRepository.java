package org.bn.travel_agency.repositories;

import org.bn.travel_agency.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	Location getLocationByName(String name);
}
