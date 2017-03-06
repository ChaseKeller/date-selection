import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;



public class DateSelectionApp extends Applet implements ActionListener, ItemListener
{
	///DECLARE THE MONTHS AND HOW MANY DAYS IN THE MONTH
		String[] monthStrings = {"January","February","March","April",
								"May","June","July","August","September","October","November","December"};
		int[] monthDays = {31,28,31,30,31,30,31,31,30,31,30,31};
		String[] yearStrings = {"2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027"};
		JComboBox yearBox = new JComboBox(yearStrings);

	///BUTTONS
		JButton calendarButton = new JButton("Choose Date");
		JButton nextButton = new JButton(">");
		JButton previousButton = new JButton("<");
		JButton btn[][] = new JButton[12][365];

	///CALENDAR FRAME
		JFrame dayFrame = new JFrame("Day Frame");

	///MONTHS AND DAYS
		int month = 0;
		String days = "0";
		String date = "1";
		String year = "2016";

	///LABELS
		Label monthLabel = new Label(monthStrings[0], Label.CENTER);
		Label dateLabel = new Label(monthStrings[0]+" "+date+", "+ year);

	///LAYOUTS
		CardLayout calendarLayout = new CardLayout();

	///PANELS
		Panel nPanel,cPanel,sPanel;
		JPanel[] monthPanel = new JPanel[12];



	public void init()
	{

		///THIS WILL CREATE THE MONTHS OF THE YEAR INTO DIFFERENT PANELS FOR calendarLayout
		int p = 0;///The month
		int j = 0;///The day button to be created

		///THIS WILL LOOP THROUGH TO CREATE THE MONTH PANELS
		for(p = 0; p < 12 ;p++)
		{monthPanel[p] = new JPanel();	//THIS WILL CYCLE THROUGH TO CREATE A PANEL FOR EACH MONTH
			monthPanel[p].setLayout(new GridLayout(5,7));	////this will set the LAYOUT for 7 days and 5 weeks.

			///THIS LOOP WILL CREATE BUTTONS TO BE ADDED TO THE SPECIFIED PANEL[p]
				for(j=0; j < monthDays[p]; j+=1)	///monthDays is specified as an array containing how many days in the given month.
				{btn[p][j]= new JButton(String.valueOf(j+1));	/// [p] which month in the month array... [i] which day in the monthDay array
					btn[p][j].addActionListener(this);	///give the button purpose :)
					monthPanel[p].add(btn[p][j]);}	///add the button to the panel

		}///END FOR LOOP TO CREATE PANELS


		///BUTTONS
			calendarButton.addActionListener(this);
			previousButton.addActionListener(this);
			nextButton.addActionListener(this);



		///PANELS
		nPanel = new Panel();
		nPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			///ADD nPanel COMPONENTS
				nPanel.add(previousButton);
				nPanel.add(monthLabel);
				nPanel.add(yearBox);
				nPanel.add(nextButton);

		///THIS PANEL WILL HOLD ALL THE MONTH PANELS
			cPanel = new Panel();
				cPanel.setLayout(calendarLayout);
				cPanel.add(monthPanel[0],"0");
				cPanel.add(monthPanel[1],"1");
				cPanel.add(monthPanel[2],"2");
				cPanel.add(monthPanel[3],"3");
				cPanel.add(monthPanel[4],"4");
				cPanel.add(monthPanel[5],"5");
				cPanel.add(monthPanel[6],"6");
				cPanel.add(monthPanel[7],"7");
				cPanel.add(monthPanel[8],"8");
				cPanel.add(monthPanel[9],"9");
				cPanel.add(monthPanel[10],"10");
				cPanel.add(monthPanel[11],"11");

	///SOUTH PANEL
		sPanel = new Panel();

		yearBox.addItemListener(this);

		///CALENDAR FRAME
			dayFrame.setLayout(new BorderLayout());
			dayFrame.setSize(400,300);
			dayFrame.add(nPanel,BorderLayout.NORTH);
			dayFrame.add(cPanel,BorderLayout.CENTER);
			dayFrame.add(sPanel,BorderLayout.SOUTH);

	///add our original components
		add(calendarButton);
		add(dateLabel);

	}

	public void actionPerformed(ActionEvent e)
	{
	///IF THE CALENDAR BUTTON IS PRESSED
		if (e.getSource() == calendarButton)
		{dayFrame.setVisible(true);}

	///IF THE PREVIOUS BUTTON IS PRESSED
		if (e.getSource() == previousButton)
			{
				month-=1;
				if (month <0)
				{month = 11;}
					monthLabel.setText(monthStrings[month]);
			}///END IF STATEMENT

	///IF THE NEXT BUTTON IS PRESSED
		if (e.getSource() == nextButton)
			{
				month+=1;
				if (month >11)
				{month = 0;}
					monthLabel.setText(monthStrings[month]);
			}///END IF STATEMENT

	///CHECK WHICH MONTH THE month variable is at AND CHANGE THE PANEL OF cPanel
		calendarLayout.show(cPanel, String.valueOf(month));

	///THIS LOOP WILL CHANGE THE DATE THAT WAS CLICKED BY THE USER. THEN IT WILL CLOSE THE FRAME dayFrame
		int i = 0;
		int p = 0;
		for(p = 0; p < 12 ;p++)
		{
				for(i = 0; i<365; i++)
				{
					if (e.getSource() == btn[p][i])
					{days = btn[p][i].getText();
						dateLabel.setText(monthStrings[p]+" "+days+", "+ year);
						dayFrame.setVisible(false);
					}
				}///END DAY LOOP
		}///END MONTH LOOP

	}///END ACTION EVENT


	public void itemStateChanged(ItemEvent e)
	{
		if (e.getSource() == yearBox)
		{
				int i = 0;
				for(i=0;i<20;i++)
				{
					if (yearBox.getSelectedIndex()== i)
					{year = yearStrings[i];
					dateLabel.setText(monthStrings[month] + " " + days + ", " + year);
					}

				}
		}///END IF STATEMENT


	}///END ITEM LISTENER
}///END SCRIPT