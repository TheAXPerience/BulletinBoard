package com.bulletinboard.BulletinBoard;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/postings")
@Transactional
public class PostingController {
	private final PostingRepository postingRepository;
	
	record PostingRequest(
			String poster,
			String content
	) {}
	
	
	public PostingController(PostingRepository postingRepository) {
		this.postingRepository = postingRepository;
	}
	
	@GetMapping("")
	public List<Posting> getPostings() {
		return this.postingRepository.findAllByOrderByDateCreatedDesc();
	}
	
	@PostMapping("")
	public boolean addPosting(@RequestBody PostingRequest postingRequest) {
		Posting posting = new Posting();
		posting.setPoster(postingRequest.poster());
		posting.setContent(postingRequest.content());
		this.postingRepository.save(posting);
		return true;
	}
	
	@PutMapping("/{postingId}")
	public boolean editPosting(
			@PathVariable("postingId") long postingId,
			@RequestBody PostingRequest postingRequest) {
		Optional<Posting> posting = this.postingRepository.findById(postingId);
		if (!posting.isPresent()) return false;
		Posting p = posting.get();
		p.setContent(postingRequest.content());
		p.setPoster(postingRequest.poster());
		this.postingRepository.save(p);
		return true;
	}
	
	@DeleteMapping("/{postingId}")
	public boolean deletePosting(@PathVariable("postingId") long postingId) {
		Optional<Posting> posting = this.postingRepository.findById(postingId);
		if (!posting.isPresent()) return false;
		this.postingRepository.deleteById(postingId);
		return true;
	}
}
