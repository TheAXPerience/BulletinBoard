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
	private final PostingService postingService;
	
	record PostingRequest(
			String poster,
			String content
	) {}
	
	
	public PostingController(PostingService postingService) {
		this.postingService = postingService;
	}
	
	@GetMapping("")
	public List<Posting> getPostings() {
		return this.postingService.getPostings();
	}
	
	@PostMapping("")
	public boolean addPosting(@RequestBody PostingRequest postingRequest) {
		Posting posting = new Posting();
		posting.setPoster(postingRequest.poster());
		posting.setContent(postingRequest.content());
		return this.postingService.savePosting(posting);
	}
	
	@PutMapping("/{postingId}")
	public boolean editPosting(
			@PathVariable("postingId") long postingId,
			@RequestBody PostingRequest postingRequest) {
		Posting posting = this.postingService.getPostingById(postingId);
		if (posting == null) return false;
		posting.setContent(postingRequest.content());
		posting.setPoster(postingRequest.poster());
		return this.postingService.savePosting(posting);
	}
	
	@DeleteMapping("/{postingId}")
	public boolean deletePosting(@PathVariable("postingId") long postingId) {
		return this.postingService.deletePosting(postingId);
	}
}
