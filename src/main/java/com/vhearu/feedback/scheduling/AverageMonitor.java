package com.vhearu.feedback.scheduling;

import com.vhearu.feedback.cassandra.Average;
import com.vhearu.feedback.cassandra.AverageKey;
import com.vhearu.feedback.repository.AverageRepository;
import com.vhearu.feedback.repository.FeedBackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AverageMonitor {

  private static final Logger LOG = LoggerFactory.getLogger(AverageMonitor.class);
  private final FeedBackRepository feedBackRepository;
  private final AverageRepository averageRepository;

  public AverageMonitor(
          final FeedBackRepository feedBackRepository, final AverageRepository averageRepository) {
    this.feedBackRepository = feedBackRepository;
    this.averageRepository = averageRepository;
  }

}
