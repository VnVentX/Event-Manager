package g3.com.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import g3.com.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	public Member findByEmail(String email);

	@Query("select a from Member a, UserRole b where a.idUser=b.userid and b.role = 'ROLE_MEMBER'")
	public List<Member> findMemberByRole();

	@Transactional
	@Modifying
	@Query("update Member a set a.enabled= ?1 where a.idUser= ?2")
	public void updateMemberSetEnabledForAuthorid(int st, int id);

	@Query("select a from Member a, UserRole b where a.idUser=b.userid and b.role = 'ROLE_MEMBER' and a.email like %:email%")
	public List<Member> findMemberByEmail(@Param("email") String email);

	@Transactional
	@Modifying
	@Query("update Member a set a.username= ?1, a.phone= ?2, a.updateTime = ?3 where a.idUser= ?4")
	public void updateMemberInfo(String username, String phone, Timestamp updateTime, int id);
}
