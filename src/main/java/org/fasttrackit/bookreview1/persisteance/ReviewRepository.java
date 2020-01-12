package org.fasttrackit.bookreview1.persisteance;

import org.fasttrackit.bookreview1.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByBookId(Long BookId, Pageable pageable);
}

