package com.vhearu.feedback.scheduling;

import com.vhearu.feedback.cassandra.Feedback;
import com.vhearu.feedback.cassandra.FeedbackKey;
import com.vhearu.feedback.plugins.product.ProductFeedBackService;
import com.vhearu.feedback.repository.FeedBackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class PushFeedback {

  private static final Logger LOG = LoggerFactory.getLogger(PushFeedback.class);

  private final FeedBackRepository feedBackRepository;

  public PushFeedback(final FeedBackRepository feedBackRepository) {
    this.feedBackRepository = feedBackRepository;
  }

  @Scheduled(fixedRate = 60000)
  public void pushFeedback() {
    final LocalDateTime startTimeStamp = LocalDateTime.now();
    ProductFeedBackService productFeedBackService = new ProductFeedBackService("http://vforecast-poc-a1:9200/partnerintegration.feedbacks/_search","elastic","changeme");
    List<Feedback> feedbacks = productFeedBackService.getFeedbacks();
    for ( Feedback feedback : feedbacks)
    {
        System.out.println("New feedback has been pulled from source "+feedback.getSource().toString()+" submitted by :"+feedback.getCustomerEmail());
        feedBackRepository.save(feedback);
    }
  }
}
