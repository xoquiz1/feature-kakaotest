package kakao.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ApplicationInformation {
	@Id
	@GeneratedValue
	private Long application_id;
	@Column(name = "userId")
	private String user_id;
	private Long product_id;
	private String title;
	private BigDecimal total_investing_amount;
	private BigDecimal my_investing_amount;
	private String investing_at;

	public Long getApplication_id() {
		return application_id;
	}
	public void setApplication_id(Long application_id) {
		this.application_id = application_id;
	}
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
	public BigDecimal getMy_investing_amount() {
		return my_investing_amount;
	}
	public void setMy_investing_amount(BigDecimal my_investing_amount) {
		this.my_investing_amount = my_investing_amount;
	}
	public String getInvesting_at() {
		return investing_at;
	}
	public void setInvesting_at(String investing_at) {
		this.investing_at = investing_at;
	}
}