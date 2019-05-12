package jp.ac.kansai_u.k756880.kadai03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /**
     * 直前に押された計算記号を記憶
     */
    char calculateSign = '0';
    /**
     * '='ボタンを押される前までの途中の計算結果を記憶
     */
    double tmpResultNumber = 0.0;
    /**
     * 直前に押されたボタンを記憶
     */
    char previousPushedButton = '0';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** 各数字ボタンの押下イベントリスナー
     *
     * @param view
     */
    public void numberButtonPushed(View view) {
        switch (view.getId()) {
            case R.id.zero_button:
                if(isSimilarSign('0')) {
                    // 前回、0以外の数字が文字押された
                    glue('0');
                } else {
                    // 前回、0が押された または、　前回、記号文字押された
                    changeView(0.0);
                }
                previousPushedButton = '0';
                break;
            case R.id.one_button:
                if(isSimilarSign('1')) {
                    // 数字が複数回押されたとき
                    glue('1');
                } else {
                    changeView(1.0);
                }
                previousPushedButton = '1';
                break;
            case R.id.two_button:
                if(isSimilarSign('2')) {
                    glue('2');
                } else {
                    changeView(2.0);
                }
                previousPushedButton = '2';
                break;
            case R.id.three_button:
                if(isSimilarSign('3')) {
                    glue('3');
                } else {
                    changeView(3.0);
                }
                previousPushedButton = '3';
                break;
            case R.id.four_button:
                if(isSimilarSign('4')) {
                    glue('4');
                } else {
                    changeView(4.0);
                }
                previousPushedButton = '4';
                break;
            case R.id.five_button:
                if(isSimilarSign('5')) {
                    glue('5');
                } else {
                    changeView(5.0);
                }
                previousPushedButton = '5';
                break;
            case R.id.six_button:
                if(isSimilarSign('6')) {
                    glue('6');
                } else {
                    changeView(6.0);
                }
                previousPushedButton = '6';
                break;
            case R.id.seven_button:
                if(isSimilarSign('7')) {
                    glue('7');
                } else {
                    changeView(7.0);
                }
                previousPushedButton = '7';
                break;
            case R.id.eight_button:
                if(isSimilarSign('8')) {
                    glue('8');
                } else {
                    changeView(8.0);
                }
                previousPushedButton = '8';
                break;
            case R.id.nine_button:
                if(isSimilarSign('9')) {
                    glue('9');
                } else {
                    changeView(9.0);
                }
                previousPushedButton = '9';
                break;
            default:
                System.out.println("undefined button pushed");
                break;
        }

    }

    /** 各計算ボタンの押下イベントリスナー
     *
     * @param view
     */
    public void calculateButtonPushed(View view) {
        switch (view.getId()) {
            case R.id.plus_button:
                if(isSimilarSign('+')) {
                    // 記号が連続で押された場合、計算しない
                } else {
                    tmpResultNumber = calculate();
                    changeView(tmpResultNumber);
                }
                calculateSign = '+';
                previousPushedButton = '+';
                break;
            case R.id.minus_button:
                if(isSimilarSign('-')) {
                    // 記号が連続で押された場合、計算しない
                } else {
                    tmpResultNumber = calculate();
                    changeView(tmpResultNumber);
                }
                calculateSign = '-';
                previousPushedButton = '-';
                break;
            case R.id.multiply_button:
                if(isSimilarSign('×')) {
                    // 記号が連続で押された場合、計算しない
                } else {
                    tmpResultNumber = calculate();
                    changeView(tmpResultNumber);
                }
                calculateSign = '×';
                previousPushedButton = '×';
                break;
            case R.id.divide_button:
                if(isSimilarSign('÷')) {
                    // 記号が連続で押された場合、計算しない
                } else {
                    tmpResultNumber = calculate();
                    changeView(tmpResultNumber);
                }
                calculateSign = '÷';
                previousPushedButton = '÷';
                break;
            case R.id.percent_button:
                if(isSimilarSign('%')) {
                    // 記号が連続で押された場合、計算しない
                } else {
                    tmpResultNumber = calculate();
                    changeView(tmpResultNumber);
                }
                calculateSign = '%';
                previousPushedButton = '%';
                break;
            case R.id.equal_button:
                tmpResultNumber = calculate();
                changeView(tmpResultNumber);
                calculateSign = '0';
                previousPushedButton = '=';
                break;
            default:
                System.out.println("undefined button pushed");
                break;
        }


    }

    /** CAボタンの押下イベントリスナー
     *
     * @param view
     */
    public void clearAllButtonPushed(View view) {
        changeView(0.0);
        calculateSign = '0';
        tmpResultNumber = 0.0;
        previousPushedButton = 'C';
    }

    /**
     * "+/-"ボタンの押下イベントリスナー
     *
     * @param view
     */
    public void plusMinusButtonPushed(View view) {
        TextView textView = findViewById(R.id.resultView);
        double result = Double.parseDouble(textView.getText().toString());

        changeView(result*-1);
        previousPushedButton = 'M';
    }

    /**
     * "."ボタンの押下イベントリスナー
     *
     * @param view
     */
    public void pointButtonPushed(View view) {
        TextView textView = findViewById(R.id.resultView);
        String result = textView.getText().toString();

        // 直前に押されたボタンが記号
        if(isSimilarSign('+') || previousPushedButton == '=') {
            textView.setText("0.");
        // resultViewに'.'が含まれていない
        } else if(!result.contains(".")) {
            glue('.');
        }
        // それ以外は何もしない

        previousPushedButton = '.';
    }

    /**
     * resultViewの表示を変更
     *
     * @param number resultViewに表示する数字
     */
    public void changeView(double number) {
        TextView textView = findViewById(R.id.resultView);

        if(number - (int) number == 0) {
            textView.setText(String.valueOf((int) number));
        } else {
            textView.setText(String.valueOf(number));
        }


    }

    /**
     * tmpResultNumberとresultViewの値をcalculateSignで演算
     *
     * @return 計算結果(double型)
     */
    public double calculate() {
        TextView textView = findViewById(R.id.resultView);
        double result = Double.parseDouble(textView.getText().toString());

        switch (calculateSign) {
            case '+':
                result = tmpResultNumber + result;
                break;
            case '-':
                result = tmpResultNumber = result;
                break;
            case '×':
                result = tmpResultNumber * result;
                break;
            case '÷':
                try
                {
                    // 0で割ろうとするとArithmeticException例外が投げられます。
                    result = tmpResultNumber / result;
                }
                catch( ArithmeticException e )
                {
                    e.printStackTrace();
                }
                break;
            case '%':
                try
                {
                    // 0で割ろうとするとArithmeticException例外が投げられます。
                    result = tmpResultNumber % result;
                }
                catch( ArithmeticException e )
                {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

        System.out.println(String.valueOf(tmpResultNumber) + calculateSign + textView.getText().toString() + "=" + String.valueOf(result));

        return result;
    }

    /**
     * 数字->数字　または、 演算記号->演算記号 または、'.'ボタン-> 数字 の順に押されたかを判定
     *
     * @param currentOne 現在押されているボタン(char型)
     *
     * @return (boolean型)
     */
    public boolean isSimilarSign(char currentOne) {
        TextView textView = findViewById(R.id.resultView);
        String result = textView.getText().toString();

        String[] arr = {"+", "-", "×", "÷", "%"};
        List<String> l = Arrays.asList(arr);

        if (Character.isDigit(previousPushedButton) && Character.isDigit(currentOne)) {
            // 0が複数回押された場合は例外としてfalseを返す
            if (previousPushedButton == '0' && currentOne == '0' && result.equals("0"))
                return false;
            return true;
        } else if (previousPushedButton == '.' && Character.isDigit(currentOne)) {
            // '.'ボタン-> 数字の順に押された場合
            return true;
        } else if (l.contains(String.valueOf(previousPushedButton)) && l.contains(String.valueOf(currentOne))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * resultViewの文字列を結合
     *
     * @param number 既存の文字列に後続する文字(char型)
     */
    public void glue(char number) {
        TextView textView = findViewById(R.id.resultView);

        if(textView.getText().toString().equals("0")) {
            if(number == '.') {
                textView.setText(String.valueOf(textView.getText().toString() + String.valueOf(number)));
            } else {
                textView.setText(String.valueOf(String.valueOf(number)));
            }
        } else {
            textView.setText(String.valueOf(textView.getText().toString() + String.valueOf(number)));
        }
    }

}
