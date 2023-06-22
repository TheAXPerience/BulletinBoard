package com.bulletinboard.BulletinBoard;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PostingService {
	private final PostingRepository postingRepository;
	
	public PostingService(PostingRepository postingRepository) {
		this.postingRepository = postingRepository;
	}
	
	public List<Posting> getPostings() {
		return this.postingRepository.findAllByOrderByDateCreatedDesc();
	}
	
	public Posting getPostingById(long id) {
		Optional<Posting> posting = this.postingRepository.findById(id);
		if (!posting.isPresent()) return null;
		return posting.get();
	}
	
	public boolean savePosting(Posting posting) {
		this.postingRepository.save(posting);
		return true;
	}
	
	public boolean deletePosting(long postingId) {
		Optional<Posting> posting = this.postingRepository.findById(postingId);
		if (!posting.isPresent()) return false;
		this.postingRepository.deleteById(postingId);
		return true;
	}
}
