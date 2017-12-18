package application;

import java.io.File;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Controller {
	 	@FXML
	    private Button btStart;
	 	@FXML
	    private Canvas canvas;
	 	@FXML
	    private AnchorPane aPane;
	 	
	 	
	    // Reference to the main application.
	    private Main mainApp;

	    public Controller() {
	    }

	    @FXML
	    private void initialize() {
	    }

	    public void setMainApp(Main mainApp) {
	        this.mainApp = mainApp;
	    }
	    
	    @FXML
	    private void startButtonAction(){
	    	
	    	int colNumber = 4;
	    	int rowNumber = 4;
	    	
	    	
	    	
	    	Random rand = new Random();
			double[][] flowers= new double[100][4];
			double wspolczynnikUczenia = 0.1;
			
			
			for(int i=0;i<100;i++) {
				for(int j=0;j<4;j++) {
					flowers[i][j]=rand.nextDouble();
				}
			}
			
			int wybrany = rand.nextInt(100);
			int id=0;
			perc[][] macierzPerceptronow = new perc[colNumber][colNumber];
			for(int i=0;i<colNumber;i++) {
				for(int j=0;j<rowNumber;j++) {
					macierzPerceptronow[i][j]=new perc(flowers[wybrany]);
					macierzPerceptronow[i][j].setId(id);
					id++;
				}
			}
			///////////////////////////////////////
			//////////Uczenie//////////////////////
			///////////////////////////////////////
			perc doUczenia = null;
			for(int z=0;z<10000;z++) {
				GraphicsContext gc = canvas.getGraphicsContext2D();
				gc.setStroke(Color.WHITE);
		    	gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());		    	
				wybrany = rand.nextInt(100);
				doUczenia = macierzPerceptronow[0][0];
				for(int i=0;i<colNumber;i++) {
					for(int j=0;j<rowNumber;j++) {
						macierzPerceptronow[i][j].setVector(flowers[wybrany]);
						if(doUczenia.sum()<macierzPerceptronow[i][j].sum()) {
							doUczenia = macierzPerceptronow[i][j];
						}
					}
				}
				doUczenia.ucz(wspolczynnikUczenia, flowers[wybrany]);
				for(int i=0;i<colNumber;i++) {
					for(int j=0;j<rowNumber;j++) {
						for(int g=0;g<4;g++) {
						}
					}	
				}
				/*try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
			//////////////////////////////////////
			///////////////Testowanie/////////////
			//////////////////////////////////////
			GraphicsContext gc = canvas.getGraphicsContext2D();
			int[] percs = new int[16];
			System.out.println("Zakonczono proces uczenia");
			gc.setStroke(Color.RED);
			doUczenia = macierzPerceptronow[0][0];
			int maksi = 0;
			int maksj = 0;
			for(int i=0;i<100;i++) {
				for(int j=0;j<colNumber;j++) {
					for(int k=0;k<rowNumber;k++) {
						macierzPerceptronow[j][k].setVector(flowers[i]);
						if(macierzPerceptronow[j][k].sum()>doUczenia.sum()) {
							doUczenia = macierzPerceptronow[j][k];
							maksi = j;
							maksj = k;
						}
					}
				}
				System.out.println("Kwiatek o dzialce kielicha dlugosci: "+flowers[i][0]+", i szerokosci: "+flowers[i][1]+
						"a takze o dlugosci platka rownej: "+flowers[i][2]+" i szerokosci: "+flowers[i][3]+
						" pobudzil neuron o wspolrzednych "+maksi+" i "+maksj);
				percs[macierzPerceptronow[maksi][maksj].getId()]++;
				gc.strokeRect(canvas.getWidth()/5*maksi+macierzPerceptronow[maksi][maksj].getSuma(),
						canvas.getHeight()/5*maksj+macierzPerceptronow[maksi][maksj].getSuma(),
						3, 3);
			}
			for(int l=0;l<rowNumber*colNumber;l++) {
				System.out.println("Perceptron "+l+", zostal pobudzony "+percs[l]+" razy");
			}
	    }
	    
}
