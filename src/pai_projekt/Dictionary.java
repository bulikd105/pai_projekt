package pai_projekt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 * Klasa Dictionary
 * Klasa pobieraj¹ca pllik z baz¹ wyrazów w jêzyku angielskim.
 * Metoda Contains zwraca booleana mówi¹cego czy dany wyraz znajdujê siê na tej liœcie.
 */
public class Dictionary 
{
	private Set<String> wordsSet;
	
	public Dictionary() throws IOException
	{
		Path filePath = Paths.get("wordsList.txt");
		byte[] readBytes = Files.readAllBytes(filePath);
		String wordListContents = new String(readBytes, "UTF-8");
		String[] words = wordListContents.split("\n");
		wordsSet = new HashSet<>();
		Collections.addAll(wordsSet, words);
	}
	
	public boolean Contains(String word)
	{
		return wordsSet.contains(word);
	}
}
