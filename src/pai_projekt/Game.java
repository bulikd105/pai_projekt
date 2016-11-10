package pai_projekt;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/*
 * Klasa Game
 * Odpowiedzialna za uruchomienie serwera, oraz pod³¹czanie nowych graczy do niego
 */

public class Game 
{
	// Lista do przechowywania graczy
	public static ArrayList<Player> playerList = new ArrayList<Player>();
	public static ArrayList<String> passwordList = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException 
	{
		String temp = "";
		passwordList.add(temp);
		
		int serverPort = 8060;
		
		ServerSocket serverSocket = new ServerSocket(serverPort);
		
		int playerId = 1;
		
		try
		{
			while(true)
			{
				Thread playerThread = new Thread(new Server(serverSocket.accept(), playerId++, playerList, passwordList));
				playerThread.start();
			}
		}
		finally
		{
			serverSocket.close();
		}
	}
}
