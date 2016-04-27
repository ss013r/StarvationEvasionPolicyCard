package starvationevasion.client.Networking;

import starvationevasion.client.GUI.GUI;
import starvationevasion.client.Logic.ChatManager;
import starvationevasion.common.EnumRegion;
import starvationevasion.common.policies.EnumPolicy;
import starvationevasion.common.policies.PolicyCard;
import starvationevasion.communication.CommModule;
import starvationevasion.server.model.State;

import java.util.ArrayList;

/**
 * Proof of concept.
 */
public class ClientTest implements Client
{
  /**
   * Gets the region associated with this client.
   *
   * @return region
   */
  @Override
  public EnumRegion getRegion()
  {
    return null;
  }

  /**
   * Gets the hand maintained by this client at the present time.
   *
   * @return array list containing hand data.
   */
  @Override
  public ArrayList<EnumPolicy> getHand()
  {
    return null;
  }

  /**
   * Returns the chat manager maintained by this client. A chat manager should interface
   * with this client's communication module behind the scenes.
   *
   * @return valid chat manager
   */
  @Override
  public ChatManager getChatManager()
  {
    return null;
  }

  /**
   * Gets the communication module.
   *
   * @return communication module
   */
  @Override
  public CommModule getCommunicationModule()
  {
    return null;
  }

  /**
   * This should return a valid state for this client (as received from server).
   *
   * @return last-valid state sent to the client
   */
  @Override
  public State getState()
  {
    return null;
  }

  /**
   * Gets a list of policy cards that this client currently has.
   *
   * @return list of policy cards
   */
  @Override
  public ArrayList<PolicyCard> getVotingCards()
  {
    return null;
  }

  /**
   * Gets the GUI object that was registered with this client.
   *
   * @return gui
   */
  @Override
  public GUI getGui()
  {
    return null;
  }

  /**
   * Sets the GUI for this client.
   *
   * @param gui valid gui reference
   */
  @Override
  public void setGUI(GUI gui)
  {

  }

  /**
   * This should send a request to the server to login. The return statement only reflects
   * whether the statement was sent or not, rather than whether the login was successful.
   *
   * @param username username to use
   * @param password password to try
   * @return true if the request was sent and false if the send failed
   */
  @Override
  public boolean loginToServer(String username, String password)
  {
    return false;
  }

  /**
   * Attempts to register a new account with the server.
   *
   * @param username username to create
   * @param password password to associate
   * @param region   region tied to this user
   * @return true if the request was sent and false if the send failed
   */
  @Override
  public boolean createUser(String username, String password, EnumRegion region)
  {
    return false;
  }

  /**
   * Asks the client to vote the given card up.
   *
   * @param card card to vote up
   * @return true if the vote up request was sent and false if the send failed
   */
  @Override
  public boolean voteUp(PolicyCard card)
  {
    return false;
  }

  /**
   * Asks the client to vote the given card down.
   *
   * @param card card to vote down
   * @return true if the vote up request was sent and false if the send failed
   */
  @Override
  public boolean voteDown(PolicyCard card)
  {
    return false;
  }

  /**
   * Tells the client to send a ready request.
   *
   * @return true if the ready request was sent and false if the send failed
   */
  @Override
  public boolean ready()
  {
    return false;
  }

  /**
   * Tells the client to send a done request to the server so it can move forward.
   *
   * @return true if the done request was sent and false if the send failed
   */
  @Override
  public boolean done()
  {
    return false;
  }

  /**
   * Tries to draft the given card (interfaces with server).
   *
   * @param card card to draft
   * @return true if the draft request was sent and false if the send failed
   */
  @Override
  public boolean draftCard(PolicyCard card)
  {
    return false;
  }

  /**
   * Tries to discard the given card (interfaces with server).
   *
   * @param card card to discard
   * @return true if the discard request was sent and false if the send failed
   */
  @Override
  public boolean discardCard(PolicyCard card)
  {
    return false;
  }

  /**
   * Shuts down the client. It is not meant to be used at all after this is called.
   */
  @Override
  public void shutdown()
  {

  }

  /**
   * When called, this should perform all necessary updates to keep the client up to date with
   * the current state of the game. This function should only ever be called from one thread.
   *
   * @param deltaSeconds change in seconds since the last time this function was called
   */
  @Override
  public void update(double deltaSeconds)
  {

  }
}
