package de.Bethibande.Networking.API.Listeners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private static List<Listener> listeners = new ArrayList<>();

    public static void addListener(Listener l) { listeners.add(l); }
    public static void removeListener(Listener l) { listeners.remove(l); }

    public static void runEvent(Event e) {
        for(Listener l : listeners) {
            if(l != null) {
                Class<?> clazz = l.getClass();
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(EventHandler.class)) {
                        if (method.getParameters()[0].getType().getSuperclass() == Event.class && method.getParameters()[0].getType() == e.getClass()) {
                            try {
                                method.setAccessible(true);
                                //System.out.println("Invoke " + method.getName() + " in class " + clazz.getName());
                                method.invoke(l, e);
                            } catch (IllegalAccessException | InvocationTargetException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

}
