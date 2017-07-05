package com.tr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "Name")
	public String name;

	@Column(name = "Description")
	public String description;

	@Column(name = "Price")
	public double price;

	@Column(name = "UomTypeId")
	public String uomTypeId;

	@Column(name = "VendorModelNumber")
	public String vendorModelNumber;

	@Column(name = "ImageUrl")
	public String imageUrl;

	@Column(name = "VendorId")
	public String vendorId;

	@Column(name = "IsActive")
	public Boolean isActive;

	@Column(name = "CreatedBy")
	public String createdBy;

	@Column(name = "CreatedDate")
	public Date createdDate;

	@Column(name = "ModifiedBy")
	public String modifiedBy;

	@Column(name = "ModifiedDate")
	public Date modifiedDate;

	@Transient
	public String esId;

	public String getEsId() {
		return esId;
	}

	public void setEsId(String esId) {
		this.esId = esId;
	}

	public Product() {
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
