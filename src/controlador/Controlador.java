package controlador;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;

import modelo.Casilla;
import modelo.Cronometro;
import modelo.Jugador;
import modelo.Musica;
import modelo.Reloj;
import vista.Vista;

public class Controlador implements ActionListener, MouseListener {

	private boolean partidaGanada;
	private float puntuacion;
	public static int numCasillasAbiertas;
	public static int filas;
	public static int columnas;
	public static int minas;
	private Casilla[][] casillas;
	private LocalDate fecha;
	private Vista vista;
	private Reloj reloj;
	private Timer timer;
	private Cronometro cronometro;
	private Musica musicaInfo;
	private Musica musicaRegistro;
	private Musica musicaPartida;
	private Musica musicaPartidaPerdida;
	private Musica musicaPartidaGanada;
	private Jugador jugador;
	private ArrayList<Jugador> jugadores;

	public Controlador(Vista vista) {

		this.vista = vista;

		// INICIACION DE OBJETOS
		puntuacion = 0.0f;
		
		jugadores = new ArrayList<Jugador>();
		fecha = LocalDate.now();
		reloj = new Reloj();
		iniciarReloj();
		musicaRegistro = activarMusica(new Musica("registro.wav"), vista.getChckbxNewCheckBoxMusica().isSelected());
		iniciarEscuchadores();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//BOTON JUGAR(comenzar partida)
		if (e.getSource() == vista.getBtnNewButtonComenzarPartida()) {
			jugador = new Jugador();
			numCasillasAbiertas = 0;
			

			if (validarRegistro()) {
				datosTablero();
				juego();
				paraMusica(musicaRegistro);
				musicaPartida = activarMusica(new Musica("partida.wav"), vista.getChckbxNewCheckBoxMusica().isSelected());
				
				// RECOGER DATOS DEL JUGADOR (en este caso solo coge el nombre)	
				controlCheckBox(vista.getChckbxMsicaMusicaRegistro().isSelected(),vista.getChckbxNewCheckBoxMusicaTablero());
				ocultarPaneles(vista.getPanelRegistro(),vista.getPanelTablero());
				jugador.setNombre(vista.getTextFieldNombre().getText());
			}

		//BOTÖN IR AL PANEL INFORMACIÓN
		} else if (e.getSource() == vista.getBtnNewButtonInformacion()) {

			ocultarPaneles(vista.getPanelRegistro(),vista.getPanelInfo());
			paraMusica(musicaRegistro);
			controlCheckBox(vista.getChckbxMsicaMusicaRegistro().isSelected(),vista.getChckbxNewCheckBoxMusica());
			musicaInfo = activarMusica(new Musica("musica.wav"), vista.getChckbxMsicaMusicaRegistro().isSelected());
			
		//BOTON VOLVER DEL PANEL INFORMACIÓN	
		} else if (e.getSource() == vista.getBtnNewButtonVolverInfo()) {

			ocultarPaneles(vista.getPanelInfo(),vista.getPanelRegistro());
			paraMusica(musicaInfo);
			controlCheckBox(vista.getChckbxNewCheckBoxMusica().isSelected(),vista.getChckbxMsicaMusicaRegistro());
			musicaRegistro = activarMusica(new Musica("registro.wav"), vista.getChckbxNewCheckBoxMusica().isSelected());
		
		//CHECKBOX PANEL INFORMACIÓN
		} else if (e.getSource() == vista.getChckbxNewCheckBoxMusica()) {

			if(vista.getChckbxNewCheckBoxMusica().isSelected() && musicaInfo != null)
				musicaInfo = activarMusica(new Musica("musica.wav"),true);
			else
				pausarMusica(musicaInfo);
			
		//CHECKBOX MUSICA PANEL REGISTRO (INICIAL)
		} else if (e.getSource() == vista.getChckbxMsicaMusicaRegistro()) {
			
			if(vista.getChckbxMsicaMusicaRegistro().isSelected())
				musicaRegistro = activarMusica(new Musica("registro.wav"),true);
			else
				pausarMusica(musicaRegistro);
			
			
		//CHECKBOX MUSICA PANEL RESULTADOS
		}else if (e.getSource() == vista.getChckbxNewCheckBoxMusicaResultados()) {

			if(vista.getChckbxNewCheckBoxMusicaResultados().isSelected()) {
					reproducirMusicaActual(musicaPartidaGanada);
					reproducirMusicaActual(musicaPartidaPerdida);
					reproducirMusicaActual(musicaPartida);		
				} else {
					pausarMusica(musicaPartidaGanada);
					pausarMusica(musicaPartidaPerdida);
					pausarMusica(musicaPartida);
				}
			
 
		//CHECKBOX MÚSICA TABLERO
		}else if (e.getSource() == vista.getChckbxNewCheckBoxMusicaTablero()) {

			if(vista.getChckbxNewCheckBoxMusicaTablero().isSelected()) {
				reproducirMusicaActual(musicaPartidaGanada);
				reproducirMusicaActual(musicaPartidaPerdida);
				reproducirMusicaActual(musicaPartida);	
			} else {
				pausarMusica(musicaPartidaGanada);
				pausarMusica(musicaPartidaPerdida);
				pausarMusica(musicaPartida);
			}
		
		} else if (e.getSource() == vista.getBtnNewButtonAbandonarPartida()) {

			controlCheckBox(vista.getChckbxNewCheckBoxMusicaTablero().isSelected(),vista.getChckbxNewCheckBoxMusicaResultados());
			ocultarPaneles(vista.getPanelTablero(),vista.getPanelResultados());

			
			partidaGanada = partidaGanada();
			String mensaje = vista.getLblNewLabelAvisosPartida().getText();

			
			if (partidaGanada) {
				vista.getAvisoResultados().setForeground(Color.green);
				vista.getAvisoResultados().setText(mensaje);
			} else if (!partidaGanada) {
				
				vista.getAvisoResultados().setForeground(Color.red);
				vista.getAvisoResultados().setText(mensaje);
			} else {
				vista.getAvisoResultados().setForeground(Color.red);
				vista.getAvisoResultados().setText("GRACIAS POR JUGA"+jugador.getNombre()+", VUELVE A INTENTARLO CUANDO QUIERAS¡¡");
			}

			calcularPuntuacion();
			mostrarDatosPartida();

			

		//BOTONO VOLVER A PANEL INICIAL DESDE RESULTADOS
		} else if (e.getSource() == vista.getBtnNewButtonResultadosVolverInicio()) {

			controlCheckBox(vista.getChckbxNewCheckBoxMusicaResultados().isSelected(),vista.getChckbxMsicaMusicaRegistro());
			
			restablecerVistaListaResultados();
			
			
			paraMusica(musicaPartidaGanada);
			paraMusica(musicaPartidaPerdida);
			paraMusica(musicaPartida);
			
			
			ocultarPaneles(vista.getPanelResultados(),vista.getPanelRegistro());
			
			musicaRegistro = activarMusica(new Musica("registro.wav"), vista.getChckbxNewCheckBoxMusicaTablero().isSelected());
			
			// SE GUARDA EL JUGADOR EN UN ARRAY JUGADORES
			
			
		//BOTON MOSTRAR RESULTADOS DEL PANEL RESULTADOS
		} else if (e.getSource() == vista.getBtnNewButtonMostrarMarcadoresResultados()) {
			mostrarResultadosEnJList();
		}

	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {

		if (e.getSource() instanceof JButton) {
			JButton botonPulsado = (JButton) e.getSource();
			extraerPosicionText(botonPulsado);
			
		} else if (e.getClickCount() == 2) {

			String nombre = vista.getListRsultadosLista().getSelectedValue().toString();
			recogerDatosJugador(nombre);

		}

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	//MODULACIÓN DEL PROGRAMA:-----------------------------------------------------------------------------------------------------------------------------------------------------
	
	private void restablecerVistaListaResultados() {
		vista.getBtnNewButtonMostrarMarcadoresResultados().setEnabled(true);
		vista.getListRsultadosLista().setVisible(false);
	}
	
	
	private void reproducirMusicaActual(Musica musica) {
		if(musica != null)
			musica.reproducir();
	}
	
	private void pausarMusica(Musica musica) {
		if(musica != null)
			musica.detener();

	}
	private void paraMusica(Musica musica) {
		if (musica != null) {
	        musica.parar();
	    }
	}
	
	//EXTRAE LA POSICON DEL BOTON EN STRING Y LA CONVIERTE A ENTERO (se lo pasa al método 'celdaSeleccionada')
	private void extraerPosicionText(JButton btn) {
		
		String[] posicion = btn.getName().split(",");
		int pF = Integer.parseInt(posicion[0]);
		int pC = Integer.parseInt(posicion[1]);
		btn.setEnabled(false);
		btn.setText("" + casillas[pF][pC].getNumMinasAlrededor());
		celdaSeleccionada(pF, pC);// llamada para validacion de posicion

	}
	
	//MUESTRA LA LISTA DE JUGADORES Y SUS PUNTUACIONES
	private void mostrarResultadosEnJList() {
		vista.getListaNombres().removeAllElements();
		// MUESTRA EL JLIST
		vista.getListRsultadosLista().setVisible(true);
		// DESACTIVA EL BOTON VER LISTADO
		vista.getBtnNewButtonMostrarMarcadoresResultados().setEnabled(false);
		// CARGA LOS NOMBRES EN EL JLIS
		for (int i = 0; i < jugadores.size(); i++) {
			vista.getListaNombres().insertElementAt(jugadores.get(i).getNombre(), i);
			System.out.println(jugadores.get(i).getNombre());
		}

	}
	
	//GESTIONA LOS PANELES
	private void ocultarPaneles(JPanel panel1, JPanel panel2) {
		panel1.setVisible(false);
		panel2.setVisible(true);
	}
	
	// CARGA LOS ESCUCHADORES RESPECTO A LOS BOTONES DEL TABLERO
	public void iniciarEscuchadoresTablero() {
		// carga de escuchadores para celdas vista
		JButton[][] casillas = vista.getCeldas();
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				casillas[i][j].addMouseListener(this);
			}
		}
		vista.setCeldas(casillas);
		vista.getChckbxNewCheckBoxMusicaTablero().addActionListener(this);
		vista.getBtnNewButtonAbandonarPartida().addActionListener(this);
		cronometro = new Cronometro(vista.getLblTiempoTranscurrido());
	}

	
	// DANDO VALORES A LAS VARIABLES ESTATICAS QUE CREARAN EL TABLERO
	private void datosTablero() {
		int min = Integer.parseInt(vista.getTextFieldMinas().getText());
		int colum = Integer.parseInt(vista.getTextFieldColumnas().getText());
		int fil = Integer.parseInt(vista.getTextFieldFilas().getText());

		Controlador.minas = min;
		Controlador.columnas = colum;
		Controlador.filas = fil;


	}
	
	// RECOGE LOS DATOS DEL JUGADOR SELECCIONADO EN LA LISTA Y MUESTRA SUS PUNTUACIONES
	private void recogerDatosJugador(String nombre) {
		
		int pos = 0;

		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i).getNombre().equals(nombre))
				pos = i;
		}
		vista.getLblNewLabelResultadosJugador().setText(jugadores.get(pos).getNombre());
		vista.getLblNewLabelResultadoTiempoTrancurrido().setText(jugadores.get(pos).getTiempoTranscurrido());
		vista.getLblNewLabelResultadosPuntuacion().setText("" + jugadores.get(pos).getPuntuacion());
		
	}

	//VALIDA LOS CAMPOS DEL REGISTRO TRUE SI SON CORRECTOS Y FALSE SI NO
	private boolean validarRegistro() {
		if (vista.getTextFieldNombre().getText().equals("")) {
			vista.getLblNewLabelAvisos().setText("Error, debe rellenar el campo 'Nombre'");
		} else if (vista.getTextFieldColumnas().getText().equals("")
				|| !vista.getTextFieldColumnas().getText().matches("^[5-9]|10$")) {
			vista.getLblNewLabelAvisos()
					.setText("Error, debe rellenar el campo 'COLUMNAS' y debe ser numérico(de 5 a 10)");
		} else if (vista.getTextFieldFilas().getText().equals("")
				|| !vista.getTextFieldFilas().getText().matches("^[5-9]|10$")) {
			vista.getLblNewLabelAvisos()
					.setText("Error, debe rellenar el campo 'FILAS' y debe ser numérico(de 5 a 10)");
		} else if (vista.getTextFieldMinas().getText().equals("")
				|| !vista.getTextFieldMinas().getText().matches("^[5-9]|10$")) {
			vista.getLblNewLabelAvisos()
					.setText("Error, debe rellenar el campo 'MINAS' y debe ser numérico(de 5 a 10)");
		} else {
			return true;
		}
			return false;
	}

	//CALCULA LA PUNTUACION Y LOS DATOS DE LA PARTIDA
	private void calcularPuntuacion() {
		
		if (!vista.getLblNewLabelPuntuacionTablero().getText().equals("")) {

			String puntuacionConComa = vista.getLblNewLabelPuntuacionTablero().getText();
			String puntuacionConPunto = puntuacionConComa.replace(",", ".");
			String tiempoTranscurrido = vista.getLblTiempoTranscurrido().getText();

			jugador.setTiempoTranscurrido(tiempoTranscurrido);
			jugador.setPuntuacion(Float.parseFloat(puntuacionConPunto));
			
			jugador.setNombre(vista.getTextFieldNombre().getText());
			jugadores.add(jugador);
			limpiarRegistros();
		} else {
			jugador.setPuntuacion(0);
			jugadores.add(jugador);
			limpiarRegistros();
		}
			
	}
	

	
	private Musica activarMusica(Musica musica,boolean checkActivado) {
		if(checkActivado) {
			musica.reproducir();
		}
		return musica;
	}
	
	// LIMPIAR REGISTROS
	private void limpiarRegistros() {
		vista.getLblNewLabelAvisos().setText("");
		vista.getTextFieldNombre().setText("");
		vista.getTextFieldMinas().setText("");
		vista.getTextFieldColumnas().setText("");
		vista.getTextFieldFilas().setText("");

	}

	// CONTROL DE MÚSICA
	private void controlCheckBox(boolean primerCheckbox,JCheckBox nuevo) {
		if (primerCheckbox) {
			nuevo.setSelected(true);
		} else {
			nuevo.setSelected(false);
		}

	}
	// INICIA EL CRONOMETRO
	public void iniciarReloj() {
		// Controla que se actualice la hora gracias a un hilo de la clase Timer
		reloj.actualizarHoraActual();
		// Programa una tarea para actualizar la hora cada segundo
		timer = new Timer(true);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {

				reloj.actualizarHoraActual();
				reloj.mostrarHoraActual();

				vista.getLblNewLabelHora().setText(reloj.mostrarHoraActual());// muestra la hora en un JLabel
				vista.getLblNewLabelFechaInfo().setText(reloj.mostrarHoraActual());
				vista.getLblNewLabelFecha().setText("" + fecha);
				vista.getLblNewLabelHoraInfo().setText("" + fecha);

			}
		}, 0, 1000); // Inicialmente, y luego cada 1000 ms (1 segundo)
	}

	// INICIA LAS CASILLAS DE LA CLASE CASILLA (funcionamiento interno del jeugo)
	public void iniciarCasillas() {
		casillas = new Casilla[filas][filas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				casillas[i][j] = new Casilla(i, j); // Crea una Casilla en la posición (i, j)
			}
		}
	}

	// GENERA LAS MINAS ALEATORIAMENTE
	public void generacionMinas() {

		int posTmplFila;
		int posTmpColumna;

		for (int i = 0; i < minas; i++) {
			do {
				// Elige una posición aleatoria para la mina
				posTmplFila = (int) (Math.random() * filas);
				posTmpColumna = (int) (Math.random() * columnas);
			} while (casillas[posTmplFila][posTmpColumna].isMina());
			this.casillas[posTmplFila][posTmpColumna].setMina(true);
		}

	}

	public void actualizarNumeroMinasAlrededor() {

		List<Casilla> casillasAlrededor;
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				if (casillas[i][j].isMina()) {
					// Obtiene las casillas alrededor
					casillasAlrededor = obtenerCasillasAlrededor(i, j);

					// incrementa el en 1minaAlrededor cada casilla arededor
					for (Casilla casilla : casillasAlrededor) {
						casilla.incrementarNumeroMinasAlrededor();
					}
				}
			}
		}
	}

	// PARA HACER PRUEBAS CON EL JUEGO

	/*
	 * // IMPRIME EL TABLERO POR CONSOLA public void imprimirTablero() { for (int i
	 * = 0; i < filas; i++) { for (int j = 0; j < columnas; j++) {
	 * System.out.print(this.casillas[i][j].isMina() ? "*" : "0"); }
	 * System.out.println(); } System.out.println("-----"); }
	 */

	//MËTODO RECURSIVO QUE VALIDA SI ES UNA MINA Y SI NO HAY CASILLAS QUE TENGAN MINAS ALRDERDOR (se desactivan todos los botones hasta llegar a uno que tiene minas alrededor)
	public void celdaSeleccionada(int pF, int pC) {

		DecimalFormat df = new DecimalFormat("#.00");
		JButton btn = new JButton();

		// EN CASO DE PERDER BUSCO LAS CASILLAS CON MINAS Y LAS MUESTRO dando valor a

		if (casillas[pF][pC].isMina()) {

			for (int i = 0; i < this.casillas.length; i++) {
				for (int j = 0; j < this.casillas[i].length; j++) {
					JButton[][] celdas = vista.getCeldas();
					btn = celdas[i][j];

					if (casillas[i][j].isMina()) {
					    ImageIcon minaIcon = new ImageIcon(getClass().getResource("/imagenes/mina.png"));
					    Image image = minaIcon.getImage();

					    // Sin escalado (comentar la línea de escalado)
					    // Image newImage = image;

					    // Algoritmo de escalado alternativo (puedes probar otros algoritmos)
					    Image newImage = image.getScaledInstance(20, 20, Image.SCALE_DEFAULT);

					    // Crear un ImageIcon con la imagen ajustada
					    ImageIcon minaIconCentrado = new ImageIcon(newImage);

					    // Establecer la imagen ajustada y centrada como ícono del botón
					    btn.setIcon(minaIconCentrado);
					    btn.setHorizontalTextPosition(SwingConstants.CENTER);
					    btn.setVerticalTextPosition(SwingConstants.CENTER);
					
					} else {
						if (casillas[i][j].getNumMinasAlrededor() > 0) {
							// Si hay al menos una mina alrededor, establecer el número en color verde
							btn.setText(Integer.toString(casillas[i][j].getNumMinasAlrededor()));

							// Cambiar el color del texto a verde
							btn.setForeground(Color.GREEN);
							vista.actualizarBoton(i, j, btn);
						}

						vista.actualizarBoton(i, j, btn); // Pasar el botón a la vista
					}
				}

			}

			vista.desabilitarTablero();
			cronometro.detener();
			vista.getLblNewLabelAvisosPartida().setText("HAS TOCADO UNA MINA "+jugador.getNombre()+"¡¡ Perdiste¡¡");
			vista.getBtnNewButtonAbandonarPartida().setText("CONTINUAR");
			if (vista.getChckbxNewCheckBoxMusicaTablero().isSelected()) {
				musicaPartida.parar();
				musicaPartidaPerdida = activarMusica(new Musica("partida_perdida.wav"), vista.getChckbxNewCheckBoxMusicaTablero().isSelected());

			}else {
				musicaPartida.parar();
				musicaPartidaPerdida = activarMusica(new Musica("partida_perdida.wav"), false);
			}

		} else if (!casillas[pF][pC].isAbierta()) { // Comprobar si la casilla no está abierta

			numCasillasAbiertas++;
			// Marcar la casilla como abierta
			casillas[pF][pC].setAbierta(true);
			int numMinasAlrededor = casillas[pF][pC].getNumMinasAlrededor();
			if (numMinasAlrededor == 0) {

				List<Casilla> casillasAlrededor = obtenerCasillasAlrededor(pF, pC);
				for (Casilla casilla : casillasAlrededor) {
					int minas = casilla.getNumMinasAlrededor();
					btn.setText(minas > 0 ? Integer.toString(minas) : "");

					if (!partidaGanada()) {

						celdaSeleccionada(casilla.getPosFila(), casilla.getPosColumna());

						// puntuacion
						puntuacion += puntuacionPartida();
						vista.getLblNewLabelPuntuacionTablero().setText(df.format(puntuacion));

						// Pasar el botón a la vista
						vista.actualizarBoton(casilla.getPosFila(), casilla.getPosColumna(), btn);
					} else {
						cronometro.detener();
						// Cambiar el texto del JLabel
						vista.getLblNewLabelAvisosPartida().setForeground(Color.GREEN);
						vista.getLblNewLabelAvisosPartida().setText("HAS GANADO LA PARTIDA "+jugador.getNombre()+"¡¡ ENHORABUENA¡¡");
						vista.getBtnNewButtonAbandonarPartida().setText("CONTINUAR");
						if (vista.getChckbxNewCheckBoxMusicaTablero().isSelected()) {
							musicaPartida.parar();
							musicaPartidaGanada = activarMusica(new Musica("partida_ganada.wav"), vista.getChckbxNewCheckBoxMusicaTablero().isSelected());

						}else {
							musicaPartida.parar();
							musicaPartidaGanada = activarMusica(new Musica("partida_ganada.wav"), false);
						}
					}
				}
			} else {

				if (!partidaGanada()) {

					btn.setText(Integer.toString(numMinasAlrededor));
					vista.actualizarBoton(pF, pC, btn); // Pasar el botón a la vista
					puntuacion += puntuacionPartida();
					vista.getLblNewLabelPuntuacionTablero().setText(df.format(puntuacion));
				} else {
					cronometro.detener();
					// Cambiar el texto del JLabel:
					vista.getLblNewLabelAvisosPartida().setForeground(Color.GREEN);
					vista.getLblNewLabelAvisosPartida().setText("HAS GANADO  LA PARTIDA "+jugador.getNombre()+"¡¡ ENHORABUENA¡¡");
					vista.getBtnNewButtonAbandonarPartida().setText("CONTINUAR");
					if (vista.getChckbxNewCheckBoxMusicaTablero().isSelected()) {
						musicaPartida.parar();
						musicaPartidaGanada = activarMusica(new Musica("partida_ganada.wav"), vista.getChckbxNewCheckBoxMusicaTablero().isSelected());

					} else {
						musicaPartida.parar();
						musicaPartidaGanada = activarMusica(new Musica("partida_ganada.wav"), false);
					}
				}
			}

			// Deshabilitar el botón correspondiente
			vista.actualizarBoton(pF, pC, btn);
		}
	}

	// COMPRUEBA SI SE HAN ABIERTO TODAS LAS CASILLAS
	public boolean partidaGanada() {
		return numCasillasAbiertas >= (filas * columnas) - minas;
	}

	// Obtiene las casillas adyacentes a una posición dada
	private List<Casilla> obtenerCasillasAlrededor(int posFila, int posColumna) {
		List<Casilla> listaCasillas = new ArrayList<>();

		int[] x = { -1, -1, -1, 0, 1, 1, 1, 0 };// Coordenadas para revisar las casillas alrededor
		int[] y = { -1, 0, 1, 1, 1, 0, -1, -1 };

		int f, c; // tmp filas y columnas

		for (int i = 0; i < 8; i++) {
			f = posFila + x[i];
			c = posColumna + y[i];

			if (f >= 0 && f < casillas.length && c >= 0 && c < casillas[0].length) {
				listaCasillas.add(casillas[f][c]);
			}
		}

		return listaCasillas;
	}

	public float puntuacionPartida() {
		return 2.24f;
	}

	public void juego() {
		vista.iniciarTablero();
		iniciarCasillas();
		generacionMinas();
		actualizarNumeroMinasAlrededor();
		// imprimirTablero();
		// imprimirMinasAlrededor();
		iniciarEscuchadoresTablero();
		cronometro.iniciar();

	}
	/*
	 * public void imprimirMinasAlrededor() { for (int i = 0; i < filas; i++) { for
	 * (int j = 0; j < columnas; j++) {
	 * System.out.print(this.casillas[i][j].getNumMinasAlrededor()); }
	 * System.out.println(); } 
	 * }
	 */
	private void mostrarDatosPartida() {

		// MUESTRA EL JUGADOR ACTUAL Y SUS MARCAS EN PUNTOS Y TIEMPO
		vista.getLblNewLabelResultadosJugador().setText(jugador.getNombre());
		vista.getLblNewLabelResultadosPuntuacion().setText("" + jugador.getPuntuacion());
		vista.getLblNewLabelResultadoTiempoTrancurrido().setText(jugador.getTiempoTranscurrido());


	}
	// INICIACION DE ESCUCHADORES
	private void iniciarEscuchadores() {
				vista.getBtnNewButtonComenzarPartida().addActionListener(this);
				vista.getBtnNewButtonInformacion().addActionListener(this);
				vista.getBtnNewButtonVolverInfo().addActionListener(this);
				vista.getChckbxNewCheckBoxMusica().addActionListener(this);
				vista.getChckbxMsicaMusicaRegistro().addActionListener(this);
				vista.getChckbxNewCheckBoxMusicaResultados().addActionListener(this);
				vista.getBtnNewButtonResultadosVolverInicio().addActionListener(this);
				vista.getBtnNewButtonMostrarMarcadoresResultados().addActionListener(this);
				vista.getListRsultadosLista().addMouseListener(this);


	}
	
}

