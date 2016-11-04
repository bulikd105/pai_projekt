package pai_projekt;

import java.net.Socket;
import java.util.ArrayList;

import lab4.MyService;

/*
 * Klasa Server
 * Posiada metody odpowiedzialne za zarz¹dzanie graczami i obs³ugê gry.
 */
public class Server implements Runnable 
{
	private String playerName = "Player ";
	private Socket socket;
	private ArrayList<Player> playerList;
	
	public Server(Socket socket, int playerId, ArrayList<Player> playerList) 
	{
		Player newPlayer = new Player(playerId, playerName + playerId);
		this.socket = socket;
		this.playerList = playerList;
		this.playerList.add(newPlayer);
		
		System.out.println("Player: " + newPlayer.getPlayerName());
	}

	@Override
	public void run()
	{
		
		
	}
	
	public void ChangeName()
	{
		
	}
	
	public String ShowPlayerList(ArrayList<Player> playerList)
	{
		StringBuilder sb = new StringBuilder();

		sb.append("Players list\n");
		
		// Sprawdzamy czy lista wszystkich uslug nie jest pusta
		if(!playerList.isEmpty())
		{
			// Wyswietlamy liste wszystkich uslg
			for(Player player : playerList)
			{
				sb.append("----------------------------------------\n");
				sb.append("Player ID: " + player.getPlayerId() + "\n");
				sb.append("Player Name: " + player.getPlayerName() + "\n");
				sb.append("Score: " + player.getPlayerScore() + "\n");
			}
		}
		else
		{
			sb.append("Lista wszystkich uslug jest pusta");
		}
		return sb.toString();
	}
}
