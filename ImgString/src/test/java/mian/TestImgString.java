package mian;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public class TestImgString {
	public static void main(String[] args) throws IOException {
		ImageProcesser imageProcesser=new ImageProcesser();
		imageProcesser=imageProcesser.toBitmapConvert("src/main/resources/abc.png");
		String imgCode=imageProcesser.getImgString();
		BufferedImage image=imageProcesser.stringToImg(imgCode);
		ImageIO.write(image,"JPEG",new File("src/main/resources/abc2.jpg"));
		
		
		
		
		
	}

}
