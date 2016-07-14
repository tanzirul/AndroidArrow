package org.ucr.androidarrow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TransitionParser tr=new TransitionParser();
		
		List <String> transitionList = null;
		List <String> mappList = null;
		try {
			transitionList=tr.parse("/Users/tanzirulazim/Dropbox/PhD/RollbackNRestart/code/apv-2048106513689650049.xml");
			
			for(String str: transitionList)
			{
				System.out.println(str);
			}
			
			FileUtils.writeLines(new File("transition.txt"), transitionList);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		MappingParser tr1=new MappingParser();
		try {
			mappList=tr1.parse("/Users/tanzirulazim/Dropbox/PhD/RollbackNRestart/code/out.txt");
			
			for(String str: mappList)
			{
				System.out.println(str);
			}
			
			FileUtils.writeLines(new File("mapping.txt"), mappList);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//now trace transition and mapp
		//Transition: actName+tag+type+tag+id+tag+idName+tag+title+tag+eventStr+tag+handlerStr
		//Mapper: className+":"+methodSignature+":"+methodName+":"+returnType+":"+parameterString
		
		for (String str: mappList)
		{
			String[] strs=str.split(":");
			
			String classStr=strs[0];
			String methStr=strs[2];
			
			classStr=classStr.replace("/", ".").replace(";", "");
			System.out.println(classStr);
			boolean found=false;
			List <String> getStr=null;
			for (String tsr: transitionList)
			{
				String []tsrs=tsr.split("<<found>>");
				
				String clsStr=tsrs[0];
				String meth2Str=tsrs[2];
				
				if(classStr.equals(clsStr) && methStr.contains(meth2Str))
				{
					String title=tsrs[4];
					String eventStr=tsrs[5];
					String handlerStr=tsrs[6];
					String idName=tsrs[3];
					found=true;
					getStr.add(tsr);
					break;
				}
				
				
				
				
			}
			
			if(!found)getStr.add("null"+str);
			
		}

	}

}
