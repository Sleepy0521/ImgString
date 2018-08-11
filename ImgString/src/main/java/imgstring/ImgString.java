package imgstring;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class ImgString {
	//final String base = "@#&$%*o!;.";// 字符串由复杂到简单
	public static final String base = "硕爱我你刘东￥！：。";
	private static String imgCode="";
	public ImgString() {
		super();
	}
	public ImgString(String imgCode) {
		super();
		this.imgCode = imgCode;
	}
	
	public String getImgCode() {
		return imgCode;
	}
	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}
	/**
	 * @param imgPath读取图片路径
	 * 根据图片路径返回图片转换为字符串后的样子
	 *            
	 */
	public static String imgToString(String imgPath) {
		try {
			BufferedImage image = ImageIO.read(new File(imgPath));
			for (int y = 0; y < image.getHeight(); y += 2) {
				for (int x = 0; x < image.getWidth(); x++) {
					int pixel = image.getRGB(x, y);
					int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
					float gray = 0.299f * r + 0.578f * g + 0.114f * b;
					int index = Math.round(gray * (base.length() + 1) / 255);
					imgCode+=index >= base.length() ? " " : String.valueOf(base.charAt(index));
				}
				//System.out.println();
				imgCode+="\r\n";
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return imgCode;
	}
	/**
	 * @param imgCode字符画的图片字符  @param imgPath输出图片路径
	 * @throws IOException 
	 * 
	 *            
	 */
	   public static void stringToImg(String imgPath) throws IOException{  
	        int width =3000;  
	        int height =3000;                                                                                                                                                                        
	        File file = new File(imgPath);                                                                                                                                                                          
	        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	        Graphics2D g2 = (Graphics2D)bi.getGraphics();  
	        g2.setFont(new Font("微软雅黑",Font.ITALIC,15));
	        g2.setBackground(Color.WHITE);  
	        g2.clearRect(0, 0, width, height);  
	        g2.setPaint(Color.BLACK);  
	        String[] str=imgCode.split("\r\n");                                                                                                                                                              
	        int y=0;
	        int i;
	        for(i=0;i<str.length;i++) {
	        	 g2.drawString(str[i], 0, y+=30); 
	        	 System.out.println(str[i]);
	        }                                                                                                                                                                   
	        ImageIO.write(bi, "jpg", file);  
	        
	    }  
	   /* 将字符串保存为.txt文件 */
		public static void stringToTxt(String fileName) {
			BufferedWriter bw=null;
			try {
				FileWriter fw=new FileWriter(fileName);
				bw=new BufferedWriter(fw);
				bw.write(imgCode);
				bw.flush();
			
			} catch (IOException ex) {
				ex.printStackTrace();
			}finally {
				if(bw!=null) {
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
 
	/**
	 * test
	 *
	 * @param args
	 * @throws Exception 
	 */
	public static void main(final String[] args) throws Exception {
		String imgCode=imgToString("src/main/resources/inImg*.jpg");
		//控制台打印字符画
		System.out.println(imgCode);
		//保存为txt文件
		stringToTxt("src/main/resources/outTxt.jpg");
		//保存为jpg图片
		stringToImg("src/main/resources/outImg.txt");
	}
}
