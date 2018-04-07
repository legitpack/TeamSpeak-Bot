package de.vocalzero.ts3bot.events;

import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.vocalzero.ts3bot.TS3Bot;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;

public class TS3Events {

    public static void load() {
        int whoAmI = TS3Bot.api.whoAmI().getId();
        TS3Bot.api.registerAllEvents();
        TS3Bot.api.addTS3Listeners(new TS3Listener() {
            @Override
            public void onTextMessage(TextMessageEvent event) {
                if (event.getTargetMode() == TextMessageTargetMode.CLIENT && event.getInvokerId() != whoAmI) {
                    String message = event.getMessage();
                    if (message.equalsIgnoreCase("!close")) {
                        Client client = TS3Bot.api.getClientByUId(event.getInvokerUniqueId());
                        if (!client.isInServerGroup(29)) { //<-- Hier deine Support GruppenId
                            TS3Bot.api.sendPrivateMessage(client.getId(), "");
                        } else {
                            HashMap<ChannelProperty, String> options = new HashMap<>();
                            options.put(ChannelProperty.CHANNEL_NAME, "┏ Support × Wartezimmer [Geschlossen]"); //<-- Hier denn neuen Channel Namen setzen
                            options.put(ChannelProperty.CHANNEL_PASSWORD, "gfdsghdfherwsadfge");
                            TS3Bot.api.editChannel(25, options); //<-- Hier deine Support Warteraum ChannelId
                        }
                    }
                }
                if (event.getTargetMode() == TextMessageTargetMode.CLIENT && event.getInvokerId() !=whoAmI) {
                    String message = event.getMessage();
                    if (message.equalsIgnoreCase("!open")) {
                        Client client = TS3Bot.api.getClientByUId(event.getInvokerUniqueId());
                        if (!client.isInServerGroup(29)) { //<-- Hier deine Support GruppenId
                            TS3Bot.api.sendPrivateMessage(client.getId(), "");
                        } else {
                            HashMap<ChannelProperty, String> options = new HashMap<>();
                            options.put(ChannelProperty.CHANNEL_NAME, "┏ Support × Wartezimmer [Geöffnet]"); //<-- Hier denn neuen Channel Namen setzen
                            options.put(ChannelProperty.CHANNEL_PASSWORD, "");
                            TS3Bot.api.editChannel(25, options); //<-- Hier deine Support Warteraum ChannelId
                        }
                    }
                }
            }

            @Override
            public void onClientJoin(ClientJoinEvent event) {
                int clientid = event.getClientId();
                TS3Bot.api.sendPrivateMessage(clientid, "Willkommen [b]" + event.getClientNickname() + "[/b] auf DeinServer"); //<-- Hier dein Server Name
            }

            @Override
            public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {

            }

            @Override
            public void onServerEdit(ServerEditedEvent serverEditedEvent) {

            }

            @Override
            public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {

            }

            @Override
            public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {

            }

            @Override
            public void onClientMoved(ClientMovedEvent event) {
                if (event.getTargetChannelId() == 25) { //<-- Hier deine Support Warteraum ChannelId
                    int i = 0;
                    for(Client clients : TS3Bot.api.getClients()){
                        if (clients.isInServerGroup(29)) { //<-- Hier deine Support GruppenId
                            i++;
                            TS3Bot.api.pokeClient(clients.getId(), "Jemand im Support Wartezimmer benötigt Support!");
                        }
                    }
                    if (i == 0) {
                        TS3Bot.api.sendPrivateMessage(event.getClientId(), "Es sind zurzeit [B]keine Teammitglieder[/B] online!");
                    } if (i == 1) {
                        TS3Bot.api.sendPrivateMessage(event.getClientId(), "Derzeit ist nur [B]" + i + " Teammitglied [/B]zum Supporten online!");
                    } else {
                        TS3Bot.api.sendPrivateMessage(event.getClientId(), "Derzeit sind [B]" + i + " Teammitglieder [/B]zum Supporten online!");
                    }
                }
            }

            @Override
            public void onChannelCreate(ChannelCreateEvent event) {

            }

            @Override
            public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {

            }

            @Override
            public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {

            }

            @Override
            public void onChannelPasswordChanged(ChannelPasswordChangedEvent channelPasswordChangedEvent) {

            }

            @Override
            public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent privilegeKeyUsedEvent) {

            }
        });
    }
}
