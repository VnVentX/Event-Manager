package g3.com.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import g3.com.entity.Member;
import g3.com.security.CustomUserDetails;
import g3.com.service.IMemberService;

@Controller
public class AccountController {
	
	@Autowired
	IMemberService memberService;
	
	@RequestMapping(value = {"/register"})
	public String showRegisterPage() {
		return "register";
	}
	
	@RequestMapping(value = {"/profile"})
	public String showProfilePage(Model model) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		model.addAttribute("currentUser", memberService.getInfo(userDetails.getIdUser()));
		return "profile";
	}
	
	@RequestMapping(value = {"/editProfile"})
	public String editProfile(@ModelAttribute("member") Member member) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		member.setIdUser(userDetails.getIdUser());
		member.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
		boolean isUpdated = memberService.editProfile(member);
		return isUpdated ? "redirect:/profile/?success" : "redirect:/profile/?failed";
	}
}
