package com.payment.controller;

import java.util.List;


import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment.repository.PaymentRepository;
import com.payment.paymententity.Payments;

@RestController
public class HomeController {

	@Autowired
	public PaymentRepository paymentRepo;

	@GetMapping("/")
	public String home(Model m) {

		 List<Payments> list = paymentRepo.findAll(); 
		 m.addAttribute("all_products", list);
		 return "index";

		//return findPaginateAndSorting(0,"id","asc", m);
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginateAndSorting(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model m) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo, 3,sort);

		Page<Payments> page = paymentRepo.findAll(pageable);

		List<Payments> list = page.getContent();

		m.addAttribute("pageNo", pageNo);
		m.addAttribute("totalElements", page.getTotalElements());
		m.addAttribute("totalPage", page.getTotalPages());
		m.addAttribute("all_payments", list);
		
		m.addAttribute("sortField",sortField);
		m.addAttribute("sortDir",sortDir);
		m.addAttribute("revSortDir",sortDir.equals("asc") ? "desc" : "asc");
		

		return "index";
	}

	@GetMapping("/load_form")
	public String loadForm() {
		return "add";
	}

	@GetMapping("/edit_form/{userId}")
	public String editForm(@PathVariable(value = "userId") long userId, Model m) {

		Optional<Payments> payments = paymentRepo.findById(userId);

		Payments pro = payments.get();
		m.addAttribute("payment", pro);

		return "edit";
	}

	@PostMapping("/save_payment")
	public String savePayment(@ModelAttribute Payments payments, HttpSession session) {

		paymentRepo.save(payments);
		session.setAttribute("msg", "Payment Added Sucessfully..");

		return "redirect:/load_form";
	}

	@PostMapping("/update_payment")
	public String updatePayment(@ModelAttribute Payments payments, HttpSession session) {

		paymentRepo.save(payments);
		session.setAttribute("msg", "Payment Update Sucessfully..");

		return "redirect:/";
	}

	@GetMapping("/delete/{userId}")
	public String deletePayment(@PathVariable(value = "userId") long userId, HttpSession session) {
		paymentRepo.deleteById(userId);
		session.setAttribute("msg", "Payment Delete Sucessfully..");

		return "redirect:/";

	}

}
