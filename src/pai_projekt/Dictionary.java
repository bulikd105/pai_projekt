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
 * Klasa pobieraj�ca pllik z baz� wyraz�w w j�zyku angielskim.
 * Metoda Contains zwraca booleana m�wi�cego czy dany wyraz znajduj� si� na tej li�cie.
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
