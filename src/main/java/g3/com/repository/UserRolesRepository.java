package g3.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import g3.com.entity.UserRole;


@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Integer> {
	@Query("select a.role from UserRole a, Member b where b.email=?1 and a.userid=b.idUser")
	public List<String> findRoleByEmail(String email);
}
