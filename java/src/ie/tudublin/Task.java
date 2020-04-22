/*
OOP Lab Test 2020
Student Name: Jiyuan Liu
Student Number: D17129141
*/

package ie.tudublin;

import processing.data.TableRow;

public class Task 
{

    private String task;
    private int start;
    private int end;

    public Task(String task, int start, int end)
    {
        this.task = task;
        this.start = start;
        this.end = end;
    }

    public Task(TableRow tr)
    {
        this(tr.getString("Task"), tr.getInt("Start"), tr.getInt("End"));
    }

    public String toString()
    {
        return this.task + ", " + this.start + ", " + this.end;
    }

    public String getTask() {
        return task;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}