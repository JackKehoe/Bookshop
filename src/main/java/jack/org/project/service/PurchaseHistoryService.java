package jack.org.project.service;

import java.util.List;

import jack.org.project.model.PurchaseHistory;
import jack.org.project.model.User;

public interface PurchaseHistoryService {

	PurchaseHistory findById(Long id);
	List<PurchaseHistory> findByUser(User user);
	List<PurchaseHistory> findAll();
	void save(PurchaseHistory purchaseHistory);
}
