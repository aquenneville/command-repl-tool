package github.aq.cmdrepltool.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import github.aq.cmdrepltool.configuration.ConfigurationProperties;
import github.aq.cmdrepltool.model.CommandFactory;
import github.aq.cmdrepltool.model.CommandI;
import github.aq.cmdrepltool.model.CommandResponse;
import github.aq.cmdrepltool.model.InputTokens;

public class CmdReplShell {

	private ConfigurationProperties config; 
	private InputStreamReader inStreamReader;
	private String input = ""; 
	
	public CmdReplShell() {
		config = new ConfigurationProperties();
		inStreamReader = new InputStreamReader(System.in); // Manage System .out and .err ??
	}
	
	public void run() {
		BufferedReader br = new BufferedReader(inStreamReader);
		CommandResponse commandResponse = null;
		try {
			System.out.print(getPrompt());
			while ((input = br.readLine()) != null) {
				br = new BufferedReader(inStreamReader);
				if (input.length() > 0) {
					CommandI command = null;
					try {
					    InputTokens inputTokens = new InputTokens(input);
						command = CommandFactory.create(inputTokens);
					} catch(IllegalArgumentException ex) {
						System.err.println("Error: unknown command: " + input);
					}
					
					if (command != null) {
						try {
							try {
								commandResponse = command.eval();
							} catch(Exception ex) {
								System.err.println(ex.getMessage());
								if (command != null) {
									System.err.println("Check help: " + ConfigurationProperties.getCommandMethodDictionary().get(command.getCommandName()));
								}
							}
							Gson gson = new GsonBuilder().setPrettyPrinting().create();
							System.out.println(gson.toJson(commandResponse.getData()));
							System.out.println("evaluation time: " + gson.toJson(commandResponse.getCommandExecutionTimeInMs()) + " ms");
							
						} catch(Exception ex) {
							System.err.println("Error during evaluation: " + ex.getMessage());
						}
					}
						
				}
				input = "";
				System.out.println(getPrompt());
			}
		} catch(IOException ex) {
			System.err.println(ex);
		}
		
	}

	private String getPrompt() {
		return "repl-shell:>";// config.getProperty("env");
	}

}
