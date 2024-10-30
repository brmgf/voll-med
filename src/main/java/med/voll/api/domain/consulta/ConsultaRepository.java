package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM consulta c " +
            "WHERE c.medico.id = :medicoId " +
            "AND DATE(c.dataHora) = DATE(:dataHora) " +
            "AND c.dataHora BETWEEN :umaHoraAMenos AND :umaHoraAMais")
    boolean existeConsultaNoMesmoHorario(Long medicoId,
                                         LocalDateTime dataHora,
                                         LocalDateTime umaHoraAMenos,
                                         LocalDateTime umaHoraAMais
    );
}
