package server;
import java.io.Serializable;

public class Message implements Serializable{
	public static final int ID_CHECK = 1;
	public static final int SIGN_UP = 2;
	public static final int LOGIN =3;
	private int what;
	private String arg = null;
	private String arg2 = null;
	private boolean valid = false;
	private User user = null;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public int getWhat() {
		return what;
	}
	public void setWhat(int what) {
		this.what = what;
	}
	public String getArg() {
		return arg;
	}
	public void setArg(String arg) {
		this.arg = arg;
	}
	
	public Message(int what, String arg) {
		super();
		this.what = what;
		this.arg = arg;
	}
	
	public Message(int what, String arg, String arg2) {
		super();
		this.what = what;
		this.arg = arg;
		this.arg2 = arg2;
	}
	
	public Message(int what) {
		super();
		this.what = what;
	}
	public String getArg2() {
		return arg2;
	}
	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}
}
