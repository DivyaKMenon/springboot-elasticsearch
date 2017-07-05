package com.tr.bean;

import java.util.Date;

public class ProductBean {

	private Integer id;
	public String name;
	public String description;
	public double price;
	public String uomTypeId;
	public String vendorModelNumber;
	public String imageUrl;
	public String vendorId;
	public Boolean isActive;
	public String createdBy;
	public Date createdDate;
	public String modifiedBy;
	public Date modifiedDate;

	public ProductBean() {
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUomTypeId() {
		return uomTypeId;
	}

	public void setUomTypeId(String uomTypeId) {
		this.uomTypeId = uomTypeId;
	}

	public String getVendorModelNumber() {
		return vendorModelNumber;
	}

	public void setVendorModelNumber(String vendorModelNumber) {
		this.vendorModelNumber = vendorModelNumber;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
