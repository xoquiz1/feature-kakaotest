package kakao.service;

import java.util.List;
import java.util.Optional;

import kakao.model.dto.InvestReturn;
import kakao.model.entity.ApplicationInformation;
import kakao.model.entity.ProductInformation;

public interface KakaoService {
	
	public List<ProductInformation> findInvestInfoNowDate(String nowDate);
	public Optional<ProductInformation> findInvest(Long product_id);
	public InvestReturn addApplication(ApplicationInformation applicationInformation);
	public List<ApplicationInformation> findByMyInvestInfoUserId(String userId);
}
