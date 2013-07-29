package main;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Main {
//
	public static void main(String args[]) throws Exception{
		
		String srcFileName = "001";
		String srcDirPath = "resources/src/";
		String dstDirPath = "resources/dst/";
		
		File graSrcFile = new File(srcDirPath+srcFileName+"_gray.png");
		File bgrSrcFile = new File(srcDirPath+srcFileName+"_bgr.png");
		
		BufferedImage graSrcImg = ImageIO.read(graSrcFile);
		BufferedImage bgrSrcImg = ImageIO.read(bgrSrcFile);
		
		int w = graSrcImg.getWidth(), h = graSrcImg.getHeight();
		
		// パックされたARGB値を取得
		int graSrc2d[][] = new int[h][w];
		int bgrSrc2d[][] = new int[h][w];
		
		for(int y = 0; y < h; y++){
			for(int x = 0; x < w; x++){
				graSrc2d[y][x] = graSrcImg.getRGB(x, y);
				bgrSrc2d[y][x] = bgrSrcImg.getRGB(x, y);
			}
		}
		
		/*
		 *  このARGB値が異なることが問題
		 */
//		for(int y = 0; y < h; y++){
//			for(int x = 0; x < w; x++){
//				System.out.println(graSrc2d[y][x] + " : " + bgrSrc2d[y][x]);
//			}
//		}
	
		// ARGB値を各成分に分解する
		int graSrc3d[][][] = new int[h][w][4];
		int bgrSrc3d[][][] = new int[h][w][4];
		
		for(int y = 0; y < h; y++){
			for(int x = 0; x < w; x++){
				for(int i = 0; i < 4; i++){
					graSrc3d[y][x][i] = graSrc2d[y][x] >> (24-i*8) & 0xff;
					bgrSrc3d[y][x][i] = bgrSrc2d[y][x] >> (24-i*8) & 0xff;
				}
			}
		}
		
		/*
		 * そもそも各成分の値自体が違う
		 * （グレースケールの方が高い = 明るい)
		 */
//		for(int y = 0; y < h; y++){
//			for(int x = 0; x < w; x++){
//				for(int i = 0; i < 4; i++){
//					System.out.println(graSrc3d[y][x][i] + " : " + bgrSrc3d[y][x][i]);
//				}
//			}
//		}
		
		/*
		 * なので、何も考えず
		 * BufferedImage.TYPE_INT_ARGB で出力すると、
		 * 結果が変わってしまう
		 */
//		BufferedImage graDstImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//		BufferedImage bgrDstImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//		
//		for(int y = 0; y < h; y++){
//			for(int x = 0; x < w; x++){
//				graDstImg.setRGB(x, y, graSrcImg.getRGB(x, y));
//				bgrDstImg.setRGB(x, y, bgrSrcImg.getRGB(x, y));
//			}
//		}
//		
//		ImageIO.write(graDstImg, "png", new File(dstDirPath+srcFileName+"_gra-abgr.png"));
//		ImageIO.write(bgrDstImg, "png", new File(dstDirPath+srcFileName+"_bgr-abgr.png"));

		/*
		 * しかし、DataBuffer のレベルだと同じ値となっている
		 */
//		DataBuffer graSrcBuf = graSrcImg.getRaster().getDataBuffer();
//		DataBuffer bgrSrcBuf = bgrSrcImg.getRaster().getDataBuffer();
//		
//		for(int y = 0; y < h; y++){
//			for(int x = 0; x < w; x++){
//				System.out.println(graSrcBuf.getElem(y*w+x) + " : " + bgrSrcBuf.getElem((y*w+x)*3+1));
//			}
//		}
		
		
	}
}
