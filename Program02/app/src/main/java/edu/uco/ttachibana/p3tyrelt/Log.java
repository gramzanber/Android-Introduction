package edu.uco.ttachibana.p3tyrelt;

/**
 * Created by Tyrel on 9/18/2016.
 */
public class Log
{
    private String[] playerData, actionData, timeData;
    private short iterator;

    Log()
    {
        playerData = new String[500];
        actionData = new String[500];
        timeData = new String[500];
        iterator = 0;
    }

    public void addHistory(String player, String action, String time)
    {
        if(iterator == 500) { shiftHistory(); iterator = 499; }
        playerData[iterator] = player;
        actionData[iterator] = action;
        timeData[iterator] = time;
        iterator = (short)(iterator + 1);
    }

    public int getAmountInLog() { return (int)iterator; }
    public String getPlayerData(int index) { return playerData[index]; }
    public String getActionData(int index) { return actionData[index]; }
    public String getTimeData(int index) { return timeData[index]; }

    private void shiftHistory()
    {
        for(int index = 0; index < 500; index = index + 1)
        {
            playerData[index] = playerData[index - 1];
            actionData[index] = actionData[index - 1];
            timeData[index] = timeData[index - 1];
        }
    }

    public void clearLog()
    {
        playerData = new String[500];
        actionData = new String[500];
        timeData = new String[500];
        iterator = 0;
    }
}
