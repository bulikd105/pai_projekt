package pai_projekt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/*
 * Klasa Server
 * Posiada metody odpowiedzialne za zarz�dzanie graczami i obs�ug� gry.
 */
public class Server implements Runnable 
{
	private String playerName = "Player ";
	private Socket socket;
	private ArrayList<Player> playerList;
	Player newPlayer = null;
	
	public Server(Socket socket, int playerId, ArrayList<Player> playerList) 
	{
		newPlayer = new Player(playerId, playerName + playerId);
		this.socket = socket;
		this.playerList = playerList;
		this.playerList.add(newPlayer);
		
		System.out.println("Player: " + newPlayer.getPlayerName());
	}

	@Override
	public void run()
	{
		BufferedReader in = null;
        PrintWriter out = null;
                
        try
		{	
            in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            boolean mainFlag = true;

            String userInput = "";
            String serverOutput = "";
            
        	
            
            while(mainFlag  == true)
            {

            	// Wyswietl glowne menu
            	out.println( "1 - Podaj haslo\n2 - Wyswietl statystyki wszystkich graczy\n" + 
        					 "3 - Wyswietl swoje dane\n4 - Zmien swoja nazwe uzytkownika\n" + 
        					 "5 - Wyjdz");

            	userInput = in.readLine();
            	System.out.println("Klient: " + newPlayer.getPlayerName() + " powiedzial - " + userInput);

            	if(userInput != null && userInput.length() > 0)
            	{
            		switch(userInput)
	            	{
            		
	            	}           		
            	}
            	else
            	{
            		System.out.println("Klient: " + newPlayer.getPlayerName() + " przeslal null'a, zamykajac tym samym polaczenie");
            		break;
            	}
            }
		}
        catch(IOException e)
		{
			System.out.println("Klient: " + newPlayer.getPlayerName() + " zglosil blad: " + e);
		}
		finally // W razie gdyby klient zglosil blad, rozlacz go
		{
        	// Zamkniecie bufferow
        	try 
        	{
				in.close();
				out.close();
				socket.close();
			} 
        	catch (IOException e) 
        	{
				e.printStackTrace();
			}
		}
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
