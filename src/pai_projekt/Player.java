package pai_projekt;

/*
 * Klasa Player
 * Jest to klasa klienta, który pod³¹cza siê do servera, aby móc graæ w grê. 
 * Posiada ona podstawowe parametry u¿ytkownika, oraz metode odpowiedzialn¹ za komunikacjê z serwerem
 */
public class Player 
{
	private int playerId;			// unikalny klucz ka¿dego gracza
	private int playerScore; 		// Wynik danego gracza 
	private String playerName; 		// Nazwa gracza, mo¿liwa do zmiany
	private boolean isNewPlayer; 	// Flaga 1, czy gracz dopiero do³¹czy³
	private boolean isGuessing; 	// Flaga 2, do rozró¿niania graczy od osoby wymyœlaj¹cej has³o
	
	/*
	 * Flag1 = true && Flag2 = true 	- Cannot happen
	 * Flag1 = true && Flag2 = false 	- When new player is added
	 * Flag1 = false && Flag2 = false 	- When player is giving password
	 * Flag1 = false && Flag2 = true 	- When player joined the game
	 * */
	
	public Player(int playerId, String playerName)
	{
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerScore = 0;
		this.isNewPlayer = true;
		this.isGuessing = false;
	}

	public int getPlayerId() 
	{
		return playerId;
	}

	public int getPlayerScore() 
	{
		return playerScore;
	}

	public String getPlayerName() 
	{
		return playerName;
	}

	public boolean isGuessing() 
	{
		return isGuessing;
	}
	
	public boolean isNewPlayer() 
	{
		return isNewPlayer;
	}

	public void setNewPlayer(boolean isNewPlayer) 
	{
		this.isNewPlayer = isNewPlayer;
	}

	public void setGuessing(boolean isGuessing) 
	{
		this.isGuessing = isGuessing;
	}

	public void setPlayerScore(int playerScore) 
	{
		this.playerScore += playerScore;
	}

	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}
	
}
