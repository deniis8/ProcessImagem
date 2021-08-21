package imagem;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Filtros {

	private static int[][] matrizR;
	private static int[][] matrizG;
	private static int[][] matrizB;
	private static int gR;
	private static int gG;
	private static int gB;
	private static int menorR = 0;
	private static int menorG = 0;
	private static int menorB = 0;
	private static int maiorR = 0;
	private static int maiorG = 0;
	private static int maiorB = 0;

	public static BufferedImage colheDadosFiltro(BufferedImage imagem, String filtro, boolean normalizacao, int c1,
			int c2, int c3, int c4, int c5, int c6, int c7, int c8, int c9, int dvM) {

		int xMax = imagem.getWidth() - 1;
		int yMax = imagem.getHeight() - 1;

		matrizR = new int[imagem.getWidth()][imagem.getHeight()];
		matrizG = new int[imagem.getWidth()][imagem.getHeight()];
		matrizB = new int[imagem.getWidth()][imagem.getHeight()];

		for (int i = 1; i < xMax; i++) {
			for (int j = 1; j < yMax; j++) {

				int vC1 = imagem.getRGB(i - 1, j - 1);
				int rgb1 = (int) ((vC1 & 0x00FF0000) >>> 16);
				int vC2 = imagem.getRGB(i - 1, j);
				int rgb2 = (int) ((vC2 & 0x00FF0000) >>> 16);
				int vC3 = imagem.getRGB(i - 1, j + 1);
				int rgb3 = (int) ((vC3 & 0x00FF0000) >>> 16);
				int vC4 = imagem.getRGB(i, j - 1);
				int rgb4 = (int) ((vC4 & 0x00FF0000) >>> 16);
				int vC5 = imagem.getRGB(i, j);
				int rgb5 = (int) ((vC5 & 0x00FF0000) >>> 16);
				int vC6 = imagem.getRGB(i, j + 1);
				int rgb6 = (int) ((vC6 & 0x00FF0000) >>> 16);
				int vC7 = imagem.getRGB(i + 1, j - 1);
				int rgb7 = (int) ((vC7 & 0x00FF0000) >>> 16);
				int vC8 = imagem.getRGB(i + 1, j);
				int rgb8 = (int) ((vC8 & 0x00FF0000) >>> 16);
				int vC9 = imagem.getRGB(i + 1, j + 1);
				int rgb9 = (int) ((vC9 & 0x00FF0000) >>> 16);

				int vermelho = ((rgb1 * c1) + (rgb2 * c2) + (rgb3 * c3) + (rgb4 * c4) + (rgb5 * c5) + (rgb6 * c6)
						+ (rgb7 * c7) + (rgb8 * c8) + (rgb9 * c9)) / dvM;

				int vC10 = imagem.getRGB(i - 1, j - 1);
				int rgb10 = (int) ((vC10 & 0x0000FF00) >>> 8);
				int vC11 = imagem.getRGB(i - 1, j);
				int rgb11 = (int) ((vC11 & 0x0000FF00) >>> 8);
				int vC12 = imagem.getRGB(i - 1, j + 1);
				int rgb12 = (int) ((vC12 & 0x0000FF00) >>> 8);
				int vC13 = imagem.getRGB(i - 1, j);
				int rgb13 = (int) ((vC13 & 0x0000FF00) >>> 8);
				int vC14 = imagem.getRGB(i, j);
				int rgb14 = (int) ((vC14 & 0x0000FF00) >>> 8);
				int vC15 = imagem.getRGB(i, j + 1);
				int rgb15 = (int) ((vC15 & 0x0000FF00) >>> 8);
				int vC16 = imagem.getRGB(i + 1, j - 1);
				int rgb16 = (int) ((vC16 & 0x0000FF00) >>> 8);
				int vC17 = imagem.getRGB(i + 1, j);
				int rgb17 = (int) ((vC17 & 0x0000FF00) >>> 8);
				int vC18 = imagem.getRGB(i + 1, j + 1);
				int rgb18 = (int) ((vC18 & 0x0000FF00) >>> 8);

				int verde = ((rgb10 * c1) + (rgb11 * c2) + (rgb12 * c3) + (rgb13 * c4) + (rgb14 * c5) + (rgb15 * c6)
						+ (rgb16 * c7) + (rgb17 * c8) + (rgb18 * c9)) / dvM;

				int vC19 = imagem.getRGB(i - 1, j - 1);
				int rgb19 = (int) (vC19 & 0x000000FF);
				int vC20 = imagem.getRGB(i - 1, j);
				int rgb20 = (int) (vC20 & 0x000000FF);
				int vC21 = imagem.getRGB(i - 1, j + 1);
				int rgb21 = (int) (vC21 & 0x000000FF);
				int vC22 = imagem.getRGB(i, j - 1);
				int rgb22 = (int) (vC22 & 0x000000FF);
				int vC23 = imagem.getRGB(i, j);
				int rgb23 = (int) (vC23 & 0x000000FF);
				int vC24 = imagem.getRGB(i, j + 1);
				int rgb24 = (int) (vC24 & 0x000000FF);
				int vC25 = imagem.getRGB(i + 1, j - 1);
				int rgb25 = (int) (vC25 & 0x000000FF);
				int vC26 = imagem.getRGB(i + 1, j);
				int rgb26 = (int) (vC26 & 0x000000FF);
				int vC27 = imagem.getRGB(i + 1, j + 1);
				int rgb27 = (int) (vC27 & 0x000000FF);

				int azul = ((rgb19 * c1) + (rgb20 * c2) + (rgb21 * c3) + (rgb22 * c4) + (rgb23 * c5) + (rgb24 * c6)
						+ (rgb25 * c7) + (rgb26 * c8) + (rgb27 * c9)) / dvM;

				matrizR[i][j] = vermelho;
				matrizG[i][j] = verde;
				matrizB[i][j] = azul;
			}
		}
		if (normalizacao) {
			normalizacao(imagem);
		} else {
			truncamento(imagem, filtro);
		}

		return imagem;
	}

	public static BufferedImage normalizacao(BufferedImage imagem) {

		maiorR = maior(matrizR);
		maiorG = maior(matrizG);
		maiorB = maior(matrizB);

		menorR = menor(matrizR);
		menorG = menor(matrizG);
		menorB = menor(matrizB);

		int xMax = imagem.getWidth() - 1;
		int yMax = imagem.getHeight() - 1;

		for (int i = 1; i < xMax; i++) {
			for (int j = 1; j < yMax; j++) {
				gR = ((matrizR[i][j] - (menorR)) * 255) / (maiorR - menorR);
				gG = ((matrizG[i][j] - (menorG)) * 255) / (maiorG - menorG);
				gB = ((matrizB[i][j] - (menorB)) * 255) / (maiorB - menorB);

				Color cor = new Color(gR, gG, gB);
				imagem.setRGB(i, j, cor.getRGB());
			}
		}
		return imagem;
	}

	public static int maior(int[][] maior) {
		int vMaior = 0;

		for (int i = 0; i < maior.length; i++) {
			for (int j = 0; j < maior[0].length; j++) {
				if (maior[i][j] > vMaior) {
					vMaior = maior[i][j];
				}
			}

		}
		return vMaior;
	}

	public static int menor(int[][] menor) {
		int vMenor = 0;
		for (int i = 0; i < menor.length; i++) {
			for (int j = 0; j < menor[0].length; j++) {
				if (menor[i][j] < vMenor) {
					vMenor = menor[i][j];
				}
			}

		}
		return vMenor;
	}

	public static BufferedImage truncamento(BufferedImage imagem, String filtro) {
		int xMax = imagem.getWidth() - 1;
		int yMax = imagem.getHeight() - 1;

		for (int i = 1; i < xMax; i++) {
			for (int j = 1; j < yMax; j++) {

				if ((filtro == "PH") || (filtro == "PV") || (filtro == "SH") || (filtro == "SV")) {
					matrizR[i - 1][j - 1] = matrizR[i - 1][j - 1] + 127;
					matrizG[i - 1][j - 1] = matrizG[i - 1][j - 1] + 127;
					matrizB[i - 1][j - 1] = matrizB[i - 1][j - 1] + 127;
				}

				if (matrizR[i - 1][j - 1] >= 255) {
					matrizR[i - 1][j - 1] = 255;
				}
				if (matrizR[i - 1][j - 1] <= 0) {
					matrizR[i - 1][j - 1] = 0;
				}
				if (matrizG[i - 1][j - 1] >= 255) {
					matrizG[i - 1][j - 1] = 255;
				}
				if (matrizG[i - 1][j - 1] <= 0) {
					matrizG[i - 1][j - 1] = 0;
				}
				if (matrizB[i - 1][j - 1] >= 255) {
					matrizB[i - 1][j - 1] = 255;
				}
				if (matrizB[i - 1][j - 1] <= 0) {
					matrizB[i - 1][j - 1] = 0;
				}

				Color cor = new Color(matrizR[i - 1][j - 1], matrizG[i - 1][j - 1], matrizB[i - 1][j - 1]);
				imagem.setRGB(i, j, cor.getRGB());
			}
		}
		return imagem;
	}

	public static BufferedImage tonsDeCinza(BufferedImage imagem) {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();
		int media = 0;
		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				int rgb = imagem.getRGB(i, j);

				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				media = (r + g + b) / 3;

				Color cor = new Color(media, media, media);
				imagem.setRGB(i, j, cor.getRGB());
			}
		}
		return imagem;
	}

	public static BufferedImage potencia(BufferedImage imagem) {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();

		int expoente = 2;
		double c = 255 / Math.pow(256, expoente);

		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {

				int rgb = imagem.getRGB(i, j);

				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				r = (int) (c * Math.pow(r, expoente));
				g = (int) (c * Math.pow(g, expoente));
				b = (int) (c * Math.pow(b, expoente));

				Color cor = new Color(r, g, b);

				imagem.setRGB(i, j, cor.getRGB());

			}
		}
		return imagem;
	}

	public static BufferedImage logaritimica(BufferedImage imagem) {

		int largura = imagem.getWidth();
		int altura = imagem.getHeight();

		double c = 255 / Math.log(256);

		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {

				int rgb = imagem.getRGB(i, j);

				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				r = (int) (c * Math.log(1 + r));
				g = (int) (c * Math.log(1 + g));
				b = (int) (c * Math.log(1 + b));

				Color cor = new Color(r, g, b);

				imagem.setRGB(i, j, cor.getRGB());
			}
		}
		return imagem;
	}

	public static BufferedImage thresholding(BufferedImage imagem, int limiar) {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();
		int media = 0;

		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				int rgb = imagem.getRGB(i, j);

				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				media = (r + g + b) / 3;
				Color preto = new Color(255, 255, 255);
				Color branco = new Color(0, 0, 0);

				if (media < limiar) {
					imagem.setRGB(i, j, branco.getRGB());
				} else {
					imagem.setRGB(i, j, preto.getRGB());
				}
			}
		}
		return imagem;
	}

	public static BufferedImage negativo(BufferedImage imagem) {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();

		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {

				int rgb = imagem.getRGB(i, j);

				int r = 255 - (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = 255 - (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = 255 - (int) (rgb & 0x000000FF); // B

				Color cor = new Color(r, g, b);
				imagem.setRGB(i, j, cor.getRGB());
			}
		}
		return imagem;
	}

	public static BufferedImage soma(BufferedImage imagem) {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();
		int red = 0;
		int green = 0;
		int blue = 0;

		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {

				int rgb = imagem.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				int rgb2 = imagem.getRGB(i, j);
				int r2 = (int) ((rgb2 & 0x00FF0000) >>> 16); // R
				int g2 = (int) ((rgb2 & 0x0000FF00) >>> 8); // G
				int b2 = (int) (rgb2 & 0x000000FF); // B

				red = r + r2;
				green = g + g2;
				blue = b + b2;
				if (red > 255) {
					red = 255;
				}
				if (green > 255) {
					green = 255;
				}
				if (blue > 255) {
					blue = 255;
				}

				Color cor = new Color(red, green, blue);
				imagem.setRGB(i, j, cor.getRGB());
			}
		}
		return imagem;
	}

	public static BufferedImage subtracao(BufferedImage imagem, int sub) {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();
		int red = 0;
		int green = 0;
		int blue = 0;

		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {

				int rgb = imagem.getRGB(i, j);
				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				red = r - sub;
				green = g - sub;
				blue = b - sub;

				if (red < 0) {
					red = 0;
				}
				if (green < 0) {
					green = 0;
				}
				if (blue < 0) {
					blue = 0;
				}

				Color cor = new Color(red, green, blue);
				imagem.setRGB(i, j, cor.getRGB());
			}
		}
		return imagem;
	}

	public static BufferedImage quantizacao16Cores(BufferedImage imagem) {

		int largura = imagem.getWidth();
		int altura = imagem.getHeight();

		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				int rgb = imagem.getRGB(i, j);

				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				// 0, 17, 34, 51, 68, 85, 102, 119, 136, 153, 170, 187, 204,
				// 221, 238, 255

				if ((r > 0) && (r <= 17)) {
					r = 0;
				}
				if ((r > 17) && (r <= 34)) {
					r = 17;
				}
				if ((r > 34) && (r <= 51)) {
					r = 34;
				}
				if ((r > 51) && (r <= 68)) {
					r = 51;
				}
				if ((r > 68) && (r <= 85)) {
					r = 68;
				}
				if ((r > 85) && (r <= 102)) {
					r = 85;
				}
				if ((r > 102) && (r <= 119)) {
					r = 102;
				}
				if ((r > 119) && (r <= 136)) {
					r = 119;
				}
				if ((r > 136) && (r <= 153)) {
					r = 136;
				}
				if ((r > 153) && (r <= 170)) {
					r = 153;
				}
				if ((r > 170) && (r <= 187)) {
					r = 170;
				}
				if ((r > 187) && (r <= 204)) {
					r = 187;
				}
				if ((r > 204) && (r <= 221)) {
					r = 204;
				}
				if ((r > 221) && (r <= 238)) {
					r = 221;
				}
				if ((r > 238) && (r < 255)) {
					r = 238;
				}
				if (r == 255) {
					r = 255;
				}
				// 0, 17, 34, 51, 68, 85, 102, 119, 136, 153, 170, 187, 204,
				// 221, 238, 255

				if ((g > 0) && (g <= 17)) {
					g = 0;
				}
				if ((g > 17) && (g <= 34)) {
					g = 17;
				}
				if ((g > 34) && (g <= 51)) {
					g = 34;
				}
				if ((g > 51) && (g <= 68)) {
					g = 51;
				}
				if ((g > 68) && (g <= 85)) {
					g = 68;
				}
				if ((g > 85) && (g <= 102)) {
					g = 85;
				}
				if ((g > 102) && (g <= 119)) {
					g = 102;
				}
				if ((g > 119) && (g <= 136)) {
					g = 119;
				}
				if ((g > 136) && (g <= 153)) {
					g = 136;
				}
				if ((g > 153) && (g <= 170)) {
					g = 153;
				}
				if ((g > 170) && (g <= 187)) {
					g = 170;
				}
				if ((g > 187) && (g <= 204)) {
					g = 187;
				}
				if ((g > 204) && (g <= 221)) {
					g = 204;
				}
				if ((g > 221) && (g <= 238)) {
					g = 221;
				}
				if ((g > 238) && (g < 255)) {
					g = 238;
				}
				if (g == 255) {
					g = 255;
				}

				// 0, 17, 34, 51, 68, 85, 102, 119, 136, 153, 170, 187, 204,
				// 221, 238, 255

				if ((b > 0) && (b <= 17)) {
					b = 0;
				}
				if ((b > 17) && (b <= 34)) {
					b = 17;
				}
				if ((b > 34) && (b <= 51)) {
					b = 34;
				}
				if ((b > 51) && (b <= 68)) {
					b = 51;
				}
				if ((b > 68) && (b <= 85)) {
					b = 68;
				}
				if ((b > 85) && (b <= 102)) {
					b = 85;
				}
				if ((b > 102) && (b <= 119)) {
					b = 102;
				}
				if ((b > 119) && (b <= 136)) {
					b = 119;
				}
				if ((b > 136) && (b <= 153)) {
					b = 136;
				}
				if ((b > 153) && (b <= 170)) {
					b = 153;
				}
				if ((b > 170) && (b <= 187)) {
					b = 170;
				}
				if ((b > 187) && (b <= 204)) {
					b = 187;
				}
				if ((b > 204) && (b <= 221)) {
					b = 204;
				}
				if ((b > 221) && (b <= 238)) {
					b = 221;
				}
				if ((b > 238) && (b < 255)) {
					b = 238;
				}
				if (b == 255) {
					b = 255;
				}

				/*
				 * System.out.println(r); System.out.println(g); System.out.println(b);
				 */
				Color cor = new Color(r, g, b);
				imagem.setRGB(i, j, cor.getRGB());
			}
		}
		return imagem;
	}

	public static BufferedImage quantizacao8Cores(BufferedImage imagem) {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();

		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				int rgb = imagem.getRGB(i, j);

				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				// 0, 36 , 72, 108, 144, 180, 216, 252 ou 255

				if ((r > 0) && (r <= 36)) {
					r = 0;
				}
				if ((r > 36) && (r <= 72)) {
					r = 36;
				}
				if ((r > 72) && (r <= 108)) {
					r = 72;
				}
				if ((r > 108) && (r <= 144)) {
					r = 108;
				}
				if ((r > 144) && (r <= 180)) {
					r = 144;
				}
				if ((r > 180) && (r <= 216)) {
					r = 180;
				}
				if ((r > 216) && (r < 255)) {//
					r = 216;
				}
				if (r == 255) {
					r = 255;
				}

				// 0, 36 , 72, 108, 144, 180, 216, 252 ou 255

				if ((g > 0) && (g <= 36)) {
					g = 0;
				}
				if ((g > 36) && (g <= 72)) {
					g = 36;
				}
				if ((g > 72) && (g <= 108)) {
					g = 72;
				}
				if ((g > 108) && (g <= 144)) {
					g = 108;
				}
				if ((g > 144) && (g <= 180)) {
					g = 144;
				}
				if ((g > 180) && (g <= 216)) {
					g = 180;
				}
				if ((g > 216) && (g < 255)) {//
					g = 216;
				}
				if (g == 255) {
					g = 255;
				}

				// 0, 36 , 72, 108, 144, 180, 216, 252 ou 255

				if ((b > 0) && (b <= 36)) {
					b = 0;
				}
				if ((b > 36) && (b <= 72)) {
					b = 36;
				}
				if ((b > 72) && (b <= 108)) {
					b = 72;
				}
				if ((b > 108) && (b <= 144)) {
					b = 108;
				}
				if ((b > 144) && (b <= 180)) {
					b = 144;
				}
				if ((b > 180) && (b <= 216)) {
					b = 180;
				}
				if ((b > 216) && (b < 255)) {//
					b = 216;
				}
				if (b == 255) {
					b = 255;
				}

				Color cor = new Color(r, g, b);
				imagem.setRGB(i, j, cor.getRGB());
			}
		}
		return imagem;
	}

	public static BufferedImage quantizacao4Cores(BufferedImage imagem) {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();

		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				int rgb = imagem.getRGB(i, j);

				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				// 0, 85, 170, 255

				if ((r >= 0) && (r <= 85)) {
					r = 0;
				}
				if ((r > 85) && (r <= 170)) {
					r = 85;
				}
				if ((r > 170) && (r < 255)) {
					r = 170;
				}
				if (r == 255) {
					r = 255;
				}

				// 0, 85, 170, 255

				if ((g >= 0) && (g <= 85)) {
					g = 0;
				}
				if ((g > 85) && (g <= 170)) {
					g = 85;
				}
				if ((g > 170) && (g < 255)) {
					g = 170;
				}
				if (g == 255) {
					g = 255;
				}

				// 0, 85, 170, 255

				if ((b >= 0) && (b <= 85)) {
					b = 0;
				}
				if ((b > 85) && (b <= 170)) {
					b = 85;
				}
				if ((b > 170) && (b < 255)) {
					b = 170;
				}
				if (b == 255) {
					b = 255;
				}

				Color cor = new Color(r, g, b);
				imagem.setRGB(i, j, cor.getRGB());
			}
		}
		return imagem;
	}

	public static BufferedImage quantizacao16(BufferedImage imagem) {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();
		int media = 0;
		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				int rgb = imagem.getRGB(i, j);

				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				media = (r + g + b) / 3;
				// 0, 17, 34, 51, 68, 85, 102, 119, 136, 153, 170, 187, 204,
				// 221, 238, 255

				if ((media > 0) && (media <= 17)) {
					media = 0;
				}
				if ((media > 17) && (media <= 34)) {
					media = 17;
				}
				if ((media > 34) && (media <= 51)) {
					media = 34;
				}
				if ((media > 51) && (media <= 68)) {
					media = 51;
				}
				if ((media > 68) && (media <= 85)) {
					media = 68;
				}
				if ((media > 85) && (media <= 102)) {
					media = 85;
				}
				if ((media > 102) && (media <= 119)) {
					media = 102;
				}
				if ((media > 119) && (media <= 136)) {
					media = 119;
				}
				if ((media > 136) && (media <= 153)) {
					media = 136;
				}
				if ((media > 153) && (media <= 170)) {
					media = 153;
				}
				if ((media > 170) && (media <= 187)) {
					media = 170;
				}
				if ((media > 187) && (media <= 204)) {
					media = 187;
				}
				if ((media > 204) && (media <= 221)) {
					media = 204;
				}
				if ((media > 221) && (media <= 238)) {
					media = 221;
				}
				if ((media > 238) && (media < 255)) {
					media = 238;
				}
				if (media == 255) {
					media = 255;
				}

				Color cor = new Color(media, media, media);
				imagem.setRGB(i, j, cor.getRGB());
			}
		}

		return imagem;
	}

	public static BufferedImage quantizacao8(BufferedImage imagem) {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();
		int media = 0;
		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				int rgb = imagem.getRGB(i, j);

				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				media = (r + g + b) / 3;

				// 0, 36 , 72, 108, 144, 180, 216, 252 ou 255

				if ((media > 0) && (media <= 36)) {
					media = 0;
				}
				if ((media > 36) && (media <= 72)) {
					media = 36;
				}
				if ((media > 72) && (media <= 108)) {
					media = 72;
				}
				if ((media > 108) && (media <= 144)) {
					media = 108;
				}
				if ((media > 144) && (media <= 180)) {
					media = 144;
				}
				if ((media > 180) && (media <= 216)) {
					media = 180;
				}
				if ((media > 216) && (media < 255)) {//
					media = 216;
				}
				if (media == 255) {
					media = 255;
				}

				Color cor = new Color(media, media, media);
				imagem.setRGB(i, j, cor.getRGB());
			}
		}

		return imagem;
	}

	public static BufferedImage quantizacao4(BufferedImage imagem) {
		int largura = imagem.getWidth();
		int altura = imagem.getHeight();
		int media = 0;
		for (int i = 0; i < largura; i++) {
			for (int j = 0; j < altura; j++) {
				int rgb = imagem.getRGB(i, j);

				int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
				int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
				int b = (int) (rgb & 0x000000FF); // B

				media = (r + g + b) / 3;

				// 0, 85, 170, 255

				if ((media > 0) && (media <= 85)) {
					media = 0;
				}
				if ((media > 85) && (media <= 170)) {
					media = 85;
				}
				if ((media > 170) && (media < 255)) {
					media = 170;
				}
				if (media == 255) {
					media = 255;
				}

				Color cor = new Color(media, media, media);
				imagem.setRGB(i, j, cor.getRGB());
			}
		}

		return imagem;
	}
}
