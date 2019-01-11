package com.weige.lamdba.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;

public class PmtOutputMaterialDto extends BaseModel<String> {
	private String productionTaskId;
	// private String materialId;
	private String materialName;
	private String materialCode;
	private String productionBatch;
	private String outputMaterialType;
	private BigDecimal outputNum;
	private String containerCode;
	private Integer serialNumber;
	private String defect;// 缺陷原因


	

	public String getProductionTaskId() {
		return productionTaskId;
	}

	public void setProductionTaskId(String productionTaskId) {
		this.productionTaskId = productionTaskId;
	}

	/*
	 * public String getMaterialId() { return materialId; }
	 * 
	 * public void setMaterialId(String materialId) { this.materialId =
	 * materialId; }
	 */
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

	public String getOutputMaterialType() {
		return outputMaterialType;
	}

	public void setOutputMaterialType(String outputMaterialType) {
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

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDefect() {
		return defect;
	}

	public void setDefect(String defect) {
		this.defect = defect;
	}

	/**
	 * 将bean中的属性复制到dto中 说明：赋值过程中可能产生异常 但这些异常 均为java反射机制所产生 不会影响最终结果 如
	 * bean中存在的属性(包含父类) dto没有 反射会抛出无该字段异常 注意:dto和bean中 最好都填写 set get
	 * 
	 * @param soureObject
	 *            源对象
	 * @param targetObject
	 *            目标类型
	 * @return 目标
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertModelToDto(Object soureObject, Class<T> targetClass) {
		Class<?> soureClass = soureObject.getClass();
		Object resultObject;
		try {
			resultObject = targetClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("无法通过反射实例这个类" + targetClass.getName());
		}
		// 遍历源对象属性,优先复制自身属性
		for (Field sourcefield : soureClass.getDeclaredFields()) {
			// 判定要复制的对象属性 是否存在当前属性
			Field targetField;
			try {
				targetField = targetClass.getDeclaredField(sourcefield.getName());
				if (targetField != null) { // 说明要复制的对象中存在当前属性
					if (targetField.getType().getName().equals(sourcefield.getType().getName())) { // 类型必须一致
						sourcefield.setAccessible(true);
						targetField.setAccessible(true);
						if (sourcefield.get(soureObject) != null) {
							targetField.set(resultObject, sourcefield.get(soureObject));
						}

					}

				}
			} catch (Exception e) {
				System.out.println("此处发生了字段不存在或者get方法不存在异常 以及类型不一致无法赋值...无需关注");
			}
		}
		// 此处开始, 若源对象属性 存在父类,则继续复制 父类属性到dto中
		Class<?> sourceParentClass = soureClass.getSuperclass();
		if (sourceParentClass != null) {
			for (Field field : sourceParentClass.getDeclaredFields()) {
				// 判定目标对象中 是否存在其父类属性
				try {
					Field targetField = targetClass.getDeclaredField(field.getName());
					if (targetField != null) { // 此处为了兼容泛型父类 不做处理
						// if
						// (targetField.getType().getName().equals(field.getType().getName()))
						// {
						// 通过子类方法,调用父类属性
						StringBuilder methodName = new StringBuilder();
						methodName.append("get").append(field.getName().substring(0, 1).toUpperCase()
								+ field.getName().substring(1, field.getName().length()));
						Method method = soureClass.getMethod(methodName.toString());
						targetField.set(resultObject, method.invoke(soureObject)); // 将父类属性
																					// 赋值到dto对应属性中
						// }
					}
				} catch (Exception e) {
					System.out.println("此处发生了字段不存在或者get方法不存在异常 以及类型不一致无法赋值...无需关注");
				}
			}
		}
		// 如果dto也有继承的父类,暂不处理
		Class<?> targetParentClass = targetClass.getSuperclass();
		if (targetClass != null) {

		}
		return (T) resultObject;
	}

	public static void main(String[] args) {
		PmtOutputMaterial pmtOutputMaterial = new PmtOutputMaterial();
		pmtOutputMaterial.setMaterialCode("aaa");
		pmtOutputMaterial.setOutputMaterialType(13123);
		pmtOutputMaterial.setOutputNum(new BigDecimal("100"));
		pmtOutputMaterial.setKey("hahha");
		PmtOutputMaterialDto pmtOutputMaterialDto = new PmtOutputMaterialDto();

		PmtOutputMaterialDto dto = convertModelToDto(pmtOutputMaterial, PmtOutputMaterialDto.class);
		System.out.println(JSONObject.toJSONString(dto));

		System.out.println(JSONObject.toJSONString(pmtOutputMaterialDto));

		for (Method field : PmtOutputMaterialDto.class.getDeclaredMethods()) {
			System.out.println(field.getName() );
		}
		try {
			System.out.println(PmtOutputMaterialDto.class.getMethod("getKey"));
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
