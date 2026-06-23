package org.nhlstenden.jabberpoint;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import org.nhlstenden.jabberpoint.command.GoToCommand;
import org.nhlstenden.jabberpoint.command.NewFileCommand;
import org.nhlstenden.jabberpoint.command.OpenFileCommand;
import org.nhlstenden.jabberpoint.command.QuitCommand;
import org.nhlstenden.jabberpoint.command.SaveFileCommand;

/**
 * This is the menu controller
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {

  private final Frame parent;
  private final Presentation presentation;

  private static final long serialVersionUID = 227L;

  public MenuController(Frame frame, Presentation presentation) {
    this.parent = frame;
    this.presentation = presentation;

    QuitCommand quitCommand = new QuitCommand(this.presentation);
    GoToCommand goToCommand = new GoToCommand(this.presentation);
    OpenFileCommand openFileCommand = new OpenFileCommand(this.presentation);
    SaveFileCommand saveFileCommand = new SaveFileCommand(this.presentation);
    NewFileCommand newFileCommand = new NewFileCommand(this.presentation);

    Menu fileMenu = new Menu("File");
    fileMenu.add(this.createMenuItem("Open", e -> openFileCommand.execute(), 'O'));
    fileMenu.add(this.createMenuItem("New", e -> newFileCommand.execute(), 'N'));
    fileMenu.add(this.createMenuItem("Save", e -> saveFileCommand.execute(), 'S'));
    fileMenu.addSeparator();
    fileMenu.add(this.createMenuItem("Exit", e -> quitCommand.execute()));
    this.add(fileMenu);

    Menu viewMenu = new Menu("View");
    viewMenu.add(this.createMenuItem("Next slide", e -> this.presentation.nextSlide()));
    viewMenu.add(this.createMenuItem("Previous slide", e -> this.presentation.prevSlide()));
    viewMenu.add(this.createMenuItem("Go to...", e -> goToCommand.execute(), 'G'));
    this.add(viewMenu);

    Menu helpMenu = new Menu("Help");
    helpMenu.add(this.createMenuItem("About", e -> AboutBox.show(this.parent), 'A'));
    this.setHelpMenu(helpMenu);
  }

  /** Create a menu item. */
  public MenuItem createMenuItem(String name, ActionListener action) {
    MenuItem menuItem = new MenuItem(name);
    menuItem.addActionListener(action);
    return menuItem;
  }

  /** Create a menu item with a keyboard shortcut. */
  public MenuItem createMenuItem(String name, ActionListener action, char shortcutKey) {
    MenuItem menuItem = new MenuItem(name, new MenuShortcut(shortcutKey));
    menuItem.addActionListener(action);
    return menuItem;
  }
}
