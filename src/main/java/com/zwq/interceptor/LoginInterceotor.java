package com.zwq.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zwq.action.LoginAction;
import com.zwq.action.UserAction;

import java.util.Map;

import static com.opensymphony.xwork2.Action.INPUT;

/**
 * Created by Archer on 2016/12/6.
 */
public class LoginInterceotor extends AbstractInterceptor {

  @Override
  public String intercept(ActionInvocation actionInvocation) throws Exception {
    Map<?, ?> session = actionInvocation.getInvocationContext().getSession();
    System.out.println("userInfo是：" + session.get("user"));
    Object action = actionInvocation.getAction();

    if (action instanceof LoginAction) {
      System.out.println("exit check login, because this is login action.");
      return actionInvocation.invoke();
    }
    if (session.get("user") != null) {//记得放session
      return actionInvocation.invoke();
    }
    return "relogin";
  }
}
