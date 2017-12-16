package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import controller.MessageHandler;
import server.Message;
import server.Server;
import server.User;
import view.Login;
import view.Login.LoginListener;
import view.Main;
import view.Main.MainListener;

public class Client extends Thread implements MessageHandler, LoginListener, MainListener{
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Login login;
	private Main main;
	private User user = null;
	private MessageReceiver receiver;
	
	public Client() {
		try {
			socket = new Socket(Server.ADDRESS,Server.PORT);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		login = new Login(this);
	}

	private void startMain() {
		receiver = new MessageReceiver(in);
		receiver.setClient(this);
		receiver.start();
		main = new Main(this);
	}

	@Override
	public void MessageHandle(Message message) throws IOException{
		switch(message.getWhat()) {

		}
		
	}
	
	@Override
	public boolean onClickLogin(String id, String password) {
		System.out.println("Login");	
		try {
			Message message = new Message(Message.LOGIN, id, password);
			out.writeObject(message);
			Object o =null;
			o = in.readObject();
			if(!(o instanceof Message)) {
					return false;
			}
			Message reply = (Message)o;
			if(reply.isValid()) {
				login.dispose();
				user = reply.getUser();
				startMain();
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onClickSignUp(User user) {
		System.out.println("SignUP");
		// TODO Auto-generated method stub
		try {
			Message message = new Message(Message.SIGN_UP);
			message.setUser(user);
			out.writeObject(message);
			Object o =null;
			o = in.readObject();
			if(!(o instanceof Message)) {
					return false;
			}
			Message reply = (Message)o;
			if(reply.isValid())
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public boolean onCheckDuplicate(String id) {
		System.out.println("Check Duplication");
		try {
			out.writeObject(new Message(Message.ID_CHECK, id));
			Object o =null;
			o = in.readObject();
			if(!(o instanceof Message)) {
					return false;
			}
			Message reply = (Message)o;
			if(reply.isValid())
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	
	@Override
	public User getUser() {
		return user;
	}
	
	private class MessageReceiver extends Thread{
		private MessageHandler client;

		public void setClient(MessageHandler client) {
			this.client = client;
		}

		private final ObjectInputStream in;
		public MessageReceiver(ObjectInputStream in) {
			this.in = in;
		}
		
		public void run() {
			while(true) {
				try {
					Object o = in.readObject();
					if(!(o instanceof Message)) {
						continue;
					}
					Message message = (Message)o;
					client.MessageHandle(message);
					message = null;
				} catch (SocketException e) {
					break;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
	}
}
