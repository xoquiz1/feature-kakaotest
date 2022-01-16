package kakao.model.dto;

import java.math.BigDecimal;

public class InvestReturn {
	private String user_id;
	private Long product_id;
	private BigDecimal invest_amont;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public BigDecimal getInvest_amont() {
		return invest_amont;
	}
	public void setInvest_amont(BigDecimal invest_amont) {
		this.invest_amont = invest_amont;
	}
}
