package net.java.tms.Teller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TellerRepo extends JpaRepository<Teller, Long> {

}
