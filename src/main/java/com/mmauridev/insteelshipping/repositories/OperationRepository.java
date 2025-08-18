package com.mmauridev.insteelshipping.repositories;

import com.mmauridev.insteelshipping.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
