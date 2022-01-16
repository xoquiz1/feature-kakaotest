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
		
		// ��ǰ���� �о����
		Optional<ProductInformation> updatePrd = productRepository.findById(applicationInformation.getProduct_id());
		
		updatePrd.ifPresent(selectUser->{
			
			int compareResult = selectUser.getTotal_investing_amount().compareTo(selectUser.getNow_investing_amount().add(applicationInformation.getMy_investing_amount())); 
			updAction = compareResult;
			
			System.out.println(" Before ### ### ### ### " + selectUser.getNow_investing_amount());
			System.out.println(" Before ### ### ### ### " + applicationInformation.getMy_investing_amount());
			
			// �Ѹ����ݾ��� �����ݾ��հ� ��û�ݾ׺��� Ŭ�� �ݾ�, �Ǽ� ���� 
			if (compareResult > 0) {
				// ��ǰ ��������ݾ׿� ��û�ݾ� �ջ�
				selectUser.setNow_investing_amount(selectUser.getNow_investing_amount().add(applicationInformation.getMy_investing_amount()));
				// ��ǰ�� ��û�Ǽ� ����
				selectUser.setInvest_count(selectUser.getInvest_count() + 1);
				productRepository.save(selectUser);
			
			// �Ѹ����ݾװ� �����ݾ��հ� ��û�ݾ��� ������. �ݾ�, �Ǽ� ����, �����Ϸ� ó��
			} else if (compareResult == 0) {
				// ��ǰ ��������ݾ׿� ��û�ݾ� �ջ�
				selectUser.setNow_investing_amount(selectUser.getNow_investing_amount().add(applicationInformation.getMy_investing_amount()));
				// ��ǰ�� ��û�Ǽ� ����
				selectUser.setInvest_count(selectUser.getInvest_count() + 1);
				// �����Ϸ� ó��
				selectUser.setInvest_status("1");
				productRepository.save(selectUser);

			// �Ѹ����ݾ��� �����ݾ��հ� ��û�ݾ��� ������. Sold Out ó��
			} else {
				// ����ó�� �ؾ� ��.
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
