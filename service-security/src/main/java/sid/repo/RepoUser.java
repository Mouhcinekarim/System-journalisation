package sid.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import sid.Entity.User;

public interface RepoUser extends JpaRepository<User,UUID> {

}
