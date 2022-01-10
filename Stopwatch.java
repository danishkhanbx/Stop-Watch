package StopWatch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch implements ActionListener
{
	
	JFrame frame = new JFrame();	                // creating the frame on which watch will display 
	JButton startButton = new JButton("START");    // creating & naming the Buttons 
	JButton resetButton = new JButton("RESET");
	JLabel timeLabel = new JLabel();             // creating the Label on which timer will show up
	
	int elapsedTime = 0;
	int seconds = 0;
	int minutes = 0;
	int hours = 0;
	boolean started = false;
	
	String seconds_string = String.format("%02d", seconds);  // %02d = 00
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);

//  this function will increase the timer by 1 second and perform the respective operations on second, minutes, & hours after the timer has started
	Timer timer = new Timer(1000, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			elapsedTime+=1000;
			hours = (elapsedTime/3600000);       // time passed divided by milliseconds in an hour
			minutes = (elapsedTime/60000) % 60; // time passed divided by milliseconds in minutes, it wont show value above 60 when reaches 59 -> 60 = 0 then 1,2,3,....
			seconds = (elapsedTime/1000) % 60; //  1 second = 1000 millisecond
			
			// updating all the string values
			String seconds_string = String.format("%02d", seconds);
			String minutes_string = String.format("%02d", minutes);
			String hours_string = String.format("%02d", hours);
			timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);	
		}
		
	});
	
	Stopwatch()
	{
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
		timeLabel.setBounds(0, 0, 200, 100); // in the frame the Label will be located at -> x-coordinate & y-coordinate from the 0,0 of frame, breadth & height of the Label Box
		timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1)); // Border thinness
		timeLabel.setForeground(new Color(0x00FF00));
		timeLabel.setBackground(Color.black);
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		
		startButton.setBounds(0, 100, 100, 50);
		startButton.setFont(new Font("",Font.PLAIN,20));
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		
		resetButton.setBounds(100, 100, 100, 50);
		resetButton.setFont(new Font("",Font.PLAIN,20));
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		frame.add(startButton);
		frame.add(resetButton);
		frame.add(timeLabel);
		
		frame.setTitle("Stop Watch");        // Frame Title
		frame.setSize(213,188);
		frame.setLocation(500,150);           // Window coordinates
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource() == startButton)  // when the STARTButton is clicked
		{
			if(started==false)           // if timer is not on, on it and changed the button name to STOP, then start the timer calling start function
			{
				started = true;
				startButton.setText("STOP");
				start();
			}
			else                     // if timer is off on it, on it and changed the button name to STOP, then stop the timer calling stop function
			{
				started = false;
				startButton.setText("START");
				stop();
			}
		}
		if(e.getSource() == resetButton)     // when the RESETButton is clicked
		{
			started = false;               // off the Timer, changed the button name to STOP then call reset operation to reset all the values in the String Label
			startButton.setText("START");
			reset();
		}
	}
	
	void start()
	{
		timer.start();
	}
	
	void stop()
	{
		timer.stop();
	}
	
	void reset()
	{
		timer.stop();
		elapsedTime = 0;   // reseting everything & saving it in the Label string
		seconds = 0;
		minutes = 0;
		hours = 0;
		String seconds_string = String.format("%02d", seconds);
		String minutes_string = String.format("%02d", minutes);
		String hours_string = String.format("%02d", hours);
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);	
	}

}
