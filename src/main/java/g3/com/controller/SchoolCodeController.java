package g3.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import g3.com.entity.Event;
import g3.com.entity.SchoolCode;
import g3.com.service.IEventService;
import g3.com.service.ISchoolCodeService;

@Controller
public class SchoolCodeController {

	@Autowired
	ISchoolCodeService schoolCodeService;
	@Autowired
	IEventService eventService;

	@RequestMapping(value = { "/schoolCode" })
	public String showSchoolCodePage(Model model) {
		model.addAttribute("listSchools", schoolCodeService.findAllSchool());
		return "school_code";
	}

	@RequestMapping(value = "/editSchool/{idSchoolCode}")
	public String editSchool(@PathVariable int idSchoolCode, Model model) {
		model.addAttribute("school", schoolCodeService.searchById(idSchoolCode));
		return "edit_school_code";
	}

	@RequestMapping(value = "/saveSchool")
	public String saveSchool(Model model, @ModelAttribute("school") SchoolCode school) {
		boolean flag = schoolCodeService.saveSchoolCode(school);
		if (flag == true) {
			// Update Course Code
			List<Event> evtList = eventService.getBySchoolCode(school.getIdSchoolCode());
			if (evtList != null) {
				eventService.updateCourseCodeBySCode(evtList, school.getUniversityCode());
			}

			model.addAttribute("school", schoolCodeService.searchById(school.getIdSchoolCode()));
			return "redirect:/schoolCode";
		} else {
			return "redirect:/editSchool";
		}
	}
}
