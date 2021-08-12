package imagem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
 
@SuppressWarnings("serial")
public class Janela extends JFrame {
	public static BufferedImage imagemAuxiliar;
	private JFileChooser JCAbrir = new JFileChooser();
	private JFileChooser JCSalvar = new JFileChooser();
	private String diretorio;
	private int x = 0;
	private int y = 0;

	private int xP = 10;
	private int yP = 10;

	private int xP2 = 0;
	private int yP2 = 0;

	private KeyListener k;
	private KeyListener a;
	private KeyListener s;

	private static String caminho;
	private BufferedImage imagemOriginal;

	private static BufferedImage imagemDF;

	ImagemJanela im = new ImagemJanela();

	private JMenu menu = new JMenu("Menu");
	private JMenuItem itemAbrir = new JMenuItem("Abrir");
	private JMenuItem itemSalvar = new JMenuItem("Salvar Como...");
	private JMenuItem itemReset = new JMenuItem("Reset");
	private JMenuItem itemSair = new JMenuItem("Sair");

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuRealce = new JMenu("Realce de características");
	private JMenuItem itemTonsCinza = new JMenuItem("Tons de Cinza");
	private JMenuItem itemPotencia = new JMenuItem("Potência");
	private JMenuItem itemLogaritmica = new JMenuItem("Logaritmica");
	private JMenuItem itemNegativo = new JMenuItem("Negativo");
	private JMenuItem itemSoma = new JMenuItem("Soma dos Pixels");
	private JMenuItem itemSub = new JMenuItem("Sub dos Pixels");
	private JTextField txtSub = new JTextField("10");

	private JMenu menuSegmentacao = new JMenu("Segmentação");
	private JTextField txtLimiar = new JTextField("80");
	private JMenuItem itemThresholding = new JMenuItem("Thresholding");

	private JMenu menuQuantizacao = new JMenu("Quantização");
	private JMenu menuCores = new JMenu("Cores");
	private JMenu menuTonsC = new JMenu("Tons de Cinza");
	private JMenuItem itemQ16Cores = new JMenuItem("16 Cores");
	private JMenuItem itemQ8Cores = new JMenuItem("8 Cores");
	private JMenuItem itemQ4Cores = new JMenuItem("4 Cores");
	private JMenuItem itemQ16 = new JMenuItem("16 Tons");
	private JMenuItem itemQ8 = new JMenuItem("8 Tons");
	private JMenuItem itemQ4 = new JMenuItem("4 Tons");

	private JMenu menuFiltros = new JMenu("Filtros");
	private JMenu menuPassaA = new JMenu("Passa-altas");
	private JMenu menuPAN = new JMenu("Normalização");
	private JMenu menuPAT = new JMenu("Truncamento");
	private JMenu menuPassaB = new JMenu("Passa-baixas");
	private JMenu menuPBN = new JMenu("Normalização");
	private JMenu menuPBT = new JMenu("Truncamento");

	private JMenuItem itemPrewittXN = new JMenuItem("Prewitt X N");
	private JMenuItem itemPrewittYN = new JMenuItem("Prewitt Y N");
	private JMenuItem itemSobelXN = new JMenuItem("Sobel X N");
	private JMenuItem itemSobelYN = new JMenuItem("Sobel Y N");
	private JMenuItem itemLaplacianoN = new JMenuItem("Laplaciano N");
	
	private JMenuItem itemPrewittXT = new JMenuItem("Prewitt X T");
	private JMenuItem itemPrewittYT = new JMenuItem("Prewitt Y T");
	private JMenuItem itemSobelXT = new JMenuItem("Sobel X T");
	private JMenuItem itemSobelYT = new JMenuItem("Sobel Y T");
	private JMenuItem itemLaplacianoT = new JMenuItem("Laplaciano T");

	private JMenuItem itemMediaN = new JMenuItem("Média N");
	private JMenuItem itemMediaPonderadaN = new JMenuItem("Média Ponderada N");
	private JMenuItem itemGaussianoN = new JMenuItem("Gaussiano N");
	
	private JMenuItem itemMediaT = new JMenuItem("Média T");
	private JMenuItem itemMediaPonderadaT = new JMenuItem("Média Ponderada T");
	private JMenuItem itemGaussianoT = new JMenuItem("Gaussiano T");

	private JMenuItem itemHistograma = new JMenuItem("Gerar Histograma");
	private JMenuItem itemEqualizar = new JMenuItem("Equalizar Histograma");

	private JMenu menuSobre = new JMenu("Sobre");
	private JMenuItem itemStatus = new JMenuItem("Status");

	private JLabel lblRodape = new JLabel(
			"TECLAS DE ATALHO: A - Abri | S - Salvar | Esc - Reset");

	public Janela() {
		inicializador();
		menu();
		icone();
		acoesMenu();
		acoesRealce();
		acaoSobre();
		acoesQuantizacao();
		acoesTeclas();
		rodape();
		acoesPassaAltasN();
		acoesPassaAltasT();
		acoesPassaBaixasN();
		acoesPassaBaixasT();
	}

	public void inicializador() {
		setTitle("Processamento de Imagem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(837, 565);
		setVisible(true);
		setLocationRelativeTo(null);

		getContentPane().add(im);

	}

	public void icone() {
		ImageIcon icon = new ImageIcon("imagem/icone.png");
		setIconImage(icon.getImage());
	}

	public void menu() {
		menu.add(itemAbrir);
		menu.add(itemSalvar);
		itemSalvar.setEnabled(false);
		menu.add(itemReset);
		itemReset.setEnabled(false);
		menu.add(itemSair);
		menuBar.add(menu);

		menuFiltros.add(menuRealce);
		menuPassaA.add(menuPAN);
		menuPassaA.add(menuPAT);
		menuPassaB.add(menuPBN);
		menuPassaB.add(menuPBT);
		menuFiltros.add(menuPassaA);
		menuFiltros.add(menuPassaB);
		menuFiltros.add(menuQuantizacao);
		menuQuantizacao.add(menuCores);
		menuQuantizacao.add(menuTonsC);
		menuFiltros.add(menuSegmentacao);
		menuSegmentacao.add(itemThresholding);
		menuSegmentacao.add(txtLimiar);
		
		menuCores.add(itemQ16Cores);
		menuCores.add(itemQ8Cores);
		menuCores.add(itemQ4Cores);
		menuTonsC.add(itemQ16);
		menuTonsC.add(itemQ8);
		menuTonsC.add(itemQ4);		
		
		menuRealce.add(itemTonsCinza);
		menuRealce.add(itemPotencia);
		menuRealce.add(itemLogaritmica);
		menuRealce.add(itemNegativo);
		menuRealce.add(itemSoma);
		menuRealce.add(itemSub);
		menuRealce.add(txtSub);
		
		menuPAN.add(itemPrewittXN);
		menuPAN.add(itemPrewittYN);
		menuPAN.add(itemSobelXN);
		menuPAN.add(itemSobelYN);
		menuPAN.add(itemLaplacianoN);
		
		menuPAT.add(itemPrewittXT);
		menuPAT.add(itemPrewittYT);
		menuPAT.add(itemSobelXT);
		menuPAT.add(itemSobelYT);
		menuPAT.add(itemLaplacianoT);
		
		menuPBN.add(itemMediaN);
		menuPBN.add(itemMediaPonderadaN);
		menuPBN.add(itemGaussianoN);
		
		menuPBT.add(itemMediaT);
		menuPBT.add(itemMediaPonderadaT);
		menuPBT.add(itemGaussianoT);
		
		
		itemTonsCinza.setEnabled(false);
		itemPotencia.setEnabled(false);
		itemLogaritmica.setEnabled(false);
		itemNegativo.setEnabled(false);
		itemSoma.setEnabled(false);
		itemSub.setEnabled(false);
		itemPrewittXN.setEnabled(false);
		itemPrewittXT.setEnabled(false);
		itemPrewittYN.setEnabled(false);
		itemPrewittYT.setEnabled(false);
		itemSobelXN.setEnabled(false);
		itemSobelXT.setEnabled(false);
		itemSobelYN.setEnabled(false);
		itemSobelYT.setEnabled(false);
		itemLaplacianoN.setEnabled(false);
		itemLaplacianoT.setEnabled(false);
		itemMediaN.setEnabled(false);
		itemMediaT.setEnabled(false);
		itemMediaPonderadaN.setEnabled(false);
		itemMediaPonderadaT.setEnabled(false);
		itemGaussianoN.setEnabled(false);
		itemGaussianoT.setEnabled(false);
		itemQ16Cores.setEnabled(false);
		itemQ8Cores.setEnabled(false);
		itemQ4Cores.setEnabled(false);
		itemQ16.setEnabled(false);
		itemQ8.setEnabled(false);
		itemQ4.setEnabled(false);
		itemThresholding.setEnabled(false);
		itemHistograma.setEnabled(false);
		itemEqualizar.setEnabled(false);	
		
		menuBar.add(menuFiltros);

		menuSobre.add(itemStatus);
		menuBar.add(menuSobre);

		setJMenuBar(menuBar);
	}

	public void acoesMenu() {
		itemAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});

		itemSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});

		itemReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(imagemDF);
			}

		});

		itemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	public void acoesQuantizacao() {
		itemQ16Cores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					imagemAuxiliar = Filtros.quantizacao16Cores(imagemDF);
					repaint();
				} catch (Exception e) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});
		itemQ8Cores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.quantizacao8Cores(imagemDF);
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});
		itemQ4Cores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.quantizacao4Cores(imagemDF);
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});
		itemQ16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.quantizacao16(imagemDF);
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});
		itemQ8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.quantizacao8(imagemDF);
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});
		itemQ4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.quantizacao4(imagemDF);
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});
	}

	public void acoesRealce() {

		itemTonsCinza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.tonsDeCinza(imagemDF);
					repaint();
				} catch (Exception e1) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}
			}
		});
		itemPotencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.potencia(imagemDF);
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}
			}
		});
		itemLogaritmica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.logaritimica(imagemDF);
					repaint();
				} catch (Exception e3) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}
			}
		});
		itemThresholding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sLimiar = txtLimiar.getText();
					int iLimia = Integer.valueOf(sLimiar);
					imagemAuxiliar = Filtros.thresholding(imagemDF, iLimia);
					repaint();
				} catch (Exception e4) {
					JOptionPane.showMessageDialog(null, "Digite um Limiar!!!");
				}
			}
		});
		itemNegativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.negativo(imagemDF);
					repaint();
				} catch (Exception e5) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}
			}
		});

		itemSoma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.soma(imagemDF);
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

		itemSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String s = txtSub.getText();
					int sub = Integer.valueOf(s);
					imagemAuxiliar = Filtros.subtracao(imagemDF, sub);
					repaint();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Digite um valor");
				}

			}
		});

	}

	public void acoesPassaBaixasN() {
		itemMediaN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF,
							"Media", true, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9); // Média
					repaint();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,
							"Erro: " + e2.getMessage());
				}

			}
		});

		itemMediaPonderadaN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF,
							"MediaPonderada", true, 1, 1, 1, 1, 2, 1, 1, 1, 1, 10); // Média
																				// Ponderada
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

		itemGaussianoN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF,
							"Gaussiano", true, 1, 2, 1, 2, 4, 2, 1, 2, 1, 16); // Gaussiano
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});
	}
	
	
	public void acoesPassaBaixasT() {
		itemMediaT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF,
							"Media", false, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9); // Média
					repaint();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,
							"Erro: " + e2.getMessage());
				}

			}
		});

		itemMediaPonderadaT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF,
							"MediaPonderada", false, 1, 1, 1, 1, 2, 1, 1, 1, 1, 10); // Média
																				// Ponderada
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

		itemGaussianoT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF,
							"Gaussiano", false, 1, 2, 1, 2, 4, 2, 1, 2, 1, 16); // Gaussiano
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});
	}

	public void acoesPassaAltasN() {
		itemLaplacianoN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF,
							"Laplaciano1", true, 0, 1, 0, 1, -4, 1, 0, 1, 0, 1); // Laplaciano
																			// GL1
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

		itemPrewittXN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF, "PH",
							true, -1, -1, -1, 0, 0, 0, 1, 1, 1, 1); // Prewitt
																// horizontal
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

		itemPrewittYN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF, "PV",
							true, -1, 0, 1, -1, 0, 1, -1, 0, 1, 1); // Prewitt
																// vertical
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

		itemSobelXN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF, "SH",
							true, -1, -2, -1, 0, 0, 0, 1, 2, 1, 1); // Sobel
																// horizontal
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

		itemSobelYN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF, "SV",
							true, -1, 0, 1, -2, 0, 2, -1, 0, 1, 1); // Sobel vertical
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

	}
	
	
	public void acoesPassaAltasT() {
		itemLaplacianoT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF,
							"Laplaciano1", false, 0, 1, 0, 1, -4, 1, 0, 1, 0, 1); // Laplaciano
																			// GL1
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

		itemPrewittXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF, "PH",
							false, -1, -1, -1, 0, 0, 0, 1, 1, 1, 1); // Prewitt
																// horizontal
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

		itemPrewittYT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF, "PV",
							false, -1, 0, 1, -1, 0, 1, -1, 0, 1, 1); // Prewitt
																// vertical
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

		itemSobelXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF, "SH",
							false, -1, -2, -1, 0, 0, 0, 1, 2, 1, 1); // Sobel
																// horizontal
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

		itemSobelYT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imagemAuxiliar = Filtros.colheDadosFiltro(imagemDF, "SV",
							false, -1, 0, 1, -2, 0, 2, -1, 0, 1, 1); // Sobel vertical
					repaint();
				} catch (Exception e2) {
					JOptionPane
							.showMessageDialog(null, "Importe uma imagem!!!");
				}

			}
		});

	}

	public void acaoSobre() {
		itemStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Desenvolvido por: Adenilson Soares" + "\n"
								+ "Email: adenilson_denis8@hotmail.com" + "\n"
								+ "Versão 3.0");
			}
		});
	}

	public void acoesTeclas() {
		k = new KeyAdapter() {

			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
					reset(imagemDF);
				}
			}
		};
		addKeyListener(k);

		a = new KeyAdapter() {
			public void keyPressed(KeyEvent a) {
				if (a.getKeyCode() == KeyEvent.VK_A) {
					int res = JCAbrir.showOpenDialog(JCAbrir);
					if (res == JFileChooser.APPROVE_OPTION) {
						caminho = JCAbrir.getSelectedFile().getAbsolutePath();
						try {
							imagemOriginal = ImageIO.read(new File(caminho));
							imagemDF = ImageIO.read(new File(caminho));
							repaint();

						} catch (IOException exc) {
							JOptionPane.showMessageDialog(
									null,
									"Erro ao carregar a imagem: "
											+ exc.getMessage());
						}
					}
				}
			}
		};
		addKeyListener(a);

		s = new KeyAdapter() {
			public void keyPressed(KeyEvent s) {
				if (s.getKeyCode() == KeyEvent.VK_S) {
					String caminho = "";
					int retorno = JCSalvar.showSaveDialog(null);
					if (retorno == JFileChooser.APPROVE_OPTION) {
						caminho = JCSalvar.getSelectedFile().getAbsolutePath();
					}
					if (!caminho.equals("")) {
						try {
							ImageIO.write(imagemDF, "jpg", new File(caminho
									+ ".jpg"));
							JOptionPane.showMessageDialog(null,
									"Imagem salva com sucesso!");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {

					}
				}
			}
		};
		addKeyListener(s);
	}

	public void abrir() {

		int res = JCAbrir.showOpenDialog(JCAbrir);
		if (res == JFileChooser.APPROVE_OPTION) {
			try {
				diretorio = JCAbrir.getSelectedFile().getAbsolutePath();
				imagemOriginal = ImageIO.read(new File(diretorio));
				imagemDF = ImageIO.read(new File(diretorio));
				imagemAuxiliar = ImageIO.read(new File(diretorio));
				
				
				itemTonsCinza.setEnabled(true);
				itemPotencia.setEnabled(true);
				itemLogaritmica.setEnabled(true);
				itemNegativo.setEnabled(true);
				itemSoma.setEnabled(true);
				itemSub.setEnabled(true);
				itemPrewittXN.setEnabled(true);
				itemPrewittXT.setEnabled(true);
				itemPrewittYN.setEnabled(true);
				itemPrewittYT.setEnabled(true);
				itemSobelXN.setEnabled(true);
				itemSobelXT.setEnabled(true);
				itemSobelYN.setEnabled(true);
				itemSobelYT.setEnabled(true);
				itemLaplacianoN.setEnabled(true);
				itemLaplacianoT.setEnabled(true);
				itemMediaN.setEnabled(true);
				itemMediaT.setEnabled(true);
				itemMediaPonderadaN.setEnabled(true);
				itemMediaPonderadaT.setEnabled(true);
				itemGaussianoN.setEnabled(true);
				itemGaussianoT.setEnabled(true);
				itemQ16Cores.setEnabled(true);
				itemQ8Cores.setEnabled(true);
				itemQ4Cores.setEnabled(true);
				itemQ16.setEnabled(true);
				itemQ8.setEnabled(true);
				itemQ4.setEnabled(true);
				itemThresholding.setEnabled(true);
				itemHistograma.setEnabled(true);
				
				itemSalvar.setEnabled(true);
				itemReset.setEnabled(true);
				repaint();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
			}

		}
	}

	public void salvar() {
		String caminho = "";
		int retorno = JCSalvar.showSaveDialog(null);

		if (retorno == JFileChooser.APPROVE_OPTION) {
			caminho = JCSalvar.getSelectedFile().getAbsolutePath();
		}
		if (!caminho.equals("")) {
			try {
				ImageIO.write(imagemAuxiliar, "jpg", new File(caminho + ".jpg"));
				JOptionPane
						.showMessageDialog(null, "Imagem salva com sucesso!");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void reset(BufferedImage imagem) {
		try {
			int largura = imagemOriginal.getWidth();
			int altura = imagemOriginal.getHeight();

			for (int i = 0; i < largura; i++) {
				for (int j = 0; j < altura; j++) {
					int rgb = imagemOriginal.getRGB(i, j);

					int r = (int) ((rgb & 0x00FF0000) >>> 16); // R
					int g = (int) ((rgb & 0x0000FF00) >>> 8); // G
					int b = (int) (rgb & 0x000000FF); // B

					Color cor = new Color(r, g, b);
					imagemDF.setRGB(i, j, cor.getRGB());
					repaint();

				}
			}
		} catch (Exception e6) {
			JOptionPane.showMessageDialog(null, "Importe uma imagem!!!");
		}
	}

	public void rodape() {
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(BorderLayout.SOUTH, painel);
		painel.setBackground(Color.LIGHT_GRAY);
		painel.add(lblRodape);
	}

	public class ImagemJanela extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(new Color(50, 80, 100));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			x = getWidth();
			y = getHeight();
			x = (x / 2) - 15;
			y = (y / 2) + (x / 2);
			xP2 = x + 20;
			yP2 = xP;
			g.drawImage(imagemOriginal, xP, yP, x, y, this);
			g.drawImage(imagemDF, xP2, yP2, x, y, this);
		}
	}

	public static void main(String[] args) {
		new Janela().setVisible(true);
	}
}
