package com.bulletinboard.BulletinBoard;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
		return "form";
	}
	
	@PostMapping("/submit")
	public String submitForm(Model model, @ModelAttribute("posting") PostingRequest posting) {
		Posting newPosting = new Posting();
		newPosting.setPoster(posting.poster());
		newPosting.setContent(posting.content());
		this.postingService.savePosting(newPosting);
		
		return "redirect:";
	}
}
