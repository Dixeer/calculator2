package com.example.damian.calculator;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String number1 = "";
    private static String number2 = "";
    private static String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void numberOnClick(View view) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Button numberOfButton = ((Button) view);
        if(number1.length() >= 10 || number2.length() >= 10){
            Toast toast = Toast.makeText(context, "Zbyt duża liczba!", duration);
            toast.show();
        }
        else if (operation.isEmpty()) {
            number1 += numberOfButton.getText().toString();
            TextView viewById = (TextView) findViewById(R.id.firstNumberField);
            viewById.setText(number1);
        } else {
            number2 += numberOfButton.getText().toString();
            TextView viewById = (TextView) findViewById(R.id.secondNumberField);
            viewById.setText(number2);
        }
    }

    public void dotOnClick(View view) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        TextView firstNumberField = (TextView) findViewById(R.id.firstNumberField);
        TextView secondNumberField = (TextView) findViewById(R.id.secondNumberField);

        int fL = firstNumberField.getText().toString().replaceAll("[^.]", "").length();
        int sL = secondNumberField.getText().toString().replaceAll("[^.]", "").length();

        String r = firstNumberField.getText().toString() + secondNumberField.getText().toString();
        int dot = r.replaceAll("[^.]", "").length();

        if (dot > 2 && number1.isEmpty() && number2.isEmpty()) {
            Toast toast = Toast.makeText(context, "Złe użycie przecinka", duration);
            toast.show();
        } else if (!number2.isEmpty() && !operation.isEmpty() && sL < 1) {
            number2 += ".";
            secondNumberField.setText(number2);
        } else if (!number1.isEmpty() && fL < 1) {
            number1 += ".";
            firstNumberField.setText(number1);
        }
    }

    public void twoArgumentOperationOnClick(View view) {
        Button operand = (Button) view;
        operation = operand.getText().toString();

        TextView viewById = (TextView) findViewById(R.id.operatorField);
        viewById.setText(operation);
    }

    public void negativeOnClick(View view) {
        int v;
        if (!number2.isEmpty()) {
            v = Integer.parseInt(number2);
            v = -1 * v;
            number2 = String.valueOf(v);
            TextView viewById = (TextView) findViewById(R.id.secondNumberField);
            viewById.setText(number2);
        } else if (!number1.isEmpty()) {
            v = Integer.parseInt(number1);
            v = -1 * v;
            number1 = String.valueOf(v);
            TextView viewById = (TextView) findViewById(R.id.firstNumberField);
            viewById.setText(number1);
        }
    }

    public void lastRemoveOnClick(View view) {
        if (!number2.isEmpty()) {
            number2 = number2.substring(0, number2.length() - 1);
            TextView secondNumberField = (TextView) findViewById(R.id.secondNumberField);
            secondNumberField.setText(number2);
        } else if (!operation.isEmpty()) {
            operation = operation.substring(0, operation.length() - 1);
            TextView secondNumberField = (TextView) findViewById(R.id.operatorField);
            secondNumberField.setText(operation);
        } else if (!number1.isEmpty()) {
            number1 = number1.substring(0, number1.length() - 1);
            TextView secondNumberField = (TextView) findViewById(R.id.firstNumberField);
            secondNumberField.setText(number1);
        }
    }

    public void removeOnClick(View view) {
        number1 = "";
        number2 = "";
        operation = "";
        TextView firstNumberField = (TextView) findViewById(R.id.firstNumberField);
        TextView secondNumberField = (TextView) findViewById(R.id.secondNumberField);
        TextView operatorField = (TextView) findViewById(R.id.operatorField);
        TextView resultField = (TextView) findViewById(R.id.resultField);
        firstNumberField.setText(number1);
        secondNumberField.setText(number2);
        operatorField.setText(operation);
        resultField.setText("");
    }

    public void resultOnClick(View view) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if (number1.isEmpty() || number2.isEmpty() || operation.isEmpty()) {
            Toast toast = Toast.makeText(context, "Wprowadź poprawne działąnie!", duration);
            toast.show();
        } else {
            double a = Double.parseDouble(number1);
            double b = Double.parseDouble(number2);
            double result;
            switch (operation) {
                case "+":
                    result = a + b;
                    ((TextView) findViewById(R.id.resultField)).setText(String.valueOf(result));
                    break;
                case "*":
                    result = a * b;
                    ((TextView) findViewById(R.id.resultField)).setText(String.valueOf(result));
                    break;
                case "/":
                    if (b == 0) {
                        ((TextView) findViewById(R.id.resultField)).setText("Błąd");
                    } else {
                        result = a / b;
                        ((TextView) findViewById(R.id.resultField)).setText(String.valueOf(result));
                    }
                    break;
                case "-":
                    result = a - b;
                    ((TextView) findViewById(R.id.resultField)).setText(String.valueOf(result));
                    break;
            }

        }
    }


}
