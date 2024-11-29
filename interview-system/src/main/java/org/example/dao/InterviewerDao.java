package org.example.dao;

import org.example.domain.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewerDao extends JpaRepository<Interviewer, Long> {
    Interviewer findByName(String name);
    Interviewer findByNameAndPassword(String uname, String password);
    Interviewer findById(long uid);
}
