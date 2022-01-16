package kakao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kakao.model.entity.ApplicationInformation;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationInformation, Long> {
	
	@Query(value = "SELECT * FROM application_information WHERE user_id = ?1", nativeQuery=true)
	List<ApplicationInformation> findByMyInvestInfoUserId(String userId);
}
