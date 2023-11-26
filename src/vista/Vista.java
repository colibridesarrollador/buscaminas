package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.Controlador;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton[][] celdas;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JLabel lblNewLabelFecha;
	private JLabel lblNewLabelHora;
	private JButton btnNewButtonComenzarPartida;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabelAvisos;
	private JLabel fondoInicio;
	private JButton btnNewButtonInformacion;
	private JPanel panelRegistro;
	private JPanel panelInfo;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton btnNewButtonVolverInfo;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabelFondoInformacion;
	private JLabel lblNewLabelHoraInfo;
	private JLabel lblNewLabelFechaInfo;
	private JCheckBox chckbxNewCheckBoxMusica;
	private JCheckBox chckbxNewCheckBoxMusicaTablero;
	private JLabel lblTiempoTranscurrido;
	private JButton btnNewButtonAbandonarPartida;
	private JLabel lblNewLabelAvisosPartida;
	private JPanel panelTablero;
	private JCheckBox chckbxMsicaMusicaRegistro;
	private JLabel lblNewLabelResultadoTiempoTrancurrido;
	private JLabel lblNewLabelResultadosPuntuacion;
	private JButton btnNewButtonResultadosVolverInicio;
	private JButton btnNewButtonMostrarMarcadoresResultados;
	private DefaultListModel<String> listaNombres;
	private JList<String> listRsultadosLista;
	private JLabel lblNewLabelResultadosJugador;
	private JCheckBox chckbxNewCheckBoxMusicaResultados;
	private JLabel lblNewLabelMensajeResultado;
	private JPanel panelResultados;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JTextField textFieldColumnas;
	private JTextField textFieldFilas;
	private JTextField textFieldMinas;
	private JLabel lblNewLabelPuntuacionTablero;
	private JLabel avisoResultados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					Controlador controlador = new Controlador(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Vista() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon imagenFondoResultados = new ImageIcon(getClass().getResource("/imagenes/img_resultados.png"));
		ImageIcon imagenFondoIinfo = new ImageIcon(getClass().getResource("/imagenes/img_info.png"));
		ImageIcon imagenFondoRegistro = new ImageIcon(getClass().getResource("/imagenes/img_registro.png"));
		listaNombres = new DefaultListModel<>();

		// FONDO INFO

		panelResultados = new JPanel();
		panelResultados.setBounds(-2, 0, 968, 644);
		panelResultados.setLayout(null);
		contentPane.add(panelResultados);

		avisoResultados = new JLabel("New label");
		avisoResultados.setHorizontalAlignment(SwingConstants.CENTER);
		avisoResultados.setFont(new Font("Tahoma", Font.BOLD, 35));
		avisoResultados.setForeground(new Color(255, 255, 255));
		avisoResultados.setBounds(10, 27, 937, 57);
		panelResultados.add(avisoResultados);

		JLabel lblNewLabel_8 = new JLabel("Puntuación:");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_8.setBounds(259, 416, 162, 18);
		panelResultados.add(lblNewLabel_8);

		JLabel lblNewLabel_10 = new JLabel("Tiempo Transcurrido:");
		lblNewLabel_10.setForeground(new Color(255, 255, 255));
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_10.setBounds(259, 375, 230, 17);
		panelResultados.add(lblNewLabel_10);

		chckbxNewCheckBoxMusicaResultados = new JCheckBox("Música");
		chckbxNewCheckBoxMusicaResultados.setSelected(true);
		chckbxNewCheckBoxMusicaResultados.setBounds(0, 6, 87, 21);
		panelResultados.add(chckbxNewCheckBoxMusicaResultados);

		lblNewLabelResultadoTiempoTrancurrido = new JLabel("");
		lblNewLabelResultadoTiempoTrancurrido.setForeground(new Color(255, 255, 255));
		lblNewLabelResultadoTiempoTrancurrido.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelResultadoTiempoTrancurrido.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelResultadoTiempoTrancurrido.setBounds(508, 375, 204, 17);
		panelResultados.add(lblNewLabelResultadoTiempoTrancurrido);

		lblNewLabelResultadosPuntuacion = new JLabel("");
		lblNewLabelResultadosPuntuacion.setForeground(new Color(255, 255, 255));
		lblNewLabelResultadosPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelResultadosPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelResultadosPuntuacion.setBounds(508, 416, 204, 18);
		panelResultados.add(lblNewLabelResultadosPuntuacion);

		btnNewButtonResultadosVolverInicio = new JButton("Volver INICIO");
		btnNewButtonResultadosVolverInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButtonResultadosVolverInicio.setBounds(789, 602, 169, 32);
		panelResultados.add(btnNewButtonResultadosVolverInicio);

		btnNewButtonMostrarMarcadoresResultados = new JButton("Mostrar marcadores");
		btnNewButtonMostrarMarcadoresResultados.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButtonMostrarMarcadoresResultados.setBounds(10, 602, 282, 32);
		panelResultados.add(btnNewButtonMostrarMarcadoresResultados);
		listRsultadosLista = new JList(listaNombres);
		listRsultadosLista.setEnabled(true);
		listRsultadosLista.setBounds(94, 155, 342, 142);
		listRsultadosLista.setVisible(false);
		panelResultados.add(listRsultadosLista);

		JLabel lblNewLabel_11 = new JLabel("Jugador: ");
		lblNewLabel_11.setForeground(new Color(255, 255, 255));
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_11.setBounds(259, 338, 164, 19);
		panelResultados.add(lblNewLabel_11);

		lblNewLabelResultadosJugador = new JLabel("");
		lblNewLabelResultadosJugador.setForeground(new Color(255, 255, 255));
		lblNewLabelResultadosJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelResultadosJugador.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelResultadosJugador.setBounds(508, 338, 204, 19);
		panelResultados.add(lblNewLabelResultadosJugador);

		// IMAGEN RESULTADOS
		JLabel lblNewLabelFondoResultado = new JLabel(imagenFondoResultados);
		lblNewLabelFondoResultado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabelFondoResultado.setBounds(0, 0, 968, 644);
		panelResultados.add(lblNewLabelFondoResultado);

		JLabel avisosResultado = new JLabel("New label");
		avisosResultado.setBounds(234, 27, 513, 57);
		panelResultados.add(avisosResultado);
		panelResultados.setVisible(false);

		// PANEL INFO
		panelInfo = new JPanel();
		panelInfo.setLayout(null);
		panelInfo.setBounds(0, 0, 966, 644);
		contentPane.add(panelInfo);
		panelInfo.setVisible(false);

		chckbxNewCheckBoxMusica = new JCheckBox("Música");
		chckbxNewCheckBoxMusica.setSelected(true);
		chckbxNewCheckBoxMusica.setBounds(0, 0, 66, 21);
		panelInfo.add(chckbxNewCheckBoxMusica);

		lblNewLabelFechaInfo = new JLabel("");
		lblNewLabelFechaInfo.setForeground(new Color(255, 255, 255));
		lblNewLabelFechaInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelFechaInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabelFechaInfo.setBounds(10, 68, 111, 21);
		panelInfo.add(lblNewLabelFechaInfo);

		lblNewLabelHoraInfo = new JLabel("");
		lblNewLabelHoraInfo.setForeground(new Color(255, 255, 255));
		lblNewLabelHoraInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelHoraInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabelHoraInfo.setBounds(10, 27, 111, 21);
		panelInfo.add(lblNewLabelHoraInfo);

		lblNewLabel_7 = new JLabel("BUSCA MINAS");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_7.setBounds(304, 68, 339, 21);
		panelInfo.add(lblNewLabel_7);

		lblNewLabel_3 = new JLabel("Fecha de lanzamiento 01/11/2023");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(324, 266, 305, 28);
		panelInfo.add(lblNewLabel_3);
		lblNewLabel_4 = new JLabel("Autor: Juan Gabriel Lara Gómez");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(304, 434, 326, 28);
		panelInfo.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Versión 1.0 Beta");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(400, 352, 157, 28);
		panelInfo.add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("Trabajo Grado Superior Aplicaciones Multiplataforma");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(234, 172, 499, 28);
		panelInfo.add(lblNewLabel_6);

		btnNewButtonVolverInfo = new JButton("Volver");
		btnNewButtonVolverInfo.setBounds(863, 613, 93, 21);
		panelInfo.add(btnNewButtonVolverInfo);
		lblNewLabelFondoInformacion = new JLabel(imagenFondoIinfo);
		lblNewLabelFondoInformacion.setText("");
		lblNewLabelFondoInformacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabelFondoInformacion.setBounds(0, 0, 966, 657);
		panelInfo.add(lblNewLabelFondoInformacion);

		panelRegistro = new JPanel();
		panelRegistro.setBounds(0, 0, 966, 644);
		contentPane.add(panelRegistro);
		panelRegistro.setLayout(null);

		lblNewLabelHora = new JLabel("");
		lblNewLabelHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelHora.setBackground(new Color(0, 0, 0));
		lblNewLabelHora.setForeground(new Color(255, 255, 255));
		lblNewLabelHora.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabelHora.setBounds(7, 81, 98, 19);
		panelRegistro.add(lblNewLabelHora);

		lblNewLabelFecha = new JLabel("");
		lblNewLabelFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelFecha.setBackground(new Color(0, 0, 0));
		lblNewLabelFecha.setForeground(new Color(255, 255, 255));
		lblNewLabelFecha.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabelFecha.setBounds(10, 40, 107, 19);
		panelRegistro.add(lblNewLabelFecha);

		textFieldMinas = new JTextField();
		textFieldMinas.setBounds(301, 280, 29, 19);
		textFieldMinas.setColumns(10);
		panelRegistro.add(textFieldMinas);

		textFieldFilas = new JTextField();
		textFieldFilas.setBounds(301, 251, 29, 19);
		textFieldFilas.setColumns(10);
		panelRegistro.add(textFieldFilas);

		textFieldColumnas = new JTextField();
		textFieldColumnas.setBounds(301, 227, 29, 19);
		textFieldColumnas.setColumns(10);
		panelRegistro.add(textFieldColumnas);

		lblNewLabel_13 = new JLabel("Minas:");
		lblNewLabel_13.setForeground(new Color(255, 255, 255));
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_13.setBounds(184, 270, 71, 31);
		panelRegistro.add(lblNewLabel_13);

		lblNewLabel_12 = new JLabel("Columnas:");
		lblNewLabel_12.setForeground(new Color(255, 255, 255));
		lblNewLabel_12.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_12.setBounds(184, 222, 107, 20);
		panelRegistro.add(lblNewLabel_12);

		JLabel lblNewLabel_9 = new JLabel("Filas:");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_9.setBounds(184, 241, 64, 31);
		panelRegistro.add(lblNewLabel_9);

		btnNewButtonInformacion = new JButton("Información");
		btnNewButtonInformacion.setForeground(new Color(0, 0, 255));
		btnNewButtonInformacion.setBounds(849, 613, 107, 21);
		panelRegistro.add(btnNewButtonInformacion);

		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(184, 194, 104, 31);
		panelRegistro.add(lblNewLabel);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(301, 200, 96, 21);
		panelRegistro.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		btnNewButtonComenzarPartida = new JButton("Jugar");
		btnNewButtonComenzarPartida.setForeground(new Color(0, 0, 255));
		btnNewButtonComenzarPartida.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButtonComenzarPartida.setBounds(10, 611, 107, 21);
		panelRegistro.add(btnNewButtonComenzarPartida);

		lblNewLabel_2 = new JLabel("Registro Jugador");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2.setBounds(341, 22, 310, 37);
		panelRegistro.add(lblNewLabel_2);

		lblNewLabelAvisos = new JLabel("");
		lblNewLabelAvisos.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabelAvisos.setForeground(new Color(255, 0, 0));
		lblNewLabelAvisos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabelAvisos.setBounds(193, 0, 627, 27);
		panelRegistro.add(lblNewLabelAvisos);

		chckbxMsicaMusicaRegistro = new JCheckBox("Música");
		chckbxMsicaMusicaRegistro.setSelected(true);
		chckbxMsicaMusicaRegistro.setBounds(-2, 0, 93, 21);
		panelRegistro.add(chckbxMsicaMusicaRegistro);

		// FONDO INFO
		fondoInicio = new JLabel(imagenFondoRegistro);
		fondoInicio.setText("");
		fondoInicio.setBounds(-2, 0, 966, 644);
		panelRegistro.add(fondoInicio);

		lblNewLabelMensajeResultado = new JLabel("");
		lblNewLabelMensajeResultado.setBounds(150, 22, 659, 24);
		panelRegistro.add(lblNewLabelMensajeResultado);
		lblNewLabelMensajeResultado.setForeground(new Color(255, 0, 0));
		lblNewLabelMensajeResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelMensajeResultado.setFont(new Font("Tahoma", Font.PLAIN, 20));


	}

	public JTextField getTextFieldColumnas() {
		return textFieldColumnas;
	}

	public JTextField getTextFieldFilas() {
		return textFieldFilas;
	}

	public JTextField getTextFieldMinas() {
		return textFieldMinas;
	}

	public DefaultListModel<String> getListaNombres() {
		return listaNombres;
	}

	public JLabel getLblNewLabelResultadoTiempoTrancurrido() {
		return lblNewLabelResultadoTiempoTrancurrido;
	}

	public JLabel getLblNewLabelResultadosPuntuacion() {
		return lblNewLabelResultadosPuntuacion;
	}

	public JButton getBtnNewButtonResultadosVolverInicio() {
		return btnNewButtonResultadosVolverInicio;
	}

	public JList<String> getListRsultadosLista() {
		return listRsultadosLista;
	}

	public JButton getBtnNewButtonMostrarMarcadoresResultados() {
		return btnNewButtonMostrarMarcadoresResultados;
	}

	public JLabel getLblNewLabelResultadosJugador() {
		return lblNewLabelResultadosJugador;
	}

	public JCheckBox getChckbxNewCheckBoxMusicaResultados() {
		return chckbxNewCheckBoxMusicaResultados;
	}

	public JLabel getLblNewLabelMensajeResultado() {
		return lblNewLabelMensajeResultado;
	}

	public JPanel getPanelResultados() {
		return panelResultados;
	}

	public JLabel getLblTiempoTranscurrido() {
		return lblTiempoTranscurrido;
	}

	public JCheckBox getChckbxMsicaMusicaRegistro() {
		return chckbxMsicaMusicaRegistro;
	}

	public JCheckBox getChckbxNewCheckBoxMusicaTablero() {
		return chckbxNewCheckBoxMusicaTablero;
	}

	public JButton getBtnNewButtonAbandonarPartida() {
		return btnNewButtonAbandonarPartida;
	}

	public JLabel getLblNewLabelAvisosPartida() {
		return lblNewLabelAvisosPartida;
	}

	public JPanel getPanelTablero() {
		return panelTablero;
	}

	public JCheckBox getChckbxNewCheckBoxMusica() {
		return chckbxNewCheckBoxMusica;
	}

	public JPanel getPanelRegistro() {
		return panelRegistro;
	}

	public JPanel getPanelInfo() {
		return panelInfo;
	}

	public JButton getBtnNewButtonInformacion() {
		return btnNewButtonInformacion;
	}

	public JButton getBtnNewButtonVolverInfo() {
		return btnNewButtonVolverInfo;
	}

	public JLabel getLblNewLabelHoraInfo() {
		return lblNewLabelHoraInfo;
	}

	public JLabel getLblNewLabelFechaInfo() {
		return lblNewLabelFechaInfo;
	}

	public JLabel getLblNewLabelAvisos() {
		return lblNewLabelAvisos;
	}

	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}

	public JLabel getLblNewLabelFecha() {
		return lblNewLabelFecha;
	}
	
	public JLabel getAvisoResultados() {
		return avisoResultados;
	}

	public JLabel getLblNewLabelHora() {
		return lblNewLabelHora;
	}

	public JButton getBtnNewButtonComenzarPartida() {
		return btnNewButtonComenzarPartida;
	}

	public JButton[][] getCeldas() {
		return celdas;
	}

	public void setCeldas(JButton[][] celdas) {
		this.celdas = celdas;
	}

	public JLabel getLblNewLabelPuntuacionTablero() {
		return lblNewLabelPuntuacionTablero;
	}

	// CARGA DE BOTONES
	public void cargarCeldas() {
		int posY = 100; // px
		int posX = 225;
		int ancho = 40;
		int alto = 40;
		int x = 0;
		int y = 0;
		for (int i = 0; i < celdas.length; i++) {
			for (int j = 0; j < celdas[i].length; j++) {
				celdas[i][j] = new JButton();
				celdas[i][j].setName(i + "," + j);
				// Establece las coordenadas de los botones
				x = posX;
				y = posY;

				for (int col = 0; col < j; col++) {
					x += ancho + 1;
				}

				for (int fila = 0; fila < i; fila++) {
					y += alto + 1;
				}

				celdas[i][j].setBounds(x, y, ancho, alto);
				panelTablero.add(celdas[i][j]);

			}
		}
	}

	public void iniciarTablero() {
		celdas = new JButton[Controlador.filas][Controlador.columnas];

		ImageIcon imagenFondoTablero = new ImageIcon(getClass().getResource("/imagenes/img_tablero.png"));
		panelTablero = new JPanel();
		panelTablero.setBounds(0, 0, 966, 644);
		panelTablero.setLayout(null);
		contentPane.add(panelTablero);

		cargarCeldas();

		btnNewButtonAbandonarPartida = new JButton("Abandonar Partida");
		btnNewButtonAbandonarPartida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButtonAbandonarPartida.setBounds(793, 613, 163, 21);
		panelTablero.add(btnNewButtonAbandonarPartida);

		chckbxNewCheckBoxMusicaTablero = new JCheckBox("Música");
		chckbxNewCheckBoxMusicaTablero.setSelected(true);
		chckbxNewCheckBoxMusicaTablero.setBounds(0, 6, 75, 21);
		panelTablero.add(chckbxNewCheckBoxMusicaTablero);

		lblNewLabelAvisosPartida = new JLabel("");
		lblNewLabelAvisosPartida.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelAvisosPartida.setForeground(new Color(255, 0, 0));
		lblNewLabelAvisosPartida.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelAvisosPartida.setBounds(151, 10, 539, 41);
		panelTablero.add(lblNewLabelAvisosPartida);

		lblNewLabelPuntuacionTablero = new JLabel("");
		lblNewLabelPuntuacionTablero.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabelPuntuacionTablero.setBounds(799, 65, 134, 20);
		panelTablero.add(lblNewLabelPuntuacionTablero);

		lblTiempoTranscurrido = new JLabel("Tiempo transcurrido de partida: ");
		lblTiempoTranscurrido.setForeground(new Color(255, 255, 255));
		lblTiempoTranscurrido.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTiempoTranscurrido.setBounds(549, 2, 215, 21);
		panelTablero.add(lblTiempoTranscurrido);

		JLabel lblNewLabel = new JLabel("Puntuación:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(721, 65, 80, 20);
		panelTablero.add(lblNewLabel);
		JLabel lblNewLabelFondoTablero = new JLabel(imagenFondoTablero);
		lblNewLabelFondoTablero.setBounds(0, 0, 966, 644);
		panelTablero.add(lblNewLabelFondoTablero);
	}

	public void desabilitarTablero() {
		for (int i = 0; i < celdas.length; i++) {
			for (int j = 0; j < celdas.length; j++) {
				celdas[i][j].setEnabled(false);
			}
		}

	}

	public void refrescarBtn() {
		for (int i = 0; i < celdas.length; i++) {
			for (int j = 0; j < celdas.length; j++) {
				panelTablero.add(celdas[i][j]);
			}
		}
	}

	public void actualizarBoton(int fila, int columna, JButton boton) {
		celdas[fila][columna].setText(boton.getText());
		celdas[fila][columna].setEnabled(false);
		refrescarBtn();
	}
}
