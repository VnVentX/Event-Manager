package g3.com.service;

import g3.com.entity.Member;

public interface IMemberService {
	//Select
	Member getInfo(int id);
	
	//Update
	boolean editProfile(Member member);
}
