/*
OOP Lab Test 2020
Student Name: Jiyuan Liu
Student Number: D17129141
*/

package ie.tudublin;

public class Main
{

    public void gant()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Gantt());
    }
    
    public static void main(String[] arg)
    {
        Main main = new Main();
		main.gant();        
    }
}