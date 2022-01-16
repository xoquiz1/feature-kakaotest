package kakao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kakao.model.entity.ProductInformation;

@Repository
public interface ProductRepository extends JpaRepository<ProductInformation, Long> {

	@Query(value = "SELECT * FROM product_information WHERE ?1 BETWEEN started_at AND finished_at", nativeQuery=true)
	List<ProductInformation> findInvestInfoNowDate(String nowDate);
}
