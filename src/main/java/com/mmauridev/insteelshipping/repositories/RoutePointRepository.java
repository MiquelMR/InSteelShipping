package com.mmauridev.insteelshipping.repositories;

import com.mmauridev.insteelshipping.models.RoutePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutePointRepository extends JpaRepository<RoutePoint, Long> {
}
