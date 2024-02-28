package org.cassava.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageBase64Helper {

	private static BufferedImage resize(InputStream input) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte buffer[] = new byte[1024];
		int len = -1;

		while ((len = input.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}

		Image img = new ImageIcon(baos.toByteArray()).getImage();
		float ratio;
		int width, height;

		if (img.getWidth(null) < img.getHeight(null)) {
			ratio = ((float) img.getWidth(null) / (float) img.getHeight(null));

			width = Math.round(200 * ratio);
			height = 200;
		} else {
			ratio = ((float) img.getHeight(null) / (float) img.getWidth(null));

			height = Math.round(200 * ratio);
			width = 200;
		}
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = resizedImage.createGraphics();

		g2d.drawImage(img, 0, 0, width, height, null);

		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2d.dispose();

		return resizedImage;

	}

	public static void resizeImage(InputStream input, Path target) throws IOException {

		BufferedImage resizedImage = resize(input);

		ImageIO.write(resizedImage, "png", target.toFile());

	}

	public static byte[] resizeImage(InputStream input) throws IOException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		BufferedImage resizedImage = resize(input);

		ImageIO.write(resizedImage, "png", bos);

		return bos.toByteArray();

	}

	public static String toImageBase64(String name) {

		String base64Encoded = "";

		if (name == null)
			return base64Encoded;
		File f = new File(name);

		if (!f.exists() || f.isDirectory())
			return base64Encoded;
		byte[] bytes = null;

		try {

			bytes = Files.readAllBytes(f.toPath());
			byte[] encodeBase64 = Base64.getEncoder().encode(bytes);
			base64Encoded = new String(encodeBase64, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return base64Encoded;

	}

	public static String toImageBase64(byte[] bytes) {

		String base64Encoded = "";

		try {

			byte[] encodeBase64 = Base64.getEncoder().encode(bytes);
			base64Encoded = new String(encodeBase64, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return base64Encoded;

	}

}