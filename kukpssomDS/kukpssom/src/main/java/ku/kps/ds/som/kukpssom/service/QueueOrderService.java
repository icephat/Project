package ku.kps.ds.som.kukpssom.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import ku.kps.ds.som.kukpssom.model.QueueOrder;
import ku.kps.ds.som.kukpssom.repository.QueueOrderRepository;

@Service
public class QueueOrderService {

	@Autowired
	QueueOrderRepository queueOrderRepository;

	public void save(QueueOrder queueOrder) {
		queueOrderRepository.save(queueOrder);
	}

	public void delete(int id) {
		queueOrderRepository.deleteById(id);
	}

	public QueueOrder findById(int id) {
		return queueOrderRepository.findById(id).get();
	}

	public List<QueueOrder> findAll() {
		return (List<QueueOrder>) queueOrderRepository.findAll();

	}

	public List<QueueOrder> findByDateAndStoreId(Date date, int storeId) {
		return queueOrderRepository.findByDateAndStoreId(date, storeId);
	}
}
