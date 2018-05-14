import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener{

    private JTextField screen;
    private Button[] numbers;
    private JButton[] operators;

    private double num1 = 0;
    private double num2 = 0;
    private char op = '+';

    public Calculator(){

        super("Rechner");
        setLayout(new BorderLayout());
        //instantiating buttons
        numbers = new Button[10];
        for(int i=0; i<10; i++){
            numbers[i] = new Button("" + i, i);
        }
        operators = new JButton[7];
        operators[0] = new JButton("C");
        operators[1] = new JButton("÷");
        operators[2] = new JButton("x");
        operators[3] = new JButton("-");
        operators[4] = new JButton("+");
        operators[5] = new JButton("=");
        operators[6] = new JButton(".");

        //screen
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        screen = new JTextField(" ", 20);
        screen.setEditable(false);
        top.add(screen);
        top.setBackground(Color.WHITE);
        add(top, BorderLayout.NORTH);

        //numbers and operators
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 1));
        // 7 8 9
        JPanel up = new JPanel();
        up.setLayout(new GridLayout(1, 3));
        for(int i=7; i<=9; i++)
            up.add(numbers[i]);
        center.add(up);
        // 4 5 6
        JPanel mid = new JPanel();
        mid.setLayout(new GridLayout(1, 3));
        for(int i=4; i<=6; i++)
            mid.add(numbers[i]);
        center.add(mid);
        // 1 2 3
        JPanel bot = new JPanel();
        bot.setLayout(new GridLayout(1, 3));
        for(int i=1; i<=3; i++)
            bot.add(numbers[i]);
        center.add(bot);
        // . 0 =
        JPanel bellow = new JPanel();
        bellow.setLayout(new GridLayout(1, 3));
        bellow.add(operators[6]);
        bellow.add(numbers[0]);
        bellow.add(operators[5]);
        center.add(bellow);

        add(center, BorderLayout.CENTER);


        JPanel right = new JPanel();
        right.setLayout(new GridLayout(5,1));
        right.add(operators[0]);
        right.add(operators[1]);
        right.add(operators[2]);
        right.add(operators[3]);
        right.add(operators[4]);
        add(right, BorderLayout.EAST);

        //actions
        for(Button number : numbers)
            number.addActionListener(this);
        for(JButton operator : operators)
            operator.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){

        switch (e.getActionCommand()){

            case "0" : tryNum(screen.getText() + "0");
                break;
            case "1" : tryNum(screen.getText() + "1");
                break;
            case "2" : tryNum(screen.getText() + "2");
                break;
            case "3" : tryNum(screen.getText() + "3");
                break;
            case "4" : tryNum(screen.getText() + "4");
                break;
            case "5" : tryNum(screen.getText() + "5");
                break;
            case "6" : tryNum(screen.getText() + "6");
                break;
            case "7" : tryNum(screen.getText() + "7");
                break;
            case "8" : tryNum(screen.getText() + "8");
                break;
            case "9" : tryNum(screen.getText() + "9");
                break;
            case "C" : screen.setText(" ");
                break;
            case "÷" :
                num1 = setNum();
                op = '÷';
                break;
            case "x" :
                num1 = setNum();
                op = 'x';
                break;
            case "-" :
                if(screen.getText().length() <= 1){
                    screen.setText(screen.getText()+"-");
                    break;
                }
                num1 = setNum();
                op = '-';
                break;
            case "+" :
                num1 = setNum();
                op = '+';
                break;
            case "=" :
                if(screen.getText().length() <= 1)
                    break;
                num2 = Double.parseDouble(screen.getText().trim());
                screen.setText(" " + calculate(num1, num2, op));
                break;
            case "." : if(screen.getText().length() <= 1)
                            screen.setText("0.");
                        else if(!screen.getText().contains("."))
                            screen.setText(screen.getText() + ".");
                        else
                            break;
        }
    }

    private void tryNum(String number){

        String str = number.trim();
        try {
            double num = Double.parseDouble(str);
            if(number.contains("."))
                screen.setText(number);
            else if(num - (int)num == 0.0)
                screen.setText(" " + (int)num);
            else
                screen.setText(" " + num);
        } catch (NumberFormatException e){
            screen.setText(" ");
        }
    }

    private double setNum(){
        double num = 0;
        try{
            num = Double.parseDouble(screen.getText().trim());
        } catch (NumberFormatException e) {
            screen.setText(" ");
        }
        screen.setText(" ");
        return num;
    }

    private String calculate(double num1, double num2, char op){

        switch (op)
        {
            case '÷' :
                if((int)num2 != 0 && num1 / num2 - (int)num1 / (int)num2 == 0.0)
                    return "" + (int)num1 / (int)num2;
                if(num1/num2 - (int)(num1/num2) == 0.0)
                    return "" + (int)(num1/num2);
                return "" + Double.parseDouble(("" + num1 / num2).substring(0,("" + num1 / num2).length()-1));
            case 'x' :
                if( num1 * num2 - (int)num1 * (int)num2 == 0.0)
                    return "" + (int)num1 * (int)num2;
                if(num1*num2 - (int)(num1*num2) < 0.00001)
                    return "" + (int)(num1*num2);
                return "" + Double.parseDouble(("" + num1 * num2).substring(0,("" + num1 * num2).length()-1));
            case '-' :
                if( (num1 - num2) - ((int)num1 - (int)num2) == 0.0)
                    return "" + ((int)num1 - (int)num2);
                return "" + (num1-num2);
            case '+' :
                if( (num1 + num2) - ((int)num1 + (int)num2) == 0.0)
                    return "" + ((int)num1 + (int)num2);
                return "" + (num1+num2);
        }
        return "";
    }
}
