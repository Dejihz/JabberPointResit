package org.nhlstenden.jabberpoint;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.nhlstenden.jabberpoint.utils.Constants;

/** Controller for the menu. */
public class MenuController extends MenuBar {

  private final Frame parent;
  private final Presentation presentation;

  private static final long serialVersionUID = 227L;

  protected static final String ABOUT = "About";
  protected static final String FILE = "File";
  protected static final String EXIT = "Exit";
  protected static final String GOTO = "Go to";
  protected static final String HELP = "Help";
  protected static final String NEW = "New";
  protected static final String NEXT = "Next";
  protected static final String OPEN = "Open";
  protected static final String PAGENR = "Page number?";
  protected static final String PREV = "Prev";
  protected static final String SAVE = "Save";
  protected static final String VIEW = "View";

  public MenuController(Frame frame, Presentation pres) {
    this.parent = frame;
    this.presentation = pres;
    MenuItem menuItem;
    Menu fileMenu = new Menu(FILE);
    fileMenu.add(menuItem = this.mkMenuItem(OPEN));
    menuItem.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent actionEvent) {
            presentation.clear();
            Accessor xmlAccessor = new XMLAccessor();
            try {
              xmlAccessor.loadFile(presentation, Constants.TEST_FILE);
              presentation.setSlideNumber(0);
            } catch (IOException exc) {
              JOptionPane.showMessageDialog(
                  parent, Constants.IO_ERR + exc, Constants.LOAD_ERR, JOptionPane.ERROR_MESSAGE);
            }
            parent.repaint();
          }
        });
    fileMenu.add(menuItem = this.mkMenuItem(NEW));
    menuItem.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent actionEvent) {
            presentation.clear();
            parent.repaint();
          }
        });
    fileMenu.add(menuItem = this.mkMenuItem(SAVE));
    menuItem.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            Accessor xmlAccessor = new XMLAccessor();
            try {
              xmlAccessor.saveFile(presentation, Constants.DEFAULT_SAVE_PATH);
            } catch (IOException exc) {
              JOptionPane.showMessageDialog(
                  parent, Constants.IO_ERR + exc, Constants.SAVE_ERR, JOptionPane.ERROR_MESSAGE);
            }
          }
        });
    fileMenu.addSeparator();
    fileMenu.add(menuItem = this.mkMenuItem(EXIT));
    menuItem.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent actionEvent) {
            presentation.exit(0);
          }
        });
    this.add(fileMenu);
    Menu viewMenu = new Menu(VIEW);
    viewMenu.add(menuItem = this.mkMenuItem(NEXT));
    menuItem.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent actionEvent) {
            presentation.nextSlide();
          }
        });
    viewMenu.add(menuItem = this.mkMenuItem(PREV));
    menuItem.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent actionEvent) {
            presentation.prevSlide();
          }
        });
    viewMenu.add(menuItem = this.mkMenuItem(GOTO));
    menuItem.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent actionEvent) {
            String pageNumberStr = JOptionPane.showInputDialog((Object) PAGENR);
            try {
              int pageNumber = Integer.parseInt(pageNumberStr);
              presentation.setSlideNumber(pageNumber - 1);
            } catch (NumberFormatException ex) {
              JOptionPane.showMessageDialog(
                  parent, Constants.INT_ERR, Constants.JAB_ERR, JOptionPane.ERROR_MESSAGE);
            }
          }
        });
    this.add(viewMenu);
    Menu helpMenu = new Menu(HELP);
    helpMenu.add(menuItem = this.mkMenuItem(ABOUT));
    menuItem.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent actionEvent) {
            AboutBox.show(parent);
          }
        });
    this.setHelpMenu(helpMenu);
  }

  public MenuItem mkMenuItem(String name) {
    return new MenuItem(name, new MenuShortcut(name.charAt(0)));
  }
}
