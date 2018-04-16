package jack.org.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jack.org.project.model.PurchaseHistory;
import jack.org.project.model.User;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long>{

	PurchaseHistory findById(Long id);
	List<PurchaseHistory> findByUser(User user);	
}
