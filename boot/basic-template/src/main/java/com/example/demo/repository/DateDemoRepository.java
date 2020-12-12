package com.example.demo.repository;

import com.example.demo.domain.DateDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DateDemoRepository extends JpaRepository<DateDemo, String> {

    DateDemo findByType(Integer s);

    @Query(value = "SELECT name, type FROM datedemo WHERE type = 1", nativeQuery = true)
    Map<String, String> findByKHH();
}
