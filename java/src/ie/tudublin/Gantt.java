package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{

	public ArrayList<Task> tasks = new ArrayList<Task>();
	
	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
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
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	public void displayTasks()
	{
		colorMode(HSB);
		for(int i = 0; i < 30; i++)
		{
			float x = map(i, 0, 30, width * 0.15f, width * 0.95f);
			stroke(255);
			line(x, height * 0.05f, x, height * 0.95f);
			textAlign(PApplet.CENTER, PApplet.CENTER);
			fill(255);
			text(i + 1, x, height * 0.025f);
		}
		noStroke();
		for(int i = 0; i < tasks.size(); i++)
		{
			float startX = map(tasks.get(i).getStart(), 1, 31, width * 0.15f, width * 0.95f);
			float endX = map(tasks.get(i).getEnd(), 1, 31, width * 0.15f, width * 0.95f);
			float y = map(i, 0, tasks.size(), height * 0.15f, height * 0.75f);
			float colorGap = map(i, 0, tasks.size(), 0, 255);
			fill(colorGap, 255, 255);
			rect(startX, y, endX - startX, height * 0.06f);
			textAlign(PApplet.LEFT, PApplet.CENTER);
			fill(255);
			text(tasks.get(i).getTask(), width * 0.05f, y + 15);
		}
	}
	
	public void setup() 
	{
		loadTasks();
		printTasks();
	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}
