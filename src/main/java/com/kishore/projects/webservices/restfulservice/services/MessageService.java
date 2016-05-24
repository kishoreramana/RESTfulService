package com.kishore.projects.webservices.restfulservice.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.kishore.projects.webservices.restfulservice.database.DatabaseClass;
import com.kishore.projects.webservices.restfulservice.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1, "Message By Kishore", "Kishore"));
		messages.put(2L, new Message(2, "Message By Kiran", "Kiran"));
		messages.put(3L, new Message(3, "Message By Kumari", "Kumari"));
		messages.put(4L, new Message(4, "Message By Ramana", "Ramana"));
	}

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}

	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> messagesPaginated = new ArrayList<Message>(messages.values());
		if (start + size > messagesPaginated.size())
			return new ArrayList<Message>();
		return messagesPaginated.subList(start, start + size);
	}

	public List<Message> getAllMessages() {
		return new ArrayList<>(messages.values());
	}

	public Message getMessage(long id) {
		return messages.get(id);
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
