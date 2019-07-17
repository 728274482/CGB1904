package com.company.sys.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JsonResult implements Serializable{

	/**
	 * VO:封装控制层要返回给客户端的数据
	 */
	private static final long serialVersionUID = -7175847294542330284L;
	/**状态码*/
	private int state=1;//1表示SUCCESS,0表示ERROR
	/**状态信息*/
	private String message="ok";
	/**正确数据*/
	private Object data;
	
	public JsonResult() {}
	/**一般查询时调用，封装查询结果*/
	public JsonResult(Object data) {
		this.data=data;
	}
	/**出现异常时时调用*/
	public JsonResult(Throwable t){
		this.state=0;
		this.message=t.getMessage();
	}
	
	public JsonResult(String message) {
		this.message=message;
	}

}
