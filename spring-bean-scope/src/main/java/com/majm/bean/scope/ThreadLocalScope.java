package com.majm.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ThreadLocal 级别 Scope </br>
 * <p>
 * {@link Scope}
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-18 16:06
 * @since
 */
public class ThreadLocalScope implements Scope {

    public static final String SCOPE_NAME = "thead-local";

    private final ThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal<Map<String, Object>>("thread-local-scope") {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };


    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {

        Map<String, Object> context = getContext();
        Object obj = context.get(name);
        if (Objects.isNull(obj)) {
            obj = objectFactory.getObject();
            context.put(name, obj);
        }
        return obj;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> context = getContext();
        return context.remove(name);
    }

    private Map<String, Object> getContext() {
        return threadLocal.get();
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // todo
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        Thread thread = Thread.currentThread();
        return String.valueOf(thread.getId());
    }
}
