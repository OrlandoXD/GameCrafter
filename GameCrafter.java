package de.orlando;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.login.LoginException;

import de.orlando.listener.CommandListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class GameCrafter {

	public ShardManager ShardMan;
	
	public static void main(String[] args) {
		try {
			new GameCrafter();
		} catch (LoginException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public GameCrafter() throws LoginException, IllegalArgumentException {
		DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
		builder.setToken("NTkzNzk5MzI2MDk0ODUyMTA2.XRTtYQ.P0RAWBBpaO23OITUZAlzlQgTQbA");
		builder.setActivity(Activity.listening("Musik"));
		builder.setStatus(OnlineStatus.ONLINE);
		builder.addEventListeners(new CommandListener());
		
		
		ShardMan = builder.build();
		System.out.println("Bot online");
		System.out.println("Use 'exitbot' to shutdown");
		
		shutdown();
	}
	
	public void shutdown() {
		new Thread(() -> {
			
			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				while((line = reader.readLine()) != null) {
					
					if(line.equalsIgnoreCase("exitbot")) {
						if(ShardMan != null) {
							ShardMan.setStatus(OnlineStatus.OFFLINE);
							ShardMan.shutdown();
							System.out.println("bot offline");
						}
					} else {
						System.out.println("Use 'exitbot' to shutdown");
					}
					
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}).start();
	}
}
