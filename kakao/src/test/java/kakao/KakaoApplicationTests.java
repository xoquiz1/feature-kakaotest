package kakao;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import kakao.model.dto.InvestReturn;
import kakao.model.entity.ApplicationInformation;
import kakao.model.entity.ProductInformation;
import kakao.service.KakaoService;

@SpringBootTest
class KakaoApplicationTests {

	@Autowired
	private KakaoService kakaoService;
	
	@Test
	public void findInvestInfoNowDateTest() {
		String nowDate = "2021-03-01 00:00:00";
		
		List<ProductInformation> result = kakaoService.findInvestInfoNowDate(nowDate);
		
		assertNotNull(result);
	}
	
	@Test
	public void findInvestTest() {
		Long productId = 1L;
		
		Optional<ProductInformation> result = kakaoService.findInvest(productId);
		
		assertNotNull(result);
	}
	
	@Test
	@Rollback(value=false)
	public void addApplicationTest() {
		
		ApplicationInformation input = new ApplicationInformation();

		input.setUser_id("1000000001");
		input.setInvesting_at("9999-03-01 15:15:15");
		input.setMy_investing_amount(BigDecimal.TEN);
		input.setProduct_id(1L);
		input.setTitle("TEST");
		input.setTotal_investing_amount(BigDecimal.TEN);
		
		InvestReturn result = kakaoService.addApplication(input);
		
		assertNotNull(result);
	}

}
