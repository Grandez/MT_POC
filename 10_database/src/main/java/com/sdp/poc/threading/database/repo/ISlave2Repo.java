package com.sdp.poc.threading.database.repo;

import com.sdp.poc.threading.database.entity.Slave2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISlave2Repo extends JpaRepository<Slave2, Long> {
    public void clean();
}
