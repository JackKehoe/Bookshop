package jack.org.project.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jack.org.project.model.PurchaseHistory;
import jack.org.project.model.User;
import jack.org.project.repository.PurchaseHistoryRepository;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService{

	@Autowired
	private PurchaseHistoryRepository purchaseHistoryRepository;
	
	@Override
	public PurchaseHistory findById(Long id) {
		return purchaseHistoryRepository.findById(id);
	}

	@Override
	public List<PurchaseHistory> findByUser(User user){
		return purchaseHistoryRepository.findByUser(user);
	}

	@Override
	public List<PurchaseHistory> findAll(){
		return purchaseHistoryRepository.findAll();
	}
	
	@Override
	public void save(PurchaseHistory purchaseHistory) {
		purchaseHistory.setUser(purchaseHistory.getUser());
		purchaseHistory.setBook(purchaseHistory.getBook());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		purchaseHistory.setDate(timestamp);
		purchaseHistoryRepository.save(purchaseHistory);
	}
}
