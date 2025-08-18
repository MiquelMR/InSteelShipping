package com.mmauridev.insteelshipping.repositories;

import com.mmauridev.insteelshipping.models.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {
}
