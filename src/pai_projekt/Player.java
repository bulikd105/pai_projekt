package pai_projekt;

/*
 * Klasa Player
 * Jest to klasa klienta, który pod³¹cza siê do servera, aby móc graæ w grê. 
 * Posiada ona podstawowe parametry u¿ytkownika, oraz metode odpowiedzialn¹ za komunikacjê z serwerem
 */
public class Player 
{
	private int playerId;
	private int playerScore;
	private String playerName;
	
	public Player(int playerId, String playerName)
	{
		this.playerId = playerId;
		this.playerName = playerName;
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

	public void setPlayerScore(int playerScore) 
	{
		this.playerScore = playerScore;
	}

	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}
	
}
