package sid.repo;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sid.entity.Logger;

public interface LoggerRepo extends JpaRepository<Logger, Integer> {

	@Query("select l from Logger l where dateRequest between :date1 and :date2 ")
	Optional<List<Logger>> findAllbetweenDates(@Param("date1") Instant date1,@Param("date2") Instant date2);
}
