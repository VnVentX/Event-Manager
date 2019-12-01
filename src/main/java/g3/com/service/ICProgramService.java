package g3.com.service;

import java.util.List;

import g3.com.entity.CProgram;

public interface ICProgramService {

	//Select
	List<CProgram> loadAllCProgram();
	CProgram loadCProgram(int id);
	List<CProgram> loadAvailableCP();
	int getCProgramId(String courseName);
}
