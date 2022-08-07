package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table
public class Tivi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "NVARCHAR(50)", nullable = false)
	private String name;
	
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String description;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private Short screenSize; // kích thước màn hình
	
	@ManyToOne
    @JoinColumn(nullable = false)
	private OperatingSystem os; // hệ điều hành
	
	@ManyToOne
    @JoinColumn(nullable = false)
	private Resolution resolution; // độ phân giải
	
	@ManyToOne
    @JoinColumn(nullable = false)
	private ScreenType screenType; // loại màn hình
	
	@ManyToOne
    @JoinColumn(nullable = false)
	private Brand brand; // hãng
	
	@ManyToOne
    @JoinColumn(nullable = false)
	private Account createUser;
	
	@Column(nullable = false)
	private Timestamp createdDate;
	
	@ManyToOne
    @JoinColumn(nullable = false)
	private Account lastModifiedUser;
	
	@Column(nullable = false)
	private Timestamp lastModifiedDate;
	
	@Column(nullable = false)
	private Boolean deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Short getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(Short screenSize) {
		this.screenSize = screenSize;
	}

	public OperatingSystem getOs() {
		return os;
	}

	public void setOs(OperatingSystem os) {
		this.os = os;
	}

	public Resolution getResolution() {
		return resolution;
	}

	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	public ScreenType getScreenType() {
		return screenType;
	}

	public void setScreenType(ScreenType screenType) {
		this.screenType = screenType;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Account getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Account createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Account getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(Account lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Tivi [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", quantity="
				+ quantity + ", screenSize=" + screenSize + ", os=" + os + ", resolution=" + resolution
				+ ", screenType=" + screenType + ", brand=" + brand + ", createUser=" + createUser + ", createdDate="
				+ createdDate + ", lastModifiedUser=" + lastModifiedUser + ", lastModifiedDate=" + lastModifiedDate
				+ ", deleted=" + deleted + "]";
	}
	
}
