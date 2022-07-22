package com.dh.PI.repositories;

import com.dh.PI.model.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationRepository  extends JpaRepository<Classification, Long> {

    Classification findByUserIdAndProductId(Long userId, Long productId);
}
