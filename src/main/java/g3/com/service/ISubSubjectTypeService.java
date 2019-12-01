package g3.com.service;

import java.util.List;

import g3.com.entity.SubSubjectType;

public interface ISubSubjectTypeService {

	//Select
	List<SubSubjectType> loadAllSubSubjectType();
	SubSubjectType searchByID(int id);
	int getSubSubjectId(String skillName);
}
