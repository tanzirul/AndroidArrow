package org.ucr.androidarrow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MappingParser implements IParser{

	
	
	@Override
	public List <String> parse(String fileToParse) throws IOException {
		// TODO Auto-generated method stub
		
		FileReader fir=new FileReader(new File(fileToParse));
		
		BufferedReader br=new BufferedReader(fir);
		String line;
		boolean markStart=false;
		List <String> block=new ArrayList<String>();
		List <String> data=new ArrayList<String>();
		while((line=br.readLine().trim())!=null)
		{
			if(line.equals("====== path ======"))
			{
				markStart=true;
			}
			
			else if(markStart && (line.startsWith("L") || line.startsWith("---->") || line.startsWith("-##->") ))
			{
				block.add(line);
			}
			else if(markStart)
			{
				markStart=false;
				break;
			}
		}
		
		if(block.size()>0)
		{
			for (String str : block)
			{
				Pattern pattern4=Pattern.compile("L((.*);)->((.*\\((.*)\\))(.)*)");
				Matcher matcher=pattern4.matcher(str);

				String className="";
				String methodSignature="";
				String methodName="";
				String returnType="";
				String parameterString="";
				if(matcher.find())
				{
					className=matcher.group(1);
					methodSignature=matcher.group(2);
					methodName=matcher.group(4);
					parameterString=matcher.group(5);
					returnType=matcher.group(6);

				}
				
				data.add(className+":"+methodSignature+":"+methodName+":"+returnType+":"+parameterString);
			}
			
			
			
			
			
			
		}
		return data;
	}

}
