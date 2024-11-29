package org.example.dao;

import org.example.domain.Interviewee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervieweeDao extends JpaRepository<Interviewee, Long> {
    List<Interviewee> findByName(String name);

    Interviewee findByIntervieweeId(String workerID);

    List<Interviewee> findAll();
}
