package converter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class Converter {

	public static String ARROW_PATH=System.getenv("ARROW_PATH").toString();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName=args[0];
		try {
			List <String> lines=FileUtils.readLines(new File(fileName));
			Pattern pattern4;
			Matcher matcher;
			if(lines.size()>1)
			{
				pattern4=Pattern.compile("A: android:targetSdkVersion\\(.*\\)0x([\\d\\w]*)".trim());
				System.out.println(lines.get(1).trim());
				matcher=pattern4.matcher(lines.get(1).trim());
			}
			else
			{
				pattern4=Pattern.compile("A: android:minSdkVersion\\(.*\\)0x([\\d\\w]*)".trim());
				System.out.println(lines.get(0).trim());
				matcher=pattern4.matcher(lines.get(0).trim());
			}
				
			
			//Matcher matcher=pattern4.matcher(lines.get(1).trim());
			
			if(matcher.find())
			{
				int versionNo=Integer.parseInt(matcher.group(1), 16);
				if(versionNo==4)versionNo=8;
				
				List <String> lines2=FileUtils.readLines(new File(ARROW_PATH+"/drive.sh"));
				lines2.set(0, "ANDROID_VER=\"android-"+versionNo+"\"");
				FileUtils.writeLines(new File(ARROW_PATH+"/drive.sh"), lines2);
				System.out.println("minimum sdk written: "+ versionNo);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
