package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import controller.MessageHandler;


public class Server {
	public final static int PORT = 9000;
	public final static String ADDRESS = "127.0.0.1";
	
	private ServerSocket listener;
	
	public Server() {
		try {
			initServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private final void initServer() throws IOException{
			listener = new ServerSocket(PORT);
	}
	
	private final void initDB() {
		
	}
	
	public final void startServer() {
		System.out.println("The chat server is running");
		try {
			while (true) {
				new Handler(listener.accept()).start();
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			//서버가 사용한 자원들을 해제하는 곳
			System.out.println("The chat server isn't running");
			try {
				listener.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class Handler extends Thread implements MessageHandler{

		private Socket socket;
		private ObjectOutputStream out;
		private ObjectInputStream in;


		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void initHandler() {
			try {
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void run() {
			initHandler();
			while(true) {
				try {
					Object o = in.readObject();
					if(!(o instanceof Message)) {
						continue;
					}
					Message message = (Message)o;
					MessageHandle(message);
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

		@Override
		public void MessageHandle(Message message) throws IOException{
			switch(message.getWhat()) {
			case Message.ID_CHECK:{
				//중복체크 코드
				message.setValid(true);
				out.writeObject(message);
				break;
			}
			case Message.SIGN_UP:{
				break;
			}
			case Message.LOGIN:{
				//아이디랑 패스워드 확인
				message.setValid(true);
				message.setUser(new User("tester","014015", "Sun", 27, "Man", 173, 70, 65));
				out.writeObject(message);
				break;
			}
			}
			
		}
	}
}
