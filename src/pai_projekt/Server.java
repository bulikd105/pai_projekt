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
	
	public boolean ChangeName(ArrayList<Player> playerList, Player player, String playerName)
	{
		// Sprawdzamy czy lista nie jest pusta
		if(!playerList.isEmpty())
		{
			// Przeszukujemy liste w poszukiwaniu nowej nazwy uzytkownika
			for(Player tempPlayer : playerList)
			{
				// Jesli juz taka istnieje zwracamy false
				if(tempPlayer.getPlayerName().equals(playerName))
				{
					return false;
				}
			}
			
			// Jesli nie istnieje to ustawiamy nowa nazwe uzytkownika
			player.setPlayerName(playerName);
			playerList.set(playerList.indexOf(player), player);
	
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String ShowMyStat(Player player)
	{
		// String builder, zeby wyslac tylko jedna linie odpwowiedzi
		StringBuilder sb = new StringBuilder();
		
		// Dane aktualnego gracza
		sb.append("My stats\n");
		sb.append("Player ID: " + player.getPlayerId() + "\n");
		sb.append("Player Name: " + player.getPlayerName() + "\n");
		sb.append("Score: " + player.getPlayerScore() + "\n");
		
		return sb.toString();
		
	}
	
	public String ShowAllStats(ArrayList<Player> playerList)
	{
		// String builder, zeby wyslac tylko jedna linie odpwowiedzi
		StringBuilder sb = new StringBuilder();
		sb.append("All players stats\n");
		
		// Sprawdzamy czy lista graczy nie jest pusta
		if(!playerList.isEmpty())
		{
			// Zapisuje liste graczy
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
