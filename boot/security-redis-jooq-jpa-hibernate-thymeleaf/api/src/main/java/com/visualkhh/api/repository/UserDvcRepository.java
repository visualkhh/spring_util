package com.visualkhh.api.repository;

import com.visualkhh.api.domain.UserDvc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDvcRepository extends JpaRepository<UserDvc, Integer> {

	public UserDvc findFirstByUserSeqAndDvcSeq(Integer userSeq, Integer dvcSeq);

	@Modifying
	@Query("update UserDvc as root set root.ageCd=:ageCd, root.genCd=:genCd where root.userDvcSeq=:userDvcSeq")
	public Integer setUserDvcAgeCdAndGenCd(@Param("userDvcSeq") Integer userDvcSeq, @Param("ageCd")String ageCd, @Param("genCd")String genCd);
}