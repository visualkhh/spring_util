package com.visualkhh.cms.repository;

import com.visualkhh.cms.domain.Adm;
import org.springframework.data.jpa.repository.JpaRepository;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
public interface AdmRepository extends JpaRepository<Adm, Integer> {

	public Adm findAdmByAdmLginId(String admLginId);
	public Adm findByAdmLginId(String admLginId);
}
