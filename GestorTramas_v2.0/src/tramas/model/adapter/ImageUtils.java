package tramas.model.adapter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageUtils {
	
	public static byte [] imageToByteArray(Image image) throws IOException {
		BufferedImage bi = SwingFXUtils.fromFXImage(image, null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, "png", baos);
		byte [] content  = baos.toByteArray();
		baos.close(); 
		return content;
	}
	
	public static Image byteArrayToImage(byte [] content) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(content);
		BufferedImage bi = ImageIO.read(bais);
		Image image = SwingFXUtils.toFXImage(bi, null);
		bais.close(); 
		return image;
	}
	
	public static String encodeImage(Image image) {
		try {
			return Base64.encode(imageToByteArray(image));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Image decodeImage(String code) {
		try {
			return byteArrayToImage(Base64.decode(code));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
