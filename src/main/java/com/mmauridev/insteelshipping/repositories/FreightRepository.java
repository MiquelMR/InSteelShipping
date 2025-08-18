package com.mmauridev.insteelshipping.repositories;

import com.mmauridev.insteelshipping.models.Freight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreightRepository extends JpaRepository<Freight, Long> {
}
