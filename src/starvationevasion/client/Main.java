package starvationevasion.client;

import starvationevasion.client.Setup.StartAnimation;

public class Main
{
  public static void main(String args[]) throws Exception
  {
    //PhaseHandler.setCurrentPhase(ConfigPhase.class);
    StartAnimation animation=new StartAnimation();
    animation.launch(StartAnimation.class,args);

   // GUI.launch(GUI.class, args);
  }
}