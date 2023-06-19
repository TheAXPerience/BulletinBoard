package com.bulletinboard.BulletinBoard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, Long> {
	// Get Postings sorted by date, descending
	List<Posting> findAllByOrderByDateCreatedDesc();
}
