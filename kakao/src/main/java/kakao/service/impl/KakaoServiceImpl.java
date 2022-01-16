package kakao.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kakao.model.dto.InvestReturn;
import kakao.model.entity.ApplicationInformation;
import kakao.model.entity.ProductInformation;
import kakao.repository.ApplicationRepository;
import kakao.repository.ProductRepository;
import kakao.service.KakaoService;

@Service
public class KakaoServiceImpl implements KakaoService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	int updAction = 0;
	
	public List<ProductInformation> findInvestInfoNowDate(String nowDate) {

		return productRepository.findInvestInfoNowDate(nowDate);
	}

	public Optional<ProductInformation> findInvest(Long product_id) {
		Optional<ProductInformation> productInformation = productRepository.findById(product_id);
		return productInformation;
	}	
	
	@Transactional
	public InvestReturn addApplication(ApplicationInformation applicationInformation) {
		
		// 상품정보 읽어오기
		Optional<ProductInformation> updatePrd = productRepository.findById(applicationInformation.getProduct_id());
		
		updatePrd.ifPresent(selectUser->{
			
			int compareResult = selectUser.getTotal_investing_amount().compareTo(selectUser.getNow_investing_amount().add(applicationInformation.getMy_investing_amount())); 
			updAction = compareResult;
			
			System.out.println(" Before ### ### ### ### " + selectUser.getNow_investing_amount());
			System.out.println(" Before ### ### ### ### " + applicationInformation.getMy_investing_amount());
			
			// 총모집금액이 누적금액합과 신청금액보다 클때 금액, 건수 증가 
			if (compareResult > 0) {
				// 상품 현재모집금액에 신청금액 합산
				selectUser.setNow_investing_amount(selectUser.getNow_investing_amount().add(applicationInformation.getMy_investing_amount()));
				// 상품의 신청건수 증가
				selectUser.setInvest_count(selectUser.getInvest_count() + 1);
				productRepository.save(selectUser);
			
			// 총모집금액과 누적금액합과 신청금액이 같을떄. 금액, 건수 증가, 모집완료 처리
			} else if (compareResult == 0) {
				// 상품 현재모집금액에 신청금액 합산
				selectUser.setNow_investing_amount(selectUser.getNow_investing_amount().add(applicationInformation.getMy_investing_amount()));
				// 상품의 신청건수 증가
				selectUser.setInvest_count(selectUser.getInvest_count() + 1);
				// 모집완료 처리
				selectUser.setInvest_status("1");
				productRepository.save(selectUser);

			// 총모집금액이 누적금액합과 신청금액이 작을떄. Sold Out 처리
			} else {
				// 예외처리 해야 함.
			}
			
		});

		InvestReturn investReturn = new InvestReturn();
		
		if(updAction >= 0) {
			ApplicationInformation newApplication = applicationRepository.save(applicationInformation);

			investReturn.setUser_id(newApplication.getUser_id());
			investReturn.setProduct_id(newApplication.getProduct_id());
			investReturn.setInvest_amont(newApplication.getMy_investing_amount());
		} else {
			investReturn.setUser_id("Sold Out");
			investReturn.setProduct_id(0L);
			investReturn.setInvest_amont(BigDecimal.ZERO);
		}
		
		return investReturn;
	}

	public List<ApplicationInformation> findByMyInvestInfoUserId(String userId) {
		
		return applicationRepository.findByMyInvestInfoUserId(userId);
		//return applicationRepository.findAll();
	}
}
