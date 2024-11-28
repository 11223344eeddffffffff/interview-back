package org.example.dao;

import org.example.domain.HR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HRDao extends JpaRepository<HR, Long> {
    HR findByUname(String name);
    HR findByUnameAndPassword(String uname, String password);
    HR findByUid(long uid);
}
