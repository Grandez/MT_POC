package com.sdp.poc.threading.repo;

import com.sdp.poc.threading.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepo extends JpaRepository<Master, Long> {

}
