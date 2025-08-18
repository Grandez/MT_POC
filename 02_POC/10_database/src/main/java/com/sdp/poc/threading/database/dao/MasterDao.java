package com.sdp.poc.threading.database.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MasterDao extends BaseDao {
    public MasterDao() { super("Master"); }
}
