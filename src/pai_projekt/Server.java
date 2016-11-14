package pai_projekt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import com.sun.scenario.effect.Offset;

/*
 * Klasa Server
 * Posiada metody odpowiedzialne za zarz¹dzanie graczami i obs³ugê gry.
 */
public class Server implements Runnable 
{
	private String playerName = "Player ";
	private Socket socket;
	private ArrayList<Player> playerList;
	private ArrayList<String> passwordList;
	Player newPlayer = null;
	private String password = "";
	
	public Server(Socket socket, int playerId, ArrayList<Player> playerList, ArrayList<String> passwordList) 
	{
		newPlayer = new Player(playerId, playerName + playerId);
		this.socket = socket;
		this.playerList = playerList;
		this.playerList.add(newPlayer);
		this.passwordList = passwordList;
		
		System.out.println("Player: " + newPlayer.getPlayerName());
	}

	@Override
	public void run()
	{
		
		BufferedReader in = null;
        PrintWriter out = null;
                
        try
		{	
        	Dictionary dictionary = new Dictionary();
            in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            boolean mainFlag = true;
            boolean menuFlag = false;

            String userInput = "";
            String serverOutput = "";
            
            int offset;
            int iterator = 0;
            
            while(mainFlag  == true)
            {
            	String password = passwordList.get(passwordList.size()-1);
            	offset = password.length();
            	
            	if(offset > iterator)
            	{
        			offset -= iterator;
            	}
            	// Gdy do³¹cza nowy gracz
            	if(newPlayer.isNewPlayer() == true && newPlayer.isGuessing() == false && menuFlag == false)
            	{
            		out.println("Witaj nowy graczu: " + newPlayer.getPlayerName() +  
            					"\n1 - Dolacz do gry\n2 - Menu");
            		out.println("Wybierasz:");
		
		           	userInput = in.readLine();
		           	System.out.println("Klient: " + newPlayer.getPlayerId() + " " + newPlayer.getPlayerName() + " w menu dolaczania, powiedzial - " + userInput);
		
		           	if(userInput != null && userInput.length() > 0)
		           	{
		           		switch(userInput)
		            	{
		            		case "1" :  out.println("Dolaczasz do gry");
					            		if(newPlayer.getPlayerId() == 1)
					            		{
					            			newPlayer.setGuessing(false);
					            		}
					            		else
					            		{
					            			newPlayer.setGuessing(true);
					            		}
		            					newPlayer.setNewPlayer(false);
		            					playerList.set(playerList.indexOf(newPlayer), newPlayer);
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
		           		out.println("Nie mozna wysylac null.");
		           	}
            	}
            	// Gdy gracz zgadujê haslo
            	else if(newPlayer.isNewPlayer() == false && newPlayer.isGuessing() == true && menuFlag == false)
            	{
            		// Wyswietl glowne menu
                	out.println( "Twoim zadaniem jest odgadniêcie hasla.\n" + 
                				 "1 - Podaj haslo\n2 - Menu");
                	out.println("Wybierasz:");

                	userInput = in.readLine();
                	System.out.println("Klient: " + newPlayer.getPlayerId() + " " + newPlayer.getPlayerName() + " w menu zgadywania hasla,  powiedzial - " + userInput);

                	if(userInput != null && userInput.length() > 0)
                	{
                		switch(userInput)
    	            	{
	    	            	case "1" :  out.println("Haslo zaczyna sie od: " + password.substring(offset) + " . Liczba liter to: " + password.length() +"\nPodaj haslo:");
	    	            				userInput = in.readLine();
	    	            				iterator += 1;
	    	            				// Jesli wyraz istnieje
	    	            				if(dictionary.Contains(userInput))
	    	            				{
	    	            					if(userInput.equals(password))
	    	            					{
	    	            						out.println("Poprawne haslo.\n Dostajesz 100 pkt i wymyslasz nowe haslo");
	    	            						// Co by nikt nie zgadl drugi raz
	    	            						password = "";
	    	            						passwordList.add(password);
	    	            						
	    	            						// Ustawienie osoby ktora wymyslala haslo na osobe ktora je odgaduje
	    	            						for(Player tempPlayer : playerList)
	    	            						{
	    	            							if(tempPlayer.isGuessing() == false && tempPlayer.isNewPlayer() == false)
	    	            							{
	    	            								tempPlayer.setGuessing(true);
	    	            								playerList.set(playerList.indexOf(tempPlayer), tempPlayer);
	    	            							}
	    	            						}
	    	            						
	    	            						// Ustawienie osoby ktora odgadla haslo na osobe ktora wymysla haslo
	    	            						newPlayer.setPlayerScore(100);
	    	            						newPlayer.setGuessing(false);
	    	            						playerList.set(playerList.indexOf(newPlayer), newPlayer);
	    	            					}
	    	            					else
	    	            					{
	    	            						out.println("Niepoprawne haslo.");
	    	            					}
	    	            				}
	    	            				// Jesli wyraz nie istnieje
	    	            				else 
	    	            				{
	    	            					out.println("Nie ma takiego wyrazu\nPopraw dane");
	    	            				}
	    								break;
	    		
				    		case "2" :  out.println("Menu");
				    					menuFlag = true;
										break;
				
							default  :  out.println("Nie ma takiej opcji\n");
										break;
    	            	}
                		
                		System.out.println("Klient: " + newPlayer.getPlayerId() + " " + newPlayer.getPlayerName() + " w menu zgadywania hasla,  powiedzial - " + userInput);
                	}
                	else
                	{
                		out.println("Nie mozna wysylac null.");
                	}
            	}
            	// Gdy gracz wymyœla has³o
            	else if(newPlayer.isNewPlayer() == false && newPlayer.isGuessing() == false && menuFlag == false)
            	{
    				if(password.equals(""))
    				{
	            		// Wyswietl glowne menu
	                	out.println( "Twoim zadaniem jest wymyœlenie has³a.\n" + 
	                				 "1 - Wymyœl nowe haslo\n2 - Menu");
	                	out.println("Wybierasz:");
	
	                	userInput = in.readLine();
	                	System.out.println("Klient: " + newPlayer.getPlayerId() + " " + newPlayer.getPlayerName() + " w menu wymyslania hasla,  powiedzial - " + userInput);
	
	                	if(userInput != null && userInput.length() > 0)
	                	{
	                		switch(userInput)
	    	            	{
		    	            	case "1" :  out.println("Podaj haslo:");
		    	            				userInput = in.readLine();
	
				            				// Jesli wyraz istnieje
				            				if(dictionary.Contains(userInput) && userInput.length() > 4)
				            				{
				            					password = userInput;
				            					passwordList.add(password);

				            					out.println("Haslo do odgadniecia to: " + password);
				            				}
				            				// Jesli wyraz nie istnieje
				            				else 
				            				{
				            					out.println("Nie ma takiego wyrazu lub wyraz jest za krótki");
				            				}

											break;
	    		
					    		case "2" :  out.println("Menu");
					    					menuFlag = true;
											break;
					
								default  :  out.println("Nie ma takiej opcji\n");
											break;
	    	            	}  
	                		

		                	System.out.println("Klient: " + newPlayer.getPlayerId() + " " + newPlayer.getPlayerName() + " w menu wymyslania hasla, powiedzial - " + userInput); 
	    				}                	
	                	else
	                	{
	                		out.println("Nie mozna wysylac null.");
	                	}
                	}
    				else
    				{
						Thread.sleep(100000);
    					out.println("Czekaj az ktos zgadnie haslo");
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
            		out.println("1 - Wyswietl swoje dane\n2 - Wyswietl statystyki wszystkich graczy\n" + 
            					"3 - Zmien swoja nazwe uzytkownika\n4 - Wroc do gry\n5 - Wyjdz");
            		out.println("Wybierasz:");
	
		           	userInput = in.readLine();
		           	System.out.println("Klient: " + newPlayer.getPlayerId() + " " + newPlayer.getPlayerName() + " w menu opcje,  powiedzial - " + userInput);
		
		           	if(userInput != null && userInput.length() > 0)
		           	{
		           		switch(userInput)
		            	{
		            		case "1" :  out.println("Twoje Dane");
		            					String temp = ShowMyStat(newPlayer);
		            					out.println(temp);
		            					menuFlag = false;
		            					break;
		            		
		            		case "2" :  out.println("Dane wszystkich graczy");
		            					out.println(ShowAllStats(playerList));
		            					menuFlag = false;
	        							break;
	   		
		            		case "3" :  out.println("Nowa nazwa:");
		            					if((userInput = in.readLine()) != null && userInput.length() > 0)
		            					{
		            						if(ChangeName(playerList, newPlayer, userInput))
		            						{
		            							out.println("Udalo sie zmienic nazwe");
		            						}
		            						else
		            						{
		            							out.println("Podana nazwa uzytkownika juz istenieje");
		            						}
		            					}
		            					else
		            					{
		            						out.println("Nie mozna wysylac null.");
		            					}
		            					menuFlag = false;
	        							break;
	   		
		            		case "4" :  out.println("Wracamy do gry");
		            					menuFlag = false;
										break;
		            		
		            		case "5" :  out.println("Wyjdz");
	        							break;
	   		
	        				default  :  out.println("Nie ma takiej opcji\n");
	        							break;
		            	} 
		           		
			           	System.out.println("Klient: " + newPlayer.getPlayerId() + " " + newPlayer.getPlayerName() + " w menu opcje,  wybral - " + userInput);
		           	}
		           	else
		           	{
		           		out.println("Nie mozna wysylac null.");
		           	}
            	}      	
            }
		}
        catch (InterruptedException e) 
        {
        	e.printStackTrace();
        }
        catch(IOException e)
		{
			System.out.println("Klient: " + newPlayer.getPlayerId() + " " + newPlayer.getPlayerName() + " zglosil blad: " + e);
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
		sb.append("----------------------------------------\n");
		sb.append("Player ID: " + player.getPlayerId() + "\n");
		sb.append("Player Name: " + player.getPlayerName() + "\n");
		sb.append("Score: " + player.getPlayerScore() + "\n");
		sb.append("----------------------------------------\n\n");
		
		return sb.toString();
		
	}
	
	public String ShowAllStats(ArrayList<Player> playerList)
	{
		// String builder, zeby wyslac tylko jedna linie odpwowiedzi
		StringBuilder sb = new StringBuilder();
		
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
			sb.append("There is no players");
		}
		sb.append("----------------------------------------\n\n");
		return sb.toString();
	}
}
