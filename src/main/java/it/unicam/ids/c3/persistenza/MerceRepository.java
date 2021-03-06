package it.unicam.ids.c3.persistenza;


import it.unicam.ids.c3.merce.Merce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerceRepository extends JpaRepository<Merce, Long> {
}
