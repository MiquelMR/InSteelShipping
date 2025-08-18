package com.mmauridev.insteelshipping.repositories;

import com.mmauridev.insteelshipping.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
