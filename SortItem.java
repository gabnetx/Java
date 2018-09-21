/*
 * @(#)SortItem.java	1.17f 95/04/10 James Gosling
 *
 * Copyright (c) 1994-1995 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL or COMMERCIAL purposes and
 * without fee is hereby granted. 
 * Please refer to the file http://java.sun.com/copy_trademarks.html
 * for further important copyright and trademark information and to
 * http://java.sun.com/licensing.html for further important licensing
 * information for the Java (tm) Technology.
 * 
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 * THIS SOFTWARE IS NOT DESIGNED OR INTENDED FOR USE OR RESALE AS ON-LINE
 * CONTROL EQUIPMENT IN HAZARDOUS ENVIRONMENTS REQUIRING FAIL-SAFE
 * PERFORMANCE, SUCH AS IN THE OPERATION OF NUCLEAR FACILITIES, AIRCRAFT
 * NAVIGATION OR COMMUNICATION SYSTEMS, AIR TRAFFIC CONTROL, DIRECT LIFE
 * SUPPORT MACHINES, OR WEAPONS SYSTEMS, IN WHICH THE FAILURE OF THE
 * SOFTWARE COULD LEAD DIRECTLY TO DEATH, PERSONAL INJURY, OR SEVERE
 * PHYSICAL OR ENVIRONMENTAL DAMAGE ("HIGH RISK ACTIVITIES").  SUN
 * SPECIFICALLY DISCLAIMS ANY EXPRESS OR IMPLIED WARRANTY OF FITNESS FOR
 * HIGH RISK ACTIVITIES.
 */

import java.awt.*;
import java.io.InputStream;
import java.util.Hashtable;
import java.net.*;

/**
 * A simple applet class to demonstrate a sort algorithm.
 * You can specify a sorting algorithm using the "alg"
 * attribyte. When you click on the applet, a thread is
 * forked which animates the sorting algorithm.
 *
 * @author James Gosling
 * @version 	1.17f, 10 Apr 1995
 * @history Modified by Pat Morin on Feb. 7 1996.
 */
public class SortItem extends java.applet.Applet  {

    Choice choice;
    SortPanel sortPanel;

    /**
     * The default constructor, builds a new SortItem.
     */
    public SortItem() {
	BorderLayout b = new BorderLayout();
	setLayout(b);
	choice = new Choice();
	choice.addItem("BozoSort");
	choice.addItem("PermSort");
	choice.addItem("StoogeSort");
	choice.addItem("QMSort");
	choice.addItem("BubbleSort");
	choice.addItem("SelectionSort");
	choice.addItem("CocktailSort");
	choice.addItem("InsertionSort");
	choice.addItem("ShakerSort");
	choice.addItem("ShakerSortTwo");
	choice.addItem("ShellSort");
	choice.addItem("QSort");
	choice.addItem("HeapSort");
	choice.addItem("JSort");
	// choice.addItem("JiSort");
	choice.addItem("MergeSort");

       	add("North", choice);
	add("South", sortPanel = new SortPanel());
	setBackground(Color.white);
	Dimension size = b.preferredLayoutSize(this);
	resize(size.width, size.height);
    }

    public boolean handleEvent(Event evt) {
	if (evt.target.equals(choice)) {
	    System.out.println(evt);
	}
	if (evt.target.equals(choice) && evt.id == Event.ACTION_EVENT) {
	    System.out.println(evt);
	    String alg = choice.getSelectedItem();
	    sortPanel.setAlgorithm(alg);
	    return true;
	}
	return super.handleEvent(evt);
   }

    /**
     * Initialize the applet.
     */
    public void init() {
	String at = getParameter("alg");
	if (at == null) {
	    at = "BubbleSort";
	}
	choice.select(at);
	sortPanel.setAlgorithm(at);
    }
}

