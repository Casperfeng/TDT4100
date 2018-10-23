package sokoban;
import java.io.*;

/**
 * created 2/3/18
 * @author casperfeng
 * To be used for handling files for Sokoban
 */

public class SokobanFileHandler implements SokobanReadFile{
	
	BufferedReader reader;
	
	/**
	 * accesses the file with given fileName if it is under the Sokoban package directory.@
	 * @return the text contents of the file
	 */
	@Override
	public String readFile(String fileName) throws IOException {
		// TODO Auto-generated method stub
		String fetchedBoard = ""; //the board that file is pasted onto
		String filePlacement = "/Users/casperfeng/tdt4100-2018-master/git/tdt4100-2018/ovinger/src/sokoban/" + fileName; //accesses the file in the given location
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePlacement)); //reads the file from given location
			while (reader.ready()) { //while the file isnt empty
				fetchedBoard += reader.readLine();	
			}
			reader.close(); //closes the reader
		}
		catch (IOException e) { //prints out error messages (if there are any)
			e.printStackTrace();
		}
		return fetchedBoard; //returns the text contents of file
	}
	

	@Override
	public void writeFile(String currentBoard) throws IOException {
		// TODO Auto-generated method stub
		String fileName = "fileName";
		PrintWriter writer = new PrintWriter("/Users/casperfeng/tdt4100-2018-master/git/tdt4100-2018/ovinger/src/sokoban/" + fileName + ".txt");
		writer.println(currentBoard);
		writer.close();
	}
	
	public static void main(String[] args) {
		SokobanFileHandler rw = new SokobanFileHandler();
		String str = "";
		try {
			str = rw.readFile("level1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(str);

		try {
			rw.writeFile(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
