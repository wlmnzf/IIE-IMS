package cn.iie.icm.plantform;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component("graph_login")
public class Login implements Serializable {

	private static final long serialVersionUID = -7740915475195013506L;
	
	private String loginId;
	
	private String loginPwd;
	
	private String verifyCode;

	private Map props = new HashMap();
	private boolean isLogined;
	
	public Login() {
		super();
	}
	public void setProperty(String str,Object obj){
		props.put(str, obj);
	}
	public Object getProperty(String str){
		return this.props.get(str);
	}
	public Login(String loginId, String loginPwd) {
		super();
		this.loginId = loginId;
		this.loginPwd = loginPwd;
	}


	public void setLogined(boolean isLogined) {
		this.isLogined = isLogined;
	}

	public boolean isLogined() {

		return isLogined;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	
}
