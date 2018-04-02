package com.example.admin.navigationproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Admin on 4/1/2018.
 */

public class CalculatorFragment extends Fragment {
        public enum OperatorType
        {
            Add,
            Subtract,
            Multiply,
            Divide
        }

        OperatorType currentOperator;
        boolean displayingCurrentOperand = false;
        double currentOperand = Double.NaN;
        TextView display;

        Button ce;
        Button c;
        Button bs;
        Button div;
        Button mul;
        Button min;
        Button plu;
        Button neg;
        Button dec;
        Button eq;
        Button b0;
        Button b1;
        Button b2;
        Button b3;
        Button b4;
        Button b5;
        Button b6;
        Button b7;
        Button b8;
        Button b9;

        @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View view = inflater.inflate(R.layout.calculator_view, container, false);

            ce = view.findViewById(R.id.ce);
            ce.setOnClickListener(cClickedListener);
            c = view.findViewById(R.id.c);
            c.setOnClickListener(cClickedListener);
            bs = view.findViewById(R.id.bs);
            bs.setOnClickListener(bsClickedListener);
            div = view.findViewById(R.id.div);
            div.setOnClickListener(operatorsClickedListener);
            mul = view.findViewById(R.id.mul);
            mul.setOnClickListener(operatorsClickedListener);
            min = view.findViewById(R.id.min);
            min.setOnClickListener(operatorsClickedListener);
            plu = view.findViewById(R.id.plu);
            plu.setOnClickListener(operatorsClickedListener);
            neg = view.findViewById(R.id.neg);
            neg.setOnClickListener(negateClickedListener);
            dec = view.findViewById(R.id.dec);
            dec.setOnClickListener(numberClickedListener);
            eq = view.findViewById(R.id.eq);
            eq.setOnClickListener(equalsClickedListener);
            b0 = view.findViewById(R.id.b0);
            b1 = view.findViewById(R.id.b1);
            b2 = view.findViewById(R.id.b2);
            b3 = view.findViewById(R.id.b3);
            b4 = view.findViewById(R.id.b4);
            b5 = view.findViewById(R.id.b5);
            b6 = view.findViewById(R.id.b6);
            b7 = view.findViewById(R.id.b7);
            b8 = view.findViewById(R.id.b8);
            b9 = view.findViewById(R.id.b9);
            b0.setOnClickListener(numberClickedListener);
            b1.setOnClickListener(numberClickedListener);
            b2.setOnClickListener(numberClickedListener);
            b3.setOnClickListener(numberClickedListener);
            b4.setOnClickListener(numberClickedListener);
            b5.setOnClickListener(numberClickedListener);
            b6.setOnClickListener(numberClickedListener);
            b7.setOnClickListener(numberClickedListener);
            b8.setOnClickListener(numberClickedListener);
            b9.setOnClickListener(numberClickedListener);



            display = view.findViewById(R.id.display);
            return view;
        }

        private View.OnClickListener numberClickedListener = new View.OnClickListener() {
            public void onClick(View v)
            {
                if (displayingCurrentOperand)
                {
                    display.setText("");
                    displayingCurrentOperand = false;
                }
                Button asButton = (Button)v;
                if (display.getText().toString().equals("0"))
                    display.setText(asButton.getText().toString());
                else
                    display.setText(display.getText().toString() + asButton.getText().toString());
            }
        };
        public void numberClicked(View view)
        {
            if (displayingCurrentOperand)
            {
                display.setText("");
                displayingCurrentOperand = false;
            }
            Button asButton = (Button)view;
            if (display.getText().toString().equals("0"))
                display.setText(asButton.getText().toString());
            else
                display.setText(display.getText().toString() + asButton.getText().toString());
        }
        private View.OnClickListener cClickedListener = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                display.setText("0");
                displayingCurrentOperand = false;
                currentOperand = Double.NaN;
            }
        };
        public void cClicked(View view)
        {
            display.setText("0");
            displayingCurrentOperand = false;
            currentOperand = Double.NaN;
        }
        private View.OnClickListener bsClickedListener = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String dispayText = display.getText().toString();
                if (dispayText.length() > 1)
                    display.setText(dispayText.subSequence(0, dispayText.length() - 1));
                else
                    display.setText("0");
            }
        };
        public void bsClicked(View view)
        {
            String dispayText = display.getText().toString();
            if (dispayText.length() > 1)
                display.setText(dispayText.subSequence(0, dispayText.length() - 1));
            else
                display.setText("0");
        }
        private View.OnClickListener negateClickedListener = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (display.getText().toString().charAt(0) != '-')
                    display.setText("-" + display.getText().toString());
                else
                    display.setText(display.getText().toString().substring(1, display.getText().toString().length()));
            }
        };
        public void negateClicked(View view)
        {
            if (display.getText().toString().charAt(0) != '-')
                display.setText("-" + display.getText().toString());
            else
                display.setText(display.getText().toString().substring(1, display.getText().toString().length()));
        }
        private View.OnClickListener operatorsClickedListener = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Button asButton = (Button)v;
                String buttonText = asButton.getText().toString();

                switch (buttonText)
                {
                    case "+":
                        currentOperator = OperatorType.Add;
                        break;
                    case "-":
                        currentOperator = OperatorType.Subtract;
                        break;
                    case "✕":
                        currentOperator = OperatorType.Multiply;
                        break;
                    case "÷":
                        currentOperator = OperatorType.Divide;
                        break;
                }
                if (!displayingCurrentOperand)
                {
                    if (Double.isNaN(currentOperand))
                        currentOperand = Double.parseDouble(display.getText().toString());
                    else
                        currentOperand = operate();
                    displayingCurrentOperand = true;
                    display.setText(Double.toString(currentOperand));
                }
            }
        };
        public void operatorClicked(View view)
        {
            Button asButton = (Button)view;
            String buttonText = asButton.getText().toString();

            switch (buttonText)
            {
                case "+":
                    currentOperator = OperatorType.Add;
                    break;
                case "-":
                    currentOperator = OperatorType.Subtract;
                    break;
                case "✕":
                    currentOperator = OperatorType.Multiply;
                    break;
                case "÷":
                    currentOperator = OperatorType.Divide;
                    break;
            }
            if (!displayingCurrentOperand)
            {
                if (Double.isNaN(currentOperand))
                    currentOperand = Double.parseDouble(display.getText().toString());
                else
                    currentOperand = operate();
                displayingCurrentOperand = true;
                display.setText(Double.toString(currentOperand));
            }
        }
        private View.OnClickListener equalsClickedListener = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (!displayingCurrentOperand && !Double.isNaN(currentOperand))
                {
                    display.setText(String.valueOf(operate()));
                    currentOperand = Double.NaN;
                }
            }
        };
        public void equalsClicked(View view)
        {
            if (!displayingCurrentOperand && !Double.isNaN(currentOperand))
            {
                display.setText(String.valueOf(operate()));
                currentOperand = Double.NaN;
            }
        }
        public double operate()
        {
            double operandTwo = Double.parseDouble(display.getText().toString());
            switch (currentOperator)
            {
                case Add:
                    return currentOperand + operandTwo;
                case Subtract:
                    return currentOperand - operandTwo;
                case Multiply:
                    return currentOperand * operandTwo;
                case Divide:
                    return currentOperand / operandTwo;
                default:
                    return 0;
            }
        }
    }
