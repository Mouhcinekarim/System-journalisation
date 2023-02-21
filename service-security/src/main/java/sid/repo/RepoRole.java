package sid.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sid.Entity.Role;

public interface RepoRole extends JpaRepository<Role,Integer>{

}
