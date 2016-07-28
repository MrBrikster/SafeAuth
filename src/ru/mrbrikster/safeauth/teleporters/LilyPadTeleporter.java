package ru.mrbrikster.safeauth.teleporters;

import org.bukkit.entity.Player;

import lilypad.client.connect.api.Connect;
import lilypad.client.connect.api.request.impl.RedirectRequest;
import lilypad.client.connect.api.result.FutureResultListener;
import lilypad.client.connect.api.result.StatusCode;
import lilypad.client.connect.api.result.impl.RedirectResult;
import ru.mrbrikster.safeauth.Main;

public class LilyPadTeleporter {

	public static void send(Player player) {
		try {
            Connect c = Main.getPlugin().getServer().getServicesManager().getRegistration(Connect.class).getProvider();
            c.request(new RedirectRequest(Main.getMainConfig().getString("mainServer"), player.getName())).registerListener(new FutureResultListener<RedirectResult>() {
            	@Override
            	public void onResult(RedirectResult redirectResult) {
                    if (redirectResult.getStatusCode() == StatusCode.SUCCESS) {
                        return;
                    }
                    player.kickPlayer("�c������ ����������� � �������.");
                }
            });
        } catch (Exception exception) {
            player.kickPlayer("�c������ ����������� � �������.");
        }
	}

}