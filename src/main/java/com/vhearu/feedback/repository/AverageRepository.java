package com.vhearu.feedback.repository;

import com.vhearu.feedback.cassandra.AverageKey;
import com.vhearu.feedback.cassandra.Average;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AverageRepository extends CassandraRepository<Average, AverageKey>{
}
