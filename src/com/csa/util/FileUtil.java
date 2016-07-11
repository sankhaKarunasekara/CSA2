package com.csa.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FileUtil {

	public static ArrayList<String> fileList(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		ArrayList<String> allFiles = new ArrayList<>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				allFiles.add(listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		Collections.sort(allFiles, String.CASE_INSENSITIVE_ORDER);
		return allFiles;
	}

}
