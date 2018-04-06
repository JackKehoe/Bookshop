package jack.org.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jack.org.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);
	
}