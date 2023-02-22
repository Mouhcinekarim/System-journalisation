package sid.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sid.Entity.Role;

public interface RepoRole extends JpaRepository<Role,Integer>{

	Optional<Role> findByRoleName(String roleName);
	
}
