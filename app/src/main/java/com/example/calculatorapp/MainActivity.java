package com.example.calculatorapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double firstNum = 0;
    String operation = "";
    boolean isSecondNum = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView screen = findViewById(R.id.screen);
        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        Button on = findViewById(R.id.on);
        Button off = findViewById(R.id.off);
        Button ac = findViewById(R.id.ac);
        Button del = findViewById(R.id.del);
        Button div = findViewById(R.id.div);
        Button times = findViewById(R.id.times);
        Button min = findViewById(R.id.min);
        Button equal = findViewById(R.id.equal);
        Button plus = findViewById(R.id.plus);
        Button point = findViewById(R.id.point);


        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        for (Button b : nums) {
            b.setOnClickListener(view -> {
                String buttonText = b.getText().toString();
                String currentText = screen.getText().toString();


                if (currentText.equals("0") || (isSecondNum && operation.isEmpty())) {
                    screen.setText(buttonText);
                } else {
                    screen.setText(currentText + buttonText);
                }

                isSecondNum = false;
            });
        }


        div.setOnClickListener(view -> handleOperation("/", screen));
        times.setOnClickListener(view -> handleOperation("X", screen));
        min.setOnClickListener(view -> handleOperation("-", screen));
        plus.setOnClickListener(view -> handleOperation("+", screen));


        ac.setOnClickListener(view -> {
            firstNum = 0;
            operation = "";
            isSecondNum = false;
            screen.setText("0");
        });


        del.setOnClickListener(view -> {
            String currentText = screen.getText().toString();
            if (currentText.length() > 1) {
                screen.setText(currentText.substring(0, currentText.length() - 1));
            } else {
                screen.setText("0");
            }
        });


        point.setOnClickListener(view -> {
            String currentText = screen.getText().toString();
            if (!currentText.contains(".")) {
                screen.setText(currentText + ".");
            }
        });

        equal.setOnClickListener(view -> {
            String[] parts = screen.getText().toString().split("[" + operation + "]");
            if (parts.length == 2) {
                double secondNum = Double.parseDouble(parts[1]);
                double result = calculateResult(firstNum, secondNum, operation);
                screen.setText(String.valueOf(result));
                firstNum = result;
                operation = "";
                isSecondNum = true;
            }
        });


        on.setOnClickListener(view -> screen.setVisibility(View.VISIBLE));
        off.setOnClickListener(view -> screen.setVisibility(View.GONE));
    }


    private void handleOperation(String op, TextView screen) {
        if (!screen.getText().toString().equals("0")) {
            firstNum = Double.parseDouble(screen.getText().toString());
            operation = op;
            screen.setText(screen.getText().toString() + " " + operation + " ");
            isSecondNum = true;
        }
    }

    private double calculateResult(double firstNum, double secondNum, String operation) {
        switch (operation) {
            case "/":
                if (secondNum == 0) {
                    return 0;
                }
                return firstNum / secondNum;
            case "X":
                return firstNum * secondNum;
            case "+":
                return firstNum + secondNum;
            case "-":
                return firstNum - secondNum;
            default:
                return 0;
        }
    }
}
