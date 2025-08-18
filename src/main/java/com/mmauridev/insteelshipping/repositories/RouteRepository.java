package com.mmauridev.insteelshipping.repositories;

import com.mmauridev.insteelshipping.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
