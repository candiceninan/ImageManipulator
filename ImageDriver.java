/**
Tests the image manipulator by creating a new instance and running it with appropriate file names
@author Candice Ninan
**/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ImageDriver {

	public static void main(String[] args) {

		ImageManipulator im = new ImageManipulator();

		// command line argument catch
		if(args.length != 1) {					
			System.out.println("Usage: java FileInputCSV <filename>");
			System.exit(1);				
		}

		//store argument as filename
		String directory = args[0];

		im.removePhotobomb(directory, "nophotobomb.jpg");
		im.zoomMiddle("nophotobomb.jpg", "zoomed.jpg"); 
	}
}
