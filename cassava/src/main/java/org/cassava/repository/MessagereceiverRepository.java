package org.cassava.repository;

import java.util.List;

import org.cassava.model.ImgVariety;
import org.cassava.model.Messagereceiver;
import org.cassava.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagereceiverRepository extends CrudRepository<Messagereceiver, Integer> {

	
	@Query("from Messagereceiver mr inner join mr.user u inner join mr.message ms where u.userId = :userId order by ms.sendDate DESC")
	public List<Messagereceiver> findByUserId(@Param("userId")int userId);
	
	@Query("from Messagereceiver mr inner join mr.user u inner join mr.message ms where u.userId = :userId and ms.messageId = :messageId")
	public Messagereceiver findByUserIdAndMessageId(@Param("userId")int userId,@Param("messageId")int messageId);
	
	@Query("from Messagereceiver mr inner join mr.user u inner join mr.message ms where u.userId = :userId and mr.readStatus = :readStatus order by ms.sendDate DESC")
	public List<Messagereceiver> findByUserIdAndReadStatus(@Param("userId")int userId,@Param("readStatus")String readStatus);
	
	@Query("select mr from Message m inner join m.user u inner join m.messagereceivers mr inner join mr.user ur where ur = :user")
	public List<Messagereceiver> findMessageAndReceiverDTOByUserId(@Param("user")User user);

}