package com.myl.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myl.util.IssueMail;
import com.myl.util.NombreObjetosSesion;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AutenticarInterceptor implements Interceptor {

	private static final long serialVersionUID = -1939367355017016797L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AutenticarInterceptor.class);
	private String prevAction;

	@Override
	public String intercept(ActionInvocation actionInvocation) {

		String previous = null;
		if ("login".equals(actionInvocation.getProxy().getActionName())) {
			try {
				actionInvocation.invoke();
			} catch (Exception e) {
				errorNotification(e, actionInvocation.getProxy()
						.getActionName());
			}
			if (ActionContext.getContext().getSession()
					.get(NombreObjetosSesion.USUARIO) == null) {
				return Action.LOGIN;
			} else {
				return "next";
			}
		} else if ("registro".equals(actionInvocation.getProxy()
				.getActionName())) {
			try {
				actionInvocation.invoke();
			} catch (Exception e) {
				errorNotification(e, actionInvocation.getProxy()
						.getActionName());
			}
			if (ActionContext.getContext().getSession()
					.get(NombreObjetosSesion.USUARIO) == null) {
				return "registro";
			} else {
				return "denegado";
			}
		} else {
			if (ActionContext.getContext().getSession()
					.get(NombreObjetosSesion.USUARIO) == null) {
				setPrevAction(actionInvocation.getProxy().getActionName());
				ActionContext.getContext().getSession()
						.put("prevAction", getPrevAction());
				return Action.LOGIN;
			} else {
				previous = (String) ActionContext.getContext().getSession()
						.get("prevAction");
				if (previous != null) {
					ActionContext.getContext().getSession()
							.put("current", previous);
					ActionContext.getContext().getSession()
							.put("prevAction", null);
					return "next";
				} else {
					try {
						return actionInvocation.invoke();
					} catch (Exception e) {
						errorNotification(e, actionInvocation.getProxy()
								.getActionName());
					}
				}
			}
		}
		return previous;
	}

	@Override
	public void destroy() {
		LOGGER.info("onDestroy");
	}

	@Override
	public void init() {
		LOGGER.info("Initializing AuthInterceptor");
	}

	public void errorNotification(Exception e, String actionName) {
		LOGGER.error("Interceptor en " + actionName);
		LOGGER.error("Error",e);
	}

	public String getPrevAction() {
		return prevAction;
	}

	public void setPrevAction(String prevAction) {
		this.prevAction = prevAction;
	}

}
