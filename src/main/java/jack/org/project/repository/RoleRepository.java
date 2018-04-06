package jack.org.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jack.org.project.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);
	
}
