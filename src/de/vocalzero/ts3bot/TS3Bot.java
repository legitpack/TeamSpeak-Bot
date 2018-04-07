package de.vocalzero.ts3bot;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import de.vocalzero.ts3bot.events.TS3Events;

public class TS3Bot {
    public static final TS3Config tsconfig = new TS3Config();
    public static final TS3Query tsquery = new TS3Query(tsconfig);
    public static final TS3Api api = tsquery.getApi();

    public static void main(String[] args) {
        tsconfig.setHost("");
        tsquery.connect();
        api.login("serveradmin", "");
        api.selectVirtualServerByPort(9987);
        TS3Events.load();
        System.out.println("TS3-Querybot wurde erfolgreich gestartet!");
    }
}
