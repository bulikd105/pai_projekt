package pai_projekt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/*
 * Klasa Server
 * Posiada metody odpowiedzialne za zarz¹dzanie graczami i obs³ugê gry.
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
            boolean menuFlag = false;

            String userInput = "";
            String serverOutput = "";
            
            while(mainFlag  == true)
            {
            	// Gdy do³¹cza nowy gracz
            	if(newPlayer.isNewPlayer() == true && newPlayer.isGuessing() == false && menuFlag == false)
            	{
            		out.println("Witaj nowy graczu. Wybierz jedna z opcji:" + 
            					"\n1 - Dolacz do gry\n2 - Menu");
            		out.println("Wybierasz:");
		
		           	userInput = in.readLine();
		           	System.out.println("Klient: " + newPlayer.getPlayerName() + " powiedzial - " + userInput);
		
		           	if(userInput != null && userInput.length() > 0)
		           	{
		           		switch(userInput)
		            	{
		            		case "1" :  out.println("Dolaczasz do gry");
		            					break;
		            		
		            		case "2" :  out.println("Menu");
		            					menuFlag = true;
            							break;

            				default  :  out.println("Nie ma takiej opcji\n");
            							break;
		            	}           		
		           	}
		           	else
		           	{
		           		System.out.println("Klient: " + newPlayer.getPlayerName() + " przeslal null'a, zamykajac tym samym polaczenie");
		           		break;
		           	}
            	}
            	// Gdy gracz zgadujê haslo
            	else if(newPlayer.isNewPlayer() == false && newPlayer.isGuessing() == true && menuFlag == false)
            	{
            		// Wyswietl glowne menu
                	out.println( "Twoim zadaniem jest odgadniêcie hasla.\n" + 
                				 "1 - Podaj haslo\n2 - Menu");

                	userInput = in.readLine();
                	System.out.println("Klient: " + newPlayer.getPlayerName() + " powiedzial - " + userInput);

                	if(userInput != null && userInput.length() > 0)
                	{
                		switch(userInput)
    	            	{
	    	            	case "1" :  out.println("Wprowadz haslo");
	    								break;
	    		
				    		case "2" :  out.println("Menu");
				    					menuFlag = true;
										break;
				
							default  :  out.println("Nie ma takiej opcji\n");
										break;
    	            	}           		
                	}
                	else
                	{
                		System.out.println("Klient: " + newPlayer.getPlayerName() + " przeslal null'a, zamykajac tym samym polaczenie");
                		break;
                	}
            	}
            	// Gdy gracz wymyœla has³o
            	else if(newPlayer.isNewPlayer() == false && newPlayer.isGuessing() == false && menuFlag == false)
            	{
            		// Wyswietl glowne menu
                	out.println( "Twoim zadaniem jest wymyœlenie has³a.\n" + 
                				 "1 - Wymyœl nowe haslo\n2 - Menu");

                	userInput = in.readLine();
                	System.out.println("Klient: " + newPlayer.getPlayerName() + " powiedzial - " + userInput);

                	if(userInput != null && userInput.length() > 0)
                	{
                		switch(userInput)
    	            	{
	    	            	case "1" :  out.println("Wymysl haslo");
	    								break;
    		
				    		case "2" :  out.println("Menu");
				    					menuFlag = true;
										break;
				
							default  :  out.println("Nie ma takiej opcji\n");
										break;
    	            	}           		
                	}
                	else
                	{
                		System.out.println("Klient: " + newPlayer.getPlayerName() + " przeslal null'a, zamykajac tym samym polaczenie");
                		break;
                	}
            	}
            	// W innym wypadku, ustaw gracza, jako graj¹cego 
            	else if(newPlayer.isNewPlayer() == true && newPlayer.isGuessing() == true && menuFlag == false)
            	{
            		newPlayer.setGuessing(true);
            		newPlayer.setNewPlayer(false);
            	}
            	
            	// Pokaz menu
            	if(menuFlag == true)
            	{
            		out.println("Witaj w menu.\n1 - Wyswietl swoje dane\n2 - Wyswietl statystyki wszystkich graczy\n" + 
            					"3 - Zmien swoja nazwe uzytkownika\n4 - Wroc do gry\n5 - Wyjdz");
            		out.println("Wybierasz:");
	
		           	userInput = in.readLine();
		           	System.out.println("Klient: " + newPlayer.getPlayerName() + " powiedzial - " + userInput);
		
		           	if(userInput != null && userInput.length() > 0)
		           	{
		           		switch(userInput)
		            	{
		            		case "1" :  out.println("Twoje Dane");
		            					break;
		            		
		            		case "2" :  out.println("Dane wszystkich graczy");
	        							break;
	   		
		            		case "3" :  out.println("Podaj nowa nazwe uzytkownika");
	        							break;
	   		
		            		case "4" :  out.println("Wracamy do gry");
		            					menuFlag = false;
										break;
		            		
		            		case "5" :  out.println("Wyjdz");
	        							break;
	   		
	        				default  :  out.println("Nie ma takiej opcji\n");
	        							break;
		            	}           		
		           	}
		           	else
		           	{
		           		System.out.println("Klient: " + newPlayer.getPlayerName() + " przeslal null'a, zamykajac tym samym polaczenie");
		           		break;
		           	}
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
