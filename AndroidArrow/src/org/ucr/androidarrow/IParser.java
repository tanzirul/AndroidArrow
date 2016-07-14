package org.ucr.androidarrow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IParser {
	//void initializeMapper();
	 
	 List <String> parse (String fileToParse) throws FileNotFoundException, IOException;
	 
	 
}
