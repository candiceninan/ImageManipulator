/**
	A class with several methods to manipulate jpg images.
	@author Candice Ninan
**/
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Collections; 

public class ImageManipulator {

	public int getMedian(ArrayList<Integer> list) {
	//sort the list
	Collections.sort(list);
	//list divided by 2 is middle
    int median = list.size()/2;
    //if list is even 
    if (list.size()%2 == 0) {
    	//get the mean of the two middle numbers
    	return (list.get(median-1) + list.get(median)) / 2;
    } else {
    	//if list is odd, get the middle number
        return list.get(median);
    }
	}
	public void removePhotobomb(String directory, String outputfile) {
		int count = 0;
		//create files array
		File[] files = (new File(directory)).listFiles();
		//iterate over whole array
		for(int i = 0; i < files.length; i++) {
			//get all files that end with jpg
			if(files[i].getAbsolutePath().endsWith(".jpg")) {
				//add to the count
				count = count + 1;
			}
		}
		//create pictures array
		Picture[] pictures = new Picture[count];
		//create fill variable as index to fill pic array
		int fill = 0;
		//iterate over files
		for(int i = 0; i < files.length; i++) {
			//get all jpgs
			if(files[i].getName().endsWith(".jpg")) {
				//add to the list 
				pictures[fill] = new Picture(files[i].getAbsolutePath());
				fill++;
			}
		}
		//pictures are same width and height, so get the info from first item
		int width = pictures[0].getWidth();
		int height = pictures[0].getHeight();
		//create new picture
		Picture newPicture = new Picture(width, height);

		//iterate over each pixel
		for(int w = 0; w < width; w++) {
			for(int h = 0; h < height; h++) {
				//create arraylists for each red, blue and green values
				ArrayList<Integer> reds = new ArrayList<Integer>();
				ArrayList<Integer> blues = new ArrayList<Integer>();
				ArrayList<Integer> greens = new ArrayList<Integer>();
				//create new pixel
				Pixel newPixel = new Pixel();
				//iterate over each picture in list
				for(int i = 0; i < pictures.length; i++) {
					//get the pixels of each photo
					Pixel oldPixel = pictures[i].getPixel(w, h);
					//add the pixelf for each to the list
					reds.add(oldPixel.getRed());
					blues.add(oldPixel.getBlue());
					greens.add(oldPixel.getGreen());
				}
				//set the new pixel to the median of the arraylist of each color
				newPixel.setRed(getMedian(reds));
				newPixel.setGreen(getMedian(greens));
				newPixel.setBlue(getMedian(blues));
				newPicture.setPixel(w, h, newPixel);
			}
		}
		//store picture
		newPicture.store(outputfile);
	}
	public void zoomMiddle(String inputfile, String outputfile) {
		//create new picture
		Picture picture = new Picture(inputfile);
		//get width and height
		int width = picture.getWidth()+1;
		int height = picture.getHeight()+1;
		//create new picture
		Picture newPicture = new Picture(width, height);
		//iterate over pixels starting with the middle portion (to get zoomed version)
		for(int w = width/4; w < 3*width/4; w++) {
			for(int h = height/4; h < 3*height/4; h++) {
				//get old pixel value
				Pixel oldPixel = picture.getPixel(w, h);
				//create new variables in order to get the zoomed part
				int newW = ((w-width/4)*2);
				int newH = ((h-height/4)*2);
				//set each pixel to the zoomed area (4 pixels each)
				newPicture.setPixel(newW, newH, oldPixel);
				newPicture.setPixel(newW+1, newH, oldPixel);
				newPicture.setPixel(newW, newH+1, oldPixel);
				newPicture.setPixel(newW+1, newH+1, oldPixel);
				//newPicture.setPixel(w, h, newPixel);
			}

		}
		//store picture
		newPicture.store(outputfile);
	}
	
 }
