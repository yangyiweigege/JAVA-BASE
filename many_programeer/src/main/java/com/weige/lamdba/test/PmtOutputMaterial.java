package com.weige.lamdba.test;

import java.math.BigDecimal;

public class PmtOutputMaterial  extends BaseModel<String>{
	private String productionTaskId;
	private String materialId;
	private String materialName;
	private String materialCode;
	private String productionBatch;
	private Integer outputMaterialType;
	private BigDecimal outputNum;
	private String containerCode;
	public String getProductionTaskId() {
		return productionTaskId;
	}
	public void setProductionTaskId(String productionTaskId) {
		this.productionTaskId = productionTaskId;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getProductionBatch() {
		return productionBatch;
	}
	public void setProductionBatch(String productionBatch) {
		this.productionBatch = productionBatch;
	}
	public Integer getOutputMaterialType() {
		return outputMaterialType;
	}
	public void setOutputMaterialType(Integer outputMaterialType) {
		this.outputMaterialType = outputMaterialType;
	}
	public BigDecimal getOutputNum() {
		return outputNum;
	}
	public void setOutputNum(BigDecimal outputNum) {
		this.outputNum = outputNum;
	}
	public String getContainerCode() {
		return containerCode;
	}
	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}


}
