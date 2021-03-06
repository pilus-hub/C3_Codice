package it.unicam.ids.c3.persistenza;

import it.unicam.ids.c3.personale.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, Long>{
}
