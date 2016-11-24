package edu.uco.ttachibana.p3tyrelt;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class LogActivity extends AppCompatActivity
{
    public Log logger;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        this.logger = TicTacToeActivity.logger;
        buildLog();
    }

    private void buildLog()
    {
        if(logger.getAmountInLog() > 0)
        {
            TableLayout tl = (TableLayout) findViewById(R.id.historyTable);
            TableRow tr = new TableRow(this);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            TextView tvLeft = new TextView(this);
            TextView tvCenter = new TextView(this);
            //TextView tvRight = new TextView(this);
            tr.setLayoutParams(lp);

            //LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            tvLeft.setLayoutParams(lp);
            tvLeft.setBackgroundColor(Color.WHITE);
            tvLeft.setText("#     ");
            tvCenter.setLayoutParams(lp);
            tvCenter.setBackgroundColor(Color.WHITE);
            tvCenter.setText("Winner");
            //tvRight.setLayoutParams(lp);
            //tvRight.setBackgroundColor(Color.WHITE);
            //tvRight.setText("Date");

            tr.addView(tvLeft);
            tr.addView(tvCenter);
            //tr.addView(tvRight);

            tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            for (int index = 0; index < logger.getAmountInLog(); index = index + 1) {
                TableRow trExtra = new TableRow(this);
                TextView tvLeftExtra = new TextView(this);
                TextView tvCenterExtra = new TextView(this);
                //TextView tvRightExtra = new TextView(this);
                tvLeftExtra.setLayoutParams(lp);
                tvLeftExtra.setBackgroundColor(Color.WHITE);
                tvLeftExtra.setText((index + 1) + "");
                tvCenterExtra.setLayoutParams(lp);
                tvCenterExtra.setBackgroundColor(Color.WHITE);
                tvCenterExtra.setText(logger.getPlayerData(index));
                //tvRightExtra.setLayoutParams(lp);
                //tvRightExtra.setBackgroundColor(Color.WHITE);
                //tvRightExtra.setText(logger.getTimeData(index));

                trExtra.addView(tvLeftExtra);
                trExtra.addView(tvCenterExtra);
                //trExtra.addView(tvRightExtra);

                tl.addView(trExtra, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            }
        }
        else
        {
            TableLayout tl = (TableLayout) findViewById(R.id.historyTable);
            TableRow tr = new TableRow(this);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            TextView tvCenter = new TextView(this);
            tr.setLayoutParams(lp);

            tvCenter.setLayoutParams(lp);
            tvCenter.setBackgroundColor(Color.WHITE);
            tvCenter.setText("NONE");

            tr.addView(tvCenter);

            tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }
    }

    public void clearLog()
    {
        logger.clearLog();
        buildLog();
    }
}
