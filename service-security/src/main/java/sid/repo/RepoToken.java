package sid.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import sid.Entity.Token;

public interface RepoToken extends JpaRepository<Token, Integer>{

	Optional<Token> findbyToken(UUID token);
}
