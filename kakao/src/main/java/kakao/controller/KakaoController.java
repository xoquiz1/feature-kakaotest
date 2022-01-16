package kakao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kakao.model.dto.InvestReturn;
import kakao.model.entity.ApplicationInformation;
import kakao.model.entity.ProductInformation;
import kakao.service.KakaoService;

@RestController
@RequestMapping("/index")
public class KakaoController {
	
	@Autowired
	KakaoService kakaoService;
	
	@GetMapping("/investList")
	public List<ProductInformation> findInvestInfoNowDate(String nowDate) {
		
		return kakaoService.findInvestInfoNowDate(nowDate);
	}

	@GetMapping("/invest")
	public Optional<ProductInformation> findInvest(Long product_id) {
		
		return kakaoService.findInvest(product_id);
	}
	
	@GetMapping("/my-investList")
	public List<ApplicationInformation> findByMyInvestInfoUserId(HttpEntity<byte[]> requestEntity) {
	
		return kakaoService.findByMyInvestInfoUserId(requestEntity.getHeaders().getFirst("x-user-id"));
	}
	
	@PostMapping("/invest")
	public InvestReturn addApplication(@RequestHeader("x-user-id") String userId, @RequestBody ApplicationInformation applicationInformation) {
		
		applicationInformation.setUser_id(userId);

		return kakaoService.addApplication(applicationInformation);
	}
	
}
