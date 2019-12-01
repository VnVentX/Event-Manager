package g3.com.service;


import java.util.List;

import g3.com.entity.SchoolCode;

public interface ISchoolCodeService {
	//Select
	SchoolCode searchById (int id);

	List<SchoolCode> findAllSchool();
	
	int getSchoolCodeId(String name);
	//Update
	boolean saveSchoolCode(SchoolCode school);
}
