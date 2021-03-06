package it.unicam.ids.c3.persistenza;

import it.unicam.ids.c3.personale.Amministratore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmministratoreRepository extends JpaRepository<Amministratore, Long> {
}
