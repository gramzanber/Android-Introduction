package edu.uco.ttachibana.p3tyrelt;

/**
 * Created by Tyrel on 9/18/2016.
 */
public class Game
{
    private char player;
    private short turn;
    private final char[][] gameBoard;

    public Game()
    {
        this.player = 'O'; // O goes first in this game
        this.turn = 0;
        this.gameBoard = new char[3][3];
    }

    public char getPlayer()
    {
        return this.player;
    }

    public void makeMove(char player, int row, int column)
    {
        switch (player)
        {
            case 'O':
                this.gameBoard[row][column] = this.player;
                this.player = 'X';
                this.turn = (short)(this.turn + 1);
                break;
            case 'X':
                this.gameBoard[row][column] = this.player;
                this.player = 'O';
                this.turn = (short)(this.turn + 1);
                break;
            default:
                this.player = 'E';
                System.out.println("Error in class Game, received not normal player!");
                break;
        }
    }

    public boolean anyWinners()
    {
        char check = this.switchUser();
        boolean winner = false;

        if(this.turn > 4)
        {
            for (int i = 0; i < 3; i++)
            {
                if(gameBoard[i][0] == check && gameBoard[i][1] == check && gameBoard[i][2] == check)
                {
                    winner = true;
                }
                if (gameBoard[0][i] == check && gameBoard[1][i] == check && gameBoard[2][i] == check)
                {
                    winner = true;
                }
            }

            if(gameBoard[0][0] == check && gameBoard[1][1] == check && gameBoard[2][2] == check)
            {
                winner = true;
            }
            else if(gameBoard[0][2] == check && gameBoard[1][1] == check && gameBoard[2][0] == check)
            {
                winner = true;
            }
        }

        if(winner)
        {
            this.player = this.switchUser();
        }

        return winner;
    }

    public short getTurn()
    {
        return this.turn;
    }

    private char switchUser()
    {
        char check;
        switch (this.player)
        {
            case 'O':
                check = 'X';
                break;
            case 'X':
                check = 'O';
                break;
            default:
                check = 'E';
                System.out.println("Error in class Game, set winner error!");
                break;
        }
        return check;
    }
}
