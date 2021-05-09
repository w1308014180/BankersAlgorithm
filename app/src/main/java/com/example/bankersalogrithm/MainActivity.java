package com.example.bankersalogrithm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int[][] Max = new int[3][3];
    int[][] Allocation = new int[3][3];
    int[][] Need = new int[3][3];
    int[] Avaliable = new int[3];
    int[] Avaliable_new = new int[3];

    int resourceNum = 3;
    int processNum = 3;
    //是否可分配申请资源
    boolean flag = true;
    boolean[] Finish = {false,false,false};




    //初始化控件
    //max矩阵

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText_max11 = (EditText)findViewById(R.id.max1_1);
        EditText editText_max12 = (EditText)findViewById(R.id.max1_2);
        EditText editText_max13 = (EditText)findViewById(R.id.max1_3);
        EditText editText_max21 = (EditText)findViewById(R.id.max2_1);
        EditText editText_max22 = (EditText)findViewById(R.id.max2_2);
        EditText editText_max23 = (EditText)findViewById(R.id.max2_3);
        EditText editText_max31 = (EditText)findViewById(R.id.max3_1);
        EditText editText_max32 = (EditText)findViewById(R.id.max3_2);
        EditText editText_max33 = (EditText)findViewById(R.id.max3_3);
        //allocation矩阵
        EditText editText_alloc11 = (EditText)findViewById(R.id.alloc1_1);
        EditText editText_alloc12 = (EditText)findViewById(R.id.alloc1_2);
        EditText editText_alloc13 = (EditText)findViewById(R.id.alloc1_3);
        EditText editText_alloc21 = (EditText)findViewById(R.id.alloc2_1);
        EditText editText_alloc22 = (EditText)findViewById(R.id.alloc2_2);
        EditText editText_alloc23 = (EditText)findViewById(R.id.alloc2_3);
        EditText editText_alloc31 = (EditText)findViewById(R.id.alloc3_1);
        EditText editText_alloc32 = (EditText)findViewById(R.id.alloc3_2);
        EditText editText_alloc33 = (EditText)findViewById(R.id.alloc3_3);
        //need矩阵
        TextView need11 = (TextView) findViewById(R.id.need1_1);
        TextView need12 = (TextView) findViewById(R.id.need1_2);
        TextView need13 = (TextView) findViewById(R.id.need1_3);
        TextView need21 = (TextView) findViewById(R.id.need2_1);
        TextView need22 = (TextView) findViewById(R.id.need2_2);
        TextView need23 = (TextView) findViewById(R.id.need2_3);
        TextView need31 = (TextView) findViewById(R.id.need3_1);
        TextView need32 = (TextView) findViewById(R.id.need3_2);
        TextView need33 = (TextView) findViewById(R.id.need3_3);

        //avaliable矩阵
        EditText editText_ava1 = (EditText)findViewById(R.id.ava1);
        EditText editText_ava2 = (EditText)findViewById(R.id.ava2);
        EditText editText_ava3 = (EditText)findViewById(R.id.ava3);

        //avaliable_new矩阵
        TextView newAva1 = (TextView) findViewById(R.id.ava_new1);
        TextView newAva2 = (TextView) findViewById(R.id.ava_new2);
        TextView newAva3 = (TextView) findViewById(R.id.ava_new3);

        //申请新资源
        EditText apply1 = (EditText)findViewById(R.id.apply1);
        EditText apply2 = (EditText)findViewById(R.id.apply2);
        EditText apply3 = (EditText)findViewById(R.id.apply3);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);



        //从EditText获取数据并计算需求矩阵
        Button submmit = (Button)findViewById(R.id.summbit_data);
        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击按钮提交数据
                //获取最大需求矩阵信息
                Max[0][0] = Integer.parseInt(editText_max11.getText().toString());
                Max[0][1] = Integer.parseInt(editText_max12.getText().toString());
                Max[0][2] = Integer.parseInt(editText_max13.getText().toString());
                Max[1][0] = Integer.parseInt(editText_max21.getText().toString());
                Max[1][1] = Integer.parseInt(editText_max22.getText().toString());
                Max[1][2] = Integer.parseInt(editText_max23.getText().toString());
                Max[2][0] = Integer.parseInt(editText_max31.getText().toString());
                Max[2][1] = Integer.parseInt(editText_max32.getText().toString());
                Max[2][2] = Integer.parseInt(editText_max33.getText().toString());

                //获取已分配矩阵信息
                Allocation[0][0] = Integer.parseInt(editText_alloc11.getText().toString());
                Allocation[0][1] = Integer.parseInt(editText_alloc12.getText().toString());
                Allocation[0][2] = Integer.parseInt(editText_alloc13.getText().toString());
                Allocation[1][0] = Integer.parseInt(editText_alloc21.getText().toString());
                Allocation[1][1] = Integer.parseInt(editText_alloc22.getText().toString());
                Allocation[1][2] = Integer.parseInt(editText_alloc23.getText().toString());
                Allocation[2][0] = Integer.parseInt(editText_alloc31.getText().toString());
                Allocation[2][1] = Integer.parseInt(editText_alloc32.getText().toString());
                Allocation[2][2] = Integer.parseInt(editText_alloc33.getText().toString());

                //可分配资源
                Avaliable[0] = Integer.parseInt(editText_ava1.getText().toString());
                Avaliable[1] = Integer.parseInt(editText_ava2.getText().toString());
                Avaliable[2] = Integer.parseInt(editText_ava3.getText().toString());

                //获取申请资源信息
                Avaliable_new[0] = Avaliable[0]-Integer.parseInt(apply1.getText().toString());
                Avaliable_new[1] = Avaliable[1]-Integer.parseInt(apply2.getText().toString());
                Avaliable_new[2] = Avaliable[2]-Integer.parseInt(apply3.getText().toString());

                //计算需求矩阵信息
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        Need[i][j] = Max[i][j] - Allocation[i][j];
                    }
                }
                Need[1][0] -= Integer.parseInt(apply1.getText().toString());
                Need[1][1] -= Integer.parseInt(apply2.getText().toString());
                Need[1][2] -= Integer.parseInt(apply3.getText().toString());

                need11.setText(String.valueOf(Need[0][0]));
                need12.setText(String.valueOf(Need[0][1]));
                need13.setText(String.valueOf(Need[0][2]));
                need21.setText(String.valueOf(Need[1][0]));
                need22.setText(String.valueOf(Need[1][1]));
                need23.setText(String.valueOf(Need[1][2]));
                need31.setText(String.valueOf(Need[2][0]));
                need32.setText(String.valueOf(Need[2][1]));
                need33.setText(String.valueOf(Need[2][2]));

                newAva1.setText(String.valueOf(Avaliable_new[0]));
                newAva2.setText(String.valueOf(Avaliable_new[1]));
                newAva3.setText(String.valueOf(Avaliable_new[2]));

            }
        });


        //执行银行家算法
        Button judge = (Button)findViewById(R.id.judge);
        TextView result = (TextView)findViewById(R.id.result_text);
        judge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSafe()==false)
                    result.setText("Unsafe! Can not allocate!");
                else if(isSafe()==true)
                    result.setText("Safe!");
            }
        });
    }

    boolean isSafe()
    {
        //int resourceNumFinish = 0;
        int safeIndex = 0;
        int allFinish = 0;
        int[] work = {0,0,0};
        int r = 0;
        int temp = 0;
        int pNum = 0;
        //预分配为了保护available[]
        for(int i=0; i<3; i++){
            if(Need[1][i]<0)
                return false;
        }
        for(int i = 0; i < resourceNum; i++)
        {
            work[i] = Avaliable[i];
        }
        //把未完成进程置为false
        for(int i = 0; i < processNum; i++)
        {
            boolean result = false;
            if(result == true)
            {
                Finish[i] = true;
                allFinish++;
            }
            else
            {
                Finish[i] = false;
            }

        }
        //预分配开始
        while(allFinish != processNum)
        {
            int num = 0;
            for(int i = 0; i < resourceNum; i++)
            {
                if(Need[r][i] <= work[i] && Finish[r] == false)
                {
                    num ++;
                }
            }
            if(num == resourceNum)
            {
                for(int i = 0; i < resourceNum; i++ )
                {
                    work[i] = work[i] + Allocation[r][i];
                }
                allFinish ++;
                safeIndex ++;
                Finish[r] = true;
            }
            r ++;//该式必须在此处
            if(r >= processNum)
            {
                r = r % processNum;
                if(temp == allFinish)
                {
                    break;
                }
                temp = allFinish;
            }
            pNum = allFinish;
        }
        //判断系统是否安全
        for(int i = 0; i < processNum; i++)
        {
            if(Finish[i] == false)
            {
                return false;
            }
        }

        return true;
    }

}