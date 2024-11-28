package org.example.service;

import org.example.domain.Worker;
import org.example.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkerService {
    Result<Worker> loginService(Worker worker);

    Result<Worker> registerService(Worker worker);

    Worker findByWorkerIDService(String workerID);

    List<Worker> findByNameService(String name);

    List<Worker> findALlService();

    Worker updateWorkerInfoService(Worker worker);

    Worker deleteWorkerService(Worker worker);
}
