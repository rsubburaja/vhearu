package com.vhearu.feedback.repository;

import com.vhearu.feedback.cassandra.Feedback;
import com.vhearu.feedback.cassandra.FeedbackKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface FeedBackRepository extends CassandraRepository<Feedback, FeedbackKey> {

}
