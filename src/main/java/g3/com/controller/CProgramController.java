package g3.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import g3.com.entity.CProgram;
import g3.com.entity.Event;
import g3.com.repository.CProgramRepository;
import g3.com.service.ICProgramService;
import g3.com.service.IEventService;

@Controller
public class CProgramController {
	@Autowired
	ICProgramService icProgramService;
	@Autowired
	IEventService eventService;

	@Autowired
	private CProgramRepository cProgramRepo;

	@RequestMapping(value = "/campusLink")
	public String showCampusLink(Model model) {
		model.addAttribute("campusList", icProgramService.loadAllCProgram());
		return "campusLink";
	}

	@PostMapping(value = "/saveCProgram")
	public String save(CProgram cProgram) {
		if (cProgramRepo.save(cProgram) != null) {
			List<Event> evtList = eventService.getByCprogramCode(cProgram.getIdCProgram());
			if(evtList != null) {
				eventService.updateCourseCodeByCPCode(evtList, cProgram.getProgramCode());
			}
		}
		return "redirect:/campusLink";
	}

	@PostMapping(value = "/disableCProgram")
	public String disable(int idCProgram) {
		CProgram cProgram = icProgramService.loadCProgram(idCProgram);
		boolean isDisable = cProgram.isStatus();
		if (isDisable) {
			cProgram.setStatus(false);
		} else {
			cProgram.setStatus(true);
		}
		cProgramRepo.save(cProgram);
		return "redirect:/campusLink";
	}

	@GetMapping(value = "/findId")
	@ResponseBody
	public CProgram findOne(int id) {
		return icProgramService.loadCProgram(id);
	}

}
