package com.demo.librarymanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.librarymanagementsystem.entity.Contact;
import com.demo.librarymanagementsystem.entity.Publisher;
import com.demo.librarymanagementsystem.service.PublisherService;

@Controller
public class PublisherController {
	
	private final RestTemplate restTemplate;
	private final PublisherService publisherService;

	public PublisherController(PublisherService publisherService,RestTemplate restTemplate) {
		this.publisherService = publisherService;
		this.restTemplate = restTemplate;
	}

	@RequestMapping("/publishers")
	public String findAllPublishers(Model model) {
		final List<Publisher> publishers = publisherService.findAllPublishers();

		model.addAttribute("publishers", publishers);
		return "list-publishers";
	}

	@RequestMapping("/publisher/{id}")
	public String findPublisherById(@PathVariable("id") Long id, Model model) {
		final Publisher publisher = publisherService.findPublisherById(id);
		
		model.addAttribute("publisher", publisher);
		return "list-publisher";
	}

	@GetMapping("/addPublisher")
	public String showCreateForm(Publisher publisher) {
		return "add-publisher";
	}

	@RequestMapping("/add-publisher")
	public String createPublisher(Publisher publisher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-publisher";
		}

		publisherService.createPublisher(publisher);
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}

	@GetMapping("/updatePublisher/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Publisher publisher = publisherService.findPublisherById(id);

		model.addAttribute("publisher", publisher);
		return "update-publisher";
	}

	@RequestMapping("/update-publisher/{id}")
	public String updatePublisher(@PathVariable("id") Long id, Publisher publisher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			publisher.setId(id);
			return "update-publishers";
		}

		publisherService.updatePublisher(publisher);
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}

	@RequestMapping("/remove-publisher/{id}")
	public String deletePublisher(@PathVariable("id") Long id, Model model) {
		publisherService.deletePublisher(id);

		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}
	
	@RequestMapping("/publisher/contact/{pub_id}")
	public ModelAndView viewPublisherContact(@PathVariable(name="pub_id") Long pub_id) {
		ModelAndView mav = new ModelAndView("view_contact");
		ResponseEntity<Contact> res = new RestTemplate().getForEntity("http://localhost:8092/contact/publisher/{pub_id}",Contact.class,pub_id);
		Contact contact = res.getBody();
		mav.addObject("contact",contact);
		return mav;
	}
	
	

}
