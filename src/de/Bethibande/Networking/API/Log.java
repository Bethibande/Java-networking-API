package de.Bethibande.Networking.API;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Log {

    public static enum LOG_LEVEL {
        ERROR("ERROR"), INFO("Info"), SERVER("Server");

        private String prefix;
        LOG_LEVEL(String prefix) {
            this.prefix = prefix;
        }
        String getPrefix() { return this.prefix; }
    }

    public static void log(LOG_LEVEL level, String message) {
        log(level.getPrefix(), message);
    }

    public static void log(String prefix, String msg) {
        Date d = Calendar.getInstance().getTime();
        String log = "[" + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + "] [" + prefix + "] " + msg;
        System.out.println(log);
    }

}
