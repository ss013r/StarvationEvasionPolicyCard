package starvationevasion.client.GUI.votingHud;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class VotnigButtonNode extends NodeTemplate
{
  private File u = new File("src/starvationevasion/client/GUI/votingHud/testImages/up.png");
  private ImageView uiv = new ImageView(new Image(u.toURI().toString()));
  private Button up = new Button("", uiv);
  private File a = new File("src/starvationevasion/client/GUI/votingHud/testImages/abs.png");
  private ImageView aiv = new ImageView(new Image(a.toURI().toString()));
  private Button abs = new Button("", aiv);
  private File d = new File("src/starvationevasion/client/GUI/votingHud/testImages/down.png");
  private ImageView div = new ImageView(new Image(d.toURI().toString()));
  private Button down = new Button("", div);
  private File v = new File("src/starvationevasion/client/GUI/votingHud/testImages/votes.png");
  private ImageView viv = new ImageView(new Image(v.toURI().toString()));
  private File s = new File("src/starvationevasion/client/GUI/votingHud/testImages/selection.png");
  private ImageView siv = new ImageView(new Image(s.toURI().toString()));
  private Button selection = new Button("", siv);
  private Button votes = new Button("", viv);
  private Button[] buttons = new Button[4];
  private ImageView[] images = new ImageView[4];
  private VotingCard card;
  private int buttonID;

  public VotnigButtonNode()
  {
    votes.setTextFill(Color.BLACK);
    votes.setContentDisplay(ContentDisplay.CENTER);

    images[0] = uiv;
    images[1] = aiv;
    images[2] = div;
    images[3] = viv;

    up.setOnAction(new EventHandler<ActionEvent>()
    {

      @Override
      public void handle(ActionEvent event)
      {
        System.out.println("Card gets +1 vote");
        setVotes(1);
        buttonID = card.getStatus();
        onResize();
        selection.setVisible(true);

      }
    });
    abs.setOnAction(new EventHandler<ActionEvent>()
    {

      @Override
      public void handle(ActionEvent event)
      {
        System.out.println("Player abstains");
        buttonID = 1;
        onResize();
        setVotes(10);
        selection.setVisible(true);

      }
    });
    down.setOnAction(new EventHandler<ActionEvent>()
    {

      @Override
      public void handle(ActionEvent event)
      {
        System.out.println("Card gets -1 vote");
        setVotes(-1);
        buttonID = 2;
        onResize();
        selection.setVisible(true);

      }
    });

    buttons[0] = up;
    buttons[1] = abs;
    buttons[2] = down;
    buttons[3] = votes;

    double height = 10;

    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i].setTranslateY(i * height);
      buttons[i].setStyle("-fx-background-color: transparent;");

      this.getChildren().add(buttons[i]);
    }

    selection.setStyle("-fx-background-color: transparent;");
    selection.setVisible(false);

    this.getChildren().add(selection);
    onResize();
  }

  @Override
  public void onResize()
  {
    double spacing = this.getWidth();

    for (int i = 0; i < buttons.length; i++)
    {
      Button vb = buttons[i];
      if (vb == null) return;
      // vb.setSize(spacing, spacing);
      buttons[i].setLayoutX(-this.getWidth());
      buttons[i].setTranslateY(10);
      buttons[i].setLayoutY(spacing * i);

      images[i].fitWidthProperty().bind(this.widthProperty());
      images[i].fitHeightProperty().bind(this.widthProperty());

      siv.fitWidthProperty().bind(this.widthProperty());
      siv.fitHeightProperty().bind(this.widthProperty());
    }
    selection.setLayoutX(-this.getWidth());
    selection.setTranslateY(10);
    selection.setLayoutY(spacing * buttonID);
    votes.setStyle("-fx-font: " + this.getWidth() / 2.5 + " arial; -fx-background-color: transparent;");
  }

  public void setSize(double width, double height)
  {
    this.setWidth(width);
    this.setHeight(height);
  }

  public void setCard(VotingCard card)
  {
    this.card = card;
    buttonID = card.getStatus();
    if (!card.getYea() && !card.getNay() && !card.getAbstain())
    {
      selection.setVisible(false);
    }
    else selection.setVisible(true);
    onResize();
  }

  public void setVotes(int i)
  {
    if (i != 0)
    {
      card.setHasBeenVoted(true);
    }
    card.setVotes(i);
    votes.setText(Integer.toString(card.getVotes()) + "/" + Integer.toString(card.getVotesNeeded()));
    
    card.setBorders(true);
  }

}
