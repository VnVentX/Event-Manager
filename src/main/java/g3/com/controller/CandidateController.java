package g3.com.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import g3.com.dto.CandidateDTO;
import g3.com.entity.Candidate;
import g3.com.entity.CandidateEvent;
import g3.com.security.CustomUserDetails;
import g3.com.service.ICandidateEventService;
import g3.com.service.ICandidateService;
import g3.com.service.ISchoolCodeService;
import g3.com.service.ISubSubjectTypeService;

@Controller
public class CandidateController {

	@Autowired
	ICandidateService candidateSrv;

	@Autowired
	ISubSubjectTypeService subSubjectTypeService;

	@Autowired
	ISchoolCodeService schoolCodeService;

	@Autowired
	ICandidateEventService candidateEventSrv;

	@RequestMapping(value = { "/manageCandidate" })
	public String showManageCandidatePage(Model model) {
		model.addAttribute("listCandidate", candidateSrv.showAllCandidate());
		return "candidate_manager";
	}

	@RequestMapping(value = { "/addCandidatePage" })
	public String loadAddPage(Model model) {
		model.addAttribute("listSubSubjectType", subSubjectTypeService.loadAllSubSubjectType());
		model.addAttribute("listSchool", schoolCodeService.findAllSchool());
		return "create_candidate";
	}

	@RequestMapping(value = { "/saveCandidate" })
	public String saveCandidate(@ModelAttribute("candidate") Candidate candidate, Model model) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		long milis = System.currentTimeMillis();
		Date date = new Date(milis);
		candidate.setAccount(candidate.getEmail());
		candidate.setGpa(0);
		candidate.setNationalId(0);
		candidate.setCvNumber(0);
		candidate.setUpdate_by(userDetails.getUsername());
		candidate.setUpdateDate(date);
		boolean flag = candidateSrv.saveCandidate(candidate);
		if (flag == false) {
			model.addAttribute("candidate", candidateSrv.loadCandidateByIdEvent(candidate.getIdCandidate()));
			return "redirect:/addCandidatePage";
		} else {
			return "redirect:/manageCandidate";
		}
	}

	@RequestMapping(value = "/candidateDetail/{idCandidate}")
	public String loadCandidateDetail(Model model, @PathVariable int idCandidate) {
		model.addAttribute("candidateDTO", candidateSrv.showCandidateDetail(idCandidate));
		model.addAttribute("candidateEventList", candidateEventSrv.loadCandidateEvent(idCandidate));
		model.addAttribute("listSchool", schoolCodeService.findAllSchool());
		model.addAttribute("listSkill", subSubjectTypeService.loadAllSubSubjectType());
		return "candidate_detail";
	}

	@GetMapping(value = "/findCE")
	@ResponseBody
	public CandidateEvent findCE(int id) {
		return candidateEventSrv.findCEByID(id);
	}

	@RequestMapping(value = "/editCandidate")
	public String updateCandidate(Model model, @ModelAttribute("candidateDTO") CandidateDTO canDTO) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		long milis = System.currentTimeMillis();
		Date date = new Date(milis);
		Candidate a = new Candidate(canDTO);
		a.setUpdate_by(userDetails.getUsername());
		a.setUpdateDate(date);
		candidateSrv.saveCandidate(a);
		model.addAttribute("candidateDTO", candidateSrv.showCandidateDetail(canDTO.getIdCandidate()));
		model.addAttribute("candidateEventList", candidateEventSrv.loadCandidateEvent(canDTO.getIdCandidate()));
		model.addAttribute("listSchool", schoolCodeService.findAllSchool());
		model.addAttribute("listSkill", subSubjectTypeService.loadAllSubSubjectType());
		return "candidate_detail";
	}

	@RequestMapping(value = "/updateCEvent")
	public String updateCEvent(CandidateEvent ce, Model model) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		long milis = System.currentTimeMillis();
		Date date = new Date(milis);
		ce.setUpdateBy(userDetails.getUsername());
		ce.setUpdateDate(date);
		candidateEventSrv.updateCE(ce);
		int idCandidate = candidateEventSrv.findCEByID(ce.getId()).getIdCandidate();
		model.addAttribute("candidateDTO", candidateSrv.showCandidateDetail(idCandidate));
		model.addAttribute("candidateEventList", candidateEventSrv.loadCandidateEvent(idCandidate));
		model.addAttribute("listSchool", schoolCodeService.findAllSchool());
		model.addAttribute("listSkill", subSubjectTypeService.loadAllSubSubjectType());
		return "candidate_detail";
	}
}
