package kakao.model.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductInformation {
	@Id
	private Long product_id;
	private String title;
	private BigDecimal total_investing_amount;
	private BigDecimal now_investing_amount;
	private int invest_count;
	private String invest_status;
	private String started_at;
	private String finished_at;
	
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getTotal_investing_amount() {
		return total_investing_amount;
	}
	public void setTotal_investing_amount(BigDecimal total_investing_amount) {
		this.total_investing_amount = total_investing_amount;
	}
	public BigDecimal getNow_investing_amount() {
		return now_investing_amount;
	}
	public void setNow_investing_amount(BigDecimal now_investing_amount) {
		this.now_investing_amount = now_investing_amount;
	}
	public int getInvest_count() {
		return invest_count;
	}
	public void setInvest_count(int invest_count) {
		this.invest_count = invest_count;
	}
	public String getInvest_status() {
		return invest_status;
	}
	public void setInvest_status(String invest_status) {
		this.invest_status = invest_status;
	}
	public String getStarted_at() {
		return started_at;
	}
	public void setStarted_at(String started_at) {
		this.started_at = started_at;
	}
	public String getFinished_at() {
		return finished_at;
	}
	public void setFinished_at(String finished_at) {
		this.finished_at = finished_at;
	}
}
