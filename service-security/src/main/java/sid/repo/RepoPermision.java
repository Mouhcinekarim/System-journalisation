package sid.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sid.Entity.Permission;

public interface RepoPermision extends JpaRepository<Permission, Integer>{

	Optional<Permission> findByName(String name);
}
