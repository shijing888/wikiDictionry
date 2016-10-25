package com.entitylinking.entitylinking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.entitylinking.wikidictionary.NormalizeMention;

import difflib.StringUtills;

public class NormDict {

	public static void main(String args[]){
		String rpath = "H:\\MysqlData\\synonymsDict3.txt";
		String wpath = "H:\\MysqlData\\synonymsDict4.txt";
		filterDict(rpath, wpath);
	}
	
	public static void filterDict(String rpath,String wpath){
		
		try {
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(rpath)), "UTF-8"));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(wpath)),"UTF-8"));
			String line;
			int i=0;
			while((line=bReader.readLine())!=null){
				if(line.length()==0)
					continue;
				if(!isDelete(line)){
					String[] lineArray = line.split("\t\\|\\|\t");
					if(lineArray.length==2){
						if(lineArray[0].length()>0 && lineArray[1].length()>0)
							line = NormalizeMention.getNormalizeMention(lineArray[0]) + "\t||\t" + StringUtills.join(NormalizeMention.getNormalizeMentionList(lineArray[1].split("\t\\|\t")), "\t|\t");
						if((i++)%10000==0)
							System.out.println("i="+i);
						writer.write(line);
						writer.write("\n");
					}
					
				}
			}
			bReader.close();
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isDelete(String strin){
		if(strin.contains("Category:")){
			return true;
		}else{
			String lineArray[]=strin.split("\t\\|\\|\t");
			if(lineArray.length==2){
				if(lineArray[0].trim().isEmpty()||lineArray[1].trim().isEmpty()){
					return true;
				}
			}
			
		}
		return false;
		
	}
}
