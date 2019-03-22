package edu.nju.master.graduate.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = -1719909808663638420L;
	
	public String code;
	
	public BusinessException(String message) {
        super(message);
    }
	
	public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
