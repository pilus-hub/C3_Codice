package it.unicam.ids.c3.persistenza;

import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenditaSpeditaRepository extends JpaRepository<VenditaSpedita, Long> {


}
