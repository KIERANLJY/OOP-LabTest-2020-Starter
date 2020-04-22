/*
OOP Lab Test 2020
Student Name: Jiyuan Liu
Student Number: D17129141
*/

package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{

	public ArrayList<Task> tasks = new ArrayList<Task>();
	// horizontal position of the start point
	float[] startX;
	// horizontal position of the end point
	float[] endX;
	// vertical position of the task
	float[] y;
	// allows to change the position of start point if true
	boolean startChange;
	// allows to change the position of end point if true
	boolean endChange;
	
	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		// load the csv file
		Table t = loadTable("tasks.csv", "header");
		for(TableRow row:t.rows())
		{
			Task task = new Task(row);
			tasks.add(task);
		}
	}

	public void printTasks()
	{
		for(Task t:tasks)
		{
			println(t.getTask() + ", " + t.getStart() + ", " + t.getEnd());
		}
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");
		for(int i = 0; i < tasks.size(); i++)
		{
			// allows to change the position of start point if click on the right place
			if(mouseX >= startX[i] && mouseX <= startX[i] + 20)
			{
				startChange = true;
			}
			// allows to change the position of end point if click on the right place
			if(mouseX <= endX[i] && mouseX >= endX[i] - 20)
			{
				endChange = true;
			}
		}
	}

	public void mouseReleased()
	{
		// reset startChange and endChange when release the mouse
		startChange = false;
		endChange = false;
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
		float gap = width * 0.8f / 29;

		// can only change the position of one point at a time
		if(startChange)
		{
			endChange = false;
		}
		if(endChange)
		{
			startChange = false;
		}

		for(int i = 0; i < tasks.size(); i++)
		{
			// change the position of start point
			if(startChange && mouseY >= y[i] && mouseY < y[i] + height * 0.06f)
			{
				if(mouseX >= startX[i] + gap && endX[i] - startX[i] > gap + gap / 2)
				{
					startX[i] += gap;
				}
				if(mouseX <= startX[i] - gap && startX[i] > width * 0.15f)
				{
					startX[i] -= gap;
				}
			}
			// change the position of end point
			if(endChange && mouseY >= y[i] && mouseY < y[i] + height * 0.06f)
			{
				if(mouseX >= endX[i] + gap && endX[i] < width * 0.95f)
				{
					endX[i] += gap;
				}
				if(mouseX <= endX[i] - gap && endX[i] - startX[i] > gap + gap / 2)
				{
					endX[i] -= gap;
				}
			}
		}
	}

	public void displayTasks()
	{
		colorMode(HSB);

		// draw the grid
		stroke(255);
		for(int i = 1; i <= 30; i++)
		{
			// get the x-coordinate of each line
			float x = map(i, 1, 30, width * 0.15f, width * 0.95f);
			line(x, height * 0.05f, x, height * 0.95f);
			textAlign(PApplet.CENTER, PApplet.CENTER);
			fill(255);
			text(i, x, height * 0.025f);
		}

		// draw rectangles
		noStroke();
		for(int i = 0; i < tasks.size(); i++)
		{
			// get the color gap
			float colorGap = map(i, 0, tasks.size(), 0, 255);
			fill(colorGap, 255, 255);
			rect(startX[i], y[i], endX[i] - startX[i], height * 0.06f);
			textAlign(PApplet.LEFT, PApplet.CENTER);
			fill(255);
			text(tasks.get(i).getTask(), width * 0.05f, y[i] + 15);
		}
	}
	
	public void setup() 
	{
		loadTasks();
		printTasks();
		startX = new float[tasks.size()];
		endX = new float[tasks.size()];
		y = new float[tasks.size()];
		// intializes original positions of start points and end points
		for(int i = 0; i < tasks.size(); i++)
		{
			startX[i] = map(tasks.get(i).getStart(), 1, 30, width * 0.15f, width * 0.95f);
			endX[i] = map(tasks.get(i).getEnd(), 1, 30, width * 0.15f, width * 0.95f);
			y[i] = map(i, 0, tasks.size(), height * 0.15f, height * 0.75f);
		}
	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}
