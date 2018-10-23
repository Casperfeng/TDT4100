package sokoban;

import java.io.IOException;

public interface SokobanReadFile {
	
	public String readFile(String fileName) throws IOException;
		
	public void writeFile(String fileName) throws IOException;
	

}
