package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import controller.MessageHandler;

public class Server {
	public final static int PORT = 9000;
	public final static String ADDRESS = "127.0.0.1";

	private ServerSocket listener;
	private DB db;

	public Server() {
		try {
			initServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		db = DB.getInstance();

	}

	private final void initServer() throws IOException {
		listener = new ServerSocket(PORT);
	}

	public final void startServer() {
		System.out.println("The chat server is running");
		try {
			while (true) {
				new Handler(listener.accept()).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 서버가 사용한 자원들을 해제하는 곳
			System.out.println("The chat server isn't running");
			try {
				listener.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private class Handler extends Thread implements MessageHandler {

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
			while (true) {
				Message message;
				try {
					Object o = in.readObject();
					if (!(o instanceof Message)) {
						continue;
					}
					message = (Message) o;
					MessageHandle(message);
				} catch (SocketException e) {
					break;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					message = null;
				}
			}
		}

		@Override
		public void MessageHandle(Message message) throws IOException {
			System.out.println(message.getWhat() + " handle");
			switch (message.getWhat()) {
			case Message.ID_CHECK: {
				message.setValid(db.checkID(message.getArg()));
				out.writeObject(message);
				break;
			}
			case Message.SIGN_UP: {
				if (message.getObject() instanceof User) {
					message.setValid(db.insertUser((User) message.getObject()));
					out.writeObject(message);
				}
				break;
			}
			case Message.LOGIN: {
				message.setObject(db.checkUser(message.getArg(), message.getArg2()));
				out.writeObject(message);
				break;
			}
			case Message.GET_FOOD_LIST: {
				List<Food> foodList = db.getFoodList(message.getArg());
				message.setObject(foodList);
				out.writeObject(message);
			}
			}

		}
	}
}
