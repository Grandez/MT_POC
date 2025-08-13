package com.sdp.poc.threading.database.repo;

import com.sdp.poc.threading.database.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepo extends JpaRepository<Master, Long> {

}
