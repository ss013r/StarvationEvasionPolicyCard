package starvationevasion.client.GUI.Popups;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import starvationevasion.client.GUI.GUI;
import starvationevasion.client.GUI.Graphs.Graph;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import starvationevasion.common.EnumRegion;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * GUI element which displays the graphs during the drafting phase
 */
public class GraphDisplay extends BorderPane
{
  GUI gui;
  ImageView leftArrow;
  ImageView rightArrow;

  ComboBox<EnumRegion> regionSelect;

  private int dataVisMode = 0; //0 for region data, 1 for world data, and 2 for world food prices. 
  
  EnumRegion currentRegion;
  int regionalGraphIndex = 1;
  int foodCropGraphIndex = 1;
  int worldGraphIndex = 1;

  Button left;
  Button right;
  Graph graphDisplay;

  Label regionName = new Label("SDFSDF");
  
  
  
  /**
   * Constructor for GraphDisplay
   * Takes a reference to the GUI which ones it
   * @param gui reference to gui
   */
  public GraphDisplay(GUI gui)
  {
	  
	  
		Button exitButton = new Button("X");
		exitButton.setFont(Font.font("Verdana", 15));
		exitButton.setStyle("-fx-background-color: rgb(230,16,16)");
		exitButton.setOnAction(evt ->
	    {
	    	gui.getPopupManager().toggleGraphDisplay();
	    	if(dataVisMode==0)
	    	{
	    	  gui.updateState();
	    	}
	    	if(dataVisMode==1)
	    	{
	    	  
	    	}
	    	if(dataVisMode==2)
	    	{
	    	  
	    	}
	    }); 
		
    this.gui = gui;
    this.getStylesheets().add("/starvationevasion/client/GUI/DraftLayout/style.css");
    this.getStyleClass().add("graphdisplay");
    this.setVisible(false);

    currentRegion = EnumRegion.USA_CALIFORNIA;//GUI.getAssignedRegion();
    graphDisplay = gui.getGraphManager().getGraphNodeGraph(currentRegion, regionalGraphIndex);

    initializeComboBox();
    initializeLeft();
    initializeRight();
    setAlignment(regionName,Pos.CENTER);
    setTop(regionName);
    
    setAlignment(exitButton, Pos.CENTER_RIGHT);
	setTop(exitButton);
	exitButton.setTranslateX(-25);
	exitButton.setTranslateY(10);
//    setAlignment(regionSelect, Pos.CENTER);
//    setTop(regionSelect);
    setAlignment(left, Pos.CENTER);
    setLeft(left);
    setAlignment(right, Pos.CENTER);
    setRight(right);
    this.setCenter(gui.getGraphManager().getPieChart(regionalGraphIndex));
   // setAlignment(graphDisplay.getPieChart(),Pos.CENTER);
   // setCenter(graphDisplay.getPieChart());
  }

  /**
   * Closes this display
   */
  public void open()
  {
    this.setVisible(true);
  }

  /**
   * Opens this display
   */
  public void close()
  {
    this.setVisible(false);
  }

  public void setDataVisMode(int mode)
  {
	  this.dataVisMode = mode;
	  if(dataVisMode==0)
	  {
		  this.setCenter(gui.getGraphManager().getPieChart(regionalGraphIndex));
	  }
	  if(dataVisMode==1)
	  {
		  
	  }
	  if(dataVisMode==2)
	  {
		  this.setCenter(gui.getGraphManager().getGraph(foodCropGraphIndex));
	  }
  }
  
  public int getMode()
  {
    return dataVisMode;
  }
  
  
  private void initializeComboBox()
  {
    ArrayList<EnumRegion> regions=new ArrayList<>(Arrays.asList(EnumRegion.values()));
    ObservableList<EnumRegion> regionList= FXCollections.observableArrayList(regions);
    regionSelect =new ComboBox<>(regionList);

    regionSelect.setValue(currentRegion);
    for (int i=  0; i < EnumRegion.values().length; ++i)
    {
      regionSelect.getItems().add(EnumRegion.values()[i]);
    }

    regionSelect.setOnAction((event) -> {
        currentRegion = regionSelect.getValue();
        graphDisplay = gui.getGraphManager().getGraphNodeGraph(currentRegion, regionalGraphIndex);
      setCenter(graphDisplay.getLineChart());
    });
  }

  private void initializeLeft()
  {
    left = new Button();
    leftArrow = new ImageView(gui.getImageGetter().getGraphLeftArrowBig());
    left.setGraphic(leftArrow);
    left.setStyle( "-fx-background-color: transparent;");
    left.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      @Override
      public void handle(MouseEvent event)
      {
        moveLeft();
      }
    });
  }

  private void initializeRight()
  {
    right = new Button();
    rightArrow = new ImageView(gui.getImageGetter().getGraphRightArrowBig());
    right.setGraphic(rightArrow);
    right.setStyle( "-fx-background-color: transparent;");
    right.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      @Override
      public void handle(MouseEvent event)
      {
        moveRight();
      }
    });
  }

  private EnumRegion stringToRegion(Object o)
  {
    String s = (String) o;

    if (s.equalsIgnoreCase(EnumRegion.USA_CALIFORNIA.toString()))
    {
      return EnumRegion.USA_CALIFORNIA;
    }
    else if (s.equalsIgnoreCase(EnumRegion.USA_MOUNTAIN.toString()))
    {
      return EnumRegion.USA_MOUNTAIN;
    }
    else if (s.equalsIgnoreCase(EnumRegion.USA_HEARTLAND.toString()))
    {
      return EnumRegion.USA_HEARTLAND;
    }
    else if (s.equalsIgnoreCase(EnumRegion.USA_SOUTHEAST.toString()))
    {
      return EnumRegion.USA_SOUTHEAST;
    }
    else if (s.equalsIgnoreCase(EnumRegion.USA_SOUTHERN_PLAINS.toString()))
    {
      return EnumRegion.USA_SOUTHERN_PLAINS;
    }
    else if (s.equalsIgnoreCase(EnumRegion.USA_NORTHERN_PLAINS.toString()))
    {
      return EnumRegion.USA_NORTHERN_PLAINS;
    }
    else if (s.equalsIgnoreCase(EnumRegion.USA_NORTHERN_CRESCENT.toString()))
    {
      return EnumRegion.USA_NORTHERN_CRESCENT;
    }
    else
    {
      return EnumRegion.USA_CALIFORNIA;
    }
  }

  private void moveRight()
  {
	  if(dataVisMode==0)
	  {
    regionalGraphIndex += 1;
    if (regionalGraphIndex >= 4)
    {
      regionalGraphIndex = 1;
    }
	  }
	  if(dataVisMode==1)
	  {
		  
		  
	  }
	  if(dataVisMode==2)
	  {
			foodCropGraphIndex += 1;
		    if (foodCropGraphIndex >= 13)
		    {
		    	foodCropGraphIndex = 1;
		    } 
	  }
    boolean isPieChart = gui.getGraphManager().isPieChart(regionalGraphIndex);
    gui.getGraphManager().buildDisplay(regionalGraphIndex);
    if(isPieChart)
    {
    	if(dataVisMode==0)
    	{
    	this.setCenter(gui.getGraphManager().getPieChart(regionalGraphIndex));
    	}
    }
    else
    {
    	this.setCenter(gui.getGraphManager().getBarGraph());
    }
    if(dataVisMode==1)
	{
		
	}
	if(dataVisMode==2)
	{
	 this.setCenter(gui.getGraphManager().getGraph(foodCropGraphIndex));
	}
    
   // graphDisplay = gui.getGraphManager().getGraphNodeGraph(currentRegion, graphIndex);
   // this.setCenter(graphDisplay.getLineChart());
  }

  private void moveLeft()
  {
	if(dataVisMode==0)
	{
    regionalGraphIndex -= 1;
    if (regionalGraphIndex < 1)
    {
      regionalGraphIndex = 3;
    }
	}
	if(dataVisMode==1)
	{
		
	}
	if(dataVisMode==2)
	{
		foodCropGraphIndex -= 1;
	    if (foodCropGraphIndex < 1)
	    {
	    	foodCropGraphIndex = 12;
	    }
	}
    
	
    boolean isPieChart = gui.getGraphManager().isPieChart(regionalGraphIndex);
  //  gui.getGraphManager().buildDisplay(graphIndex);
    if(isPieChart)
    {
    	if(dataVisMode==0)
    	{
    	this.setCenter(gui.getGraphManager().getPieChart(regionalGraphIndex));
    	}
    	
    }
    else
    {
    	this.setCenter(gui.getGraphManager().getBarGraph());
    }
    
    if(dataVisMode==1)
	{
		
	}
	if(dataVisMode==2)
	{
	 this.setCenter(gui.getGraphManager().getGraph(foodCropGraphIndex));
	}
//    graphDisplay = gui.getGraphManager().getGraphNodeGraph(currentRegion, graphIndex);
//    this.setCenter(graphDisplay.getLineChart());
  }
}
