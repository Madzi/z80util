/*
 * GUI.java - The GUI for Spectrum emulator.
 * 
 * (C) 2009, Mikko Nummelin <mikko.nummelin@tkk.fi>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

package org.mn.z80util.spectrum;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.apache.log4j.*;
import org.mn.z80util.*;

public class SpectrumGUI {
	Logger LOG=Logger.getLogger(SpectrumGUI.class);
	
	/* The GUI components */
	private JFrame GUIFrame, debuggerFrame;
	private JMenuBar GUIFrameMenuBar, debuggerFrameMenuBar;
	private JMenu fileMenu, actionMenu, viewMenu, helpMenu;
	private JMenuItem loadItem, saveItem, exitItem, stepItem, continueItem,
		debuggerItem, aboutItem;
	
	/* The event listener, which is also the main Spectrum controller for
	 * keyboard and menu options. */
	private SpectrumControls controller;
	public void setController(SpectrumControls controller) {
		this.controller=controller;
	}
	
	/* The screen */
	private SpectrumScreen screen;
	public void setScreen(SpectrumScreen screen) {
		this.screen=screen;
	}

	public void createAndShowGUI() {
		LOG.info("Creating the GUI.");

		/* Creation of the main GUI frame */
		GUIFrame=new JFrame();
		GUIFrame.setTitle("Jeccy - Spectrum emulator - (C) 2009, Mikko Nummelin");
		GUIFrame.setIconImage(LogoFactory.createLogo());
		GUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Menu bar */
		GUIFrameMenuBar=new JMenuBar();
		GUIFrame.setJMenuBar(GUIFrameMenuBar);

		/* File menu */
		fileMenu=new JMenu("File");
		loadItem=new JMenuItem("Load", KeyEvent.VK_F3);
		loadItem.addActionListener(controller);
		fileMenu.add(loadItem);
		saveItem=new JMenuItem("Save", KeyEvent.VK_F2);
		saveItem.addActionListener(controller);
		fileMenu.add(saveItem);
		fileMenu.add(new JSeparator());
		exitItem=new JMenuItem("Exit", KeyEvent.VK_F10);
		exitItem.addActionListener(controller);
		fileMenu.add(exitItem);
		GUIFrameMenuBar.add(fileMenu);
		
		/* Action menu */
		actionMenu=new JMenu("Action");
		stepItem=new JMenuItem("Step", KeyEvent.VK_F11);
		stepItem.addActionListener(controller);
		actionMenu.add(stepItem);
		continueItem=new JMenuItem("Continue", KeyEvent.VK_F12);
		continueItem.addActionListener(controller);
		actionMenu.add(continueItem);
		GUIFrameMenuBar.add(actionMenu);
		
		/* View menu */
		viewMenu=new JMenu("View");
		debuggerItem=new JMenuItem("Debugger", KeyEvent.VK_F8);
		debuggerItem.addActionListener(controller);
		viewMenu.add(debuggerItem);
		GUIFrameMenuBar.add(viewMenu);
		
		/* Help menu */
		helpMenu=new JMenu("Help");
		aboutItem=new JMenuItem("Help", KeyEvent.VK_F1);
		aboutItem.addActionListener(controller);
		helpMenu.add(aboutItem);
		GUIFrameMenuBar.add(helpMenu);
		
		/* Creation of the debugger GUI frame */
		debuggerFrame=new JFrame();
		debuggerFrame.setTitle("Z80 disassembler/debugger - (C) 2009, Mikko Nummelin");
		debuggerFrame.setIconImage(LogoFactory.createLogo());
		debuggerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		/* Finishing the setup and setting the main frame, but NOT debugger
		 * frame, visible */
		GUIFrame.add(screen);
		GUIFrame.addKeyListener(controller);
		GUIFrame.pack();
		GUIFrame.setResizable(false);
		GUIFrame.setVisible(true);
	}
}
