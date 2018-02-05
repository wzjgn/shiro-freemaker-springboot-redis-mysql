package com.xinwei.shiro;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

public interface ShiroSessionRepository {
	void saveSession(Session session);

	void deleteSession(Serializable sessionId);

	Session getSession(Serializable sessionId);

	Collection<Session> getAllSessions();
}
