package sid.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sid.entity.ApiKey;

public interface ApiKeyRepo extends JpaRepository<ApiKey, Integer> {
	
	Optional<ApiKey>  findByUserId(String userId);
	Optional<ApiKey> findByKeyValue(String keyValue);
}
