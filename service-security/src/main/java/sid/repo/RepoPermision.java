package sid.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sid.Entity.Permission;

public interface RepoPermision extends JpaRepository<Permission, Integer>{

}
