package controller;

import java.io.IOException;

import server.Message;

public interface MessageHandler {
	void MessageHandle(Message message) throws IOException;
}
