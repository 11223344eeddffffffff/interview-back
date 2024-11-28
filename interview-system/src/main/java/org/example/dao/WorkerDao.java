package org.example.dao;

import org.example.domain.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerDao extends JpaRepository<Worker, Long> {
    List<Worker> findByName(String name);

    Worker findByWorkerID(String workerID);

    List<Worker> findAll();
}
