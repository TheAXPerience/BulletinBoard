package com.bulletinboard.BulletinBoard;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bulletinboard.BulletinBoard.PostingController.PostingRequest;

@Controller
public class BulletinController {
	private PostingService postingService;
	
	public BulletinController(PostingService postingService) {
		this.postingService = postingService;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		List<Posting> lst = this.postingService.getPostings();
		model.addAttribute("postings", lst);
		return "index";
	}
	
	@GetMapping("/form")
	public String createForm(Model model) {
		model.addAttribute("posting", new Posting());
		model.addAttribute("hello", "Create New Post");
		model.addAttribute("link", "/submit");
		model.addAttribute("method", null);
		return "form";
	}
	
	@PostMapping("/submit")
	public String submitForm(Model model, @ModelAttribute("posting") PostingRequest posting) {
		Posting newPosting = new Posting();
		newPosting.setPoster(posting.poster());
		newPosting.setContent(posting.content());
		this.postingService.savePosting(newPosting);
		
		return "redirect:/";
	}
	
	@GetMapping("/{postingId}/edit")
	public String editForm(@PathVariable("postingId") long postingId, Model model) {
		Posting post = this.postingService.getPostingById(postingId);
		model.addAttribute("posting", post);
		model.addAttribute("hello", "Edit Post #" + postingId);
		model.addAttribute("link", "/" + postingId + "/submit");
		model.addAttribute("method", "PUT");
		return "form";
	}
	
	@PutMapping("/{postingId}/submit")
	public String submitEditForm(
			Model model,
			@PathVariable("postingId") long id,
			@ModelAttribute("posting") PostingRequest posting) {
		Posting newPosting = this.postingService.getPostingById(id);
		newPosting.setPoster(posting.poster());
		newPosting.setContent(posting.content());
		this.postingService.savePosting(newPosting);
		
		return "redirect:/";
	}
	
	@DeleteMapping("/{postingId}/delete")
	public String deleteForm(@PathVariable("postingId") long postingId) {
		this.postingService.deletePosting(postingId);
		return "redirect:/";
	}
}
