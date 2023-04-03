//Max Yang B1
//Recursion Calculator Project
//04.03.23

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter an expression with only positive integers, without spaces, and only using these symbols:  +, -, *, /, ^, (, ) ");
        String input = sc.nextLine();
        
        //Creates an ArrayList that contains all of the numbers and modifiers
        List<String> eq1 = new ArrayList<String>();
        for (int i = 0; i < input.length(); i++) {
            
            //Checks if input contains only integers and symbols
            if (isValid(input.substring(i,i+1)) == false) {
                System.out.println("Syntax Error: Please enter a valid input");
                System.exit(0);
            }
            
            int n = 0;
            while ((i+n < input.length()-1) && (isInt(input.substring(i+n, i+n+1)) && isInt(input.substring(i+n+1, i+n+2)))) {
                n++;
            }
            eq1.add(input.substring(i, i+n+1));
            i += n;
        }

        System.out.println(eq1);
        
        System.out.println("Total is " + (calculate(eq1)).get(0));

        //System.out.println(input + " = " + recursionCalculate(input));

    }


    public static List<String> calculate(List<String> equation) {

        //Calculations done in PEMDAS order using if and else if statements
        while (equation.contains("+") || equation.contains("-") || equation.contains("*") || equation.contains("/") || equation.contains("^") || equation.contains("(") || equation.contains(")")) {
            
            int total = 0;
            //Checks for statements in parantheses and then performs recursion to calculate value
            if (equation.contains("(")) {
                int parCounter = 0;
                int parPos = equation.indexOf("(");
                equation.remove(parPos);
                parCounter++;

                boolean endFound = false;
                int endParPos = 0;

                //Uses a counter system to ensure there is an appropriate amounts of opening and closing parantheses
                while (endFound == false) {
                    for (int i = parPos; i < equation.size(); i++) {
                        if (equation.get(i).equals("(")) {
                            parCounter++;
                        }
                        if (equation.get(i).equals(")")) {
                            parCounter--;
                        }
                        if (parCounter == 0) {
                            endParPos = i;
                            endFound = true;
                            break;                            
                        }
                    }

                    if (parCounter != 0) {
                        System.out.println("Syntax Error: Please enter a valid input with correct number of parantheses");
                        System.exit(0);
                    }

                }

                equation.remove(endParPos);
                calculate(equation.subList(parPos, endParPos));
            }

            //Checks for statmements with powers
            else if (equation.contains("^")) {
                //finds index
                int powPos = equation.indexOf("^");
                //performs calculation
                total = (int) Math.pow(Integer.parseInt(equation.get(powPos-1)), Integer.parseInt(equation.get(powPos+1)));
                
                //sets the total of this calculation to the index before the power and then removes the other two spots
                equation.set(powPos-1, Integer.toString(total));
                equation.remove(powPos+1);
                equation.remove(powPos);
                
                System.out.println(equation);

            }

            //Checks for statmements with multiplication
            else if (equation.contains("*")) {
                int mulPos = equation.indexOf("*");
                total = Integer.parseInt(equation.get(mulPos-1)) * Integer.parseInt(equation.get(mulPos+1));
                equation.set(mulPos-1, Integer.toString(total));
                equation.remove(mulPos+1);
                equation.remove(mulPos);
                System.out.println(equation);

            }

            //Checks for statmements with division
            else if (equation.contains("/")) {
                int divPos = equation.indexOf("/");
                total = Integer.parseInt(equation.get(divPos-1)) / Integer.parseInt(equation.get(divPos+1));
                
                equation.set(divPos-1, Integer.toString(total));
                equation.remove(divPos+1);
                equation.remove(divPos);
                
                System.out.println(equation);
            } 

            //Checks for statmements with addition
            else if (equation.contains("+")) {
                int addPos = equation.indexOf("+");
                total = Integer.parseInt(equation.get(addPos-1)) + Integer.parseInt(equation.get(addPos+1));
                
                equation.set(addPos-1, Integer.toString(total));
                equation.remove(addPos+1);
                equation.remove(addPos);
                
                System.out.println(equation);
            }

            //Checks for statmements with subtraction
            else if (equation.contains("-")) {
                int subPos = equation.indexOf("-");
                total = Integer.parseInt(equation.get(subPos-1)) - Integer.parseInt(equation.get(subPos+1));
                
                equation.set(subPos-1, Integer.toString(total));
                equation.remove(subPos+1);
                equation.remove(subPos);
                 
                System.out.println(equation);
            }
        }

        return equation;
    }

    //Tests if input contains only integers and symbols
    public static boolean isValid(String s) {
        if (s.equals("(") || s.equals(")") || s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {
            return true;
        }

        else {
            try {
                Integer.parseInt(s);
                return true;
            }
            
            catch (NumberFormatException e)
            {
                return false;
            }
        }
    }

    //Tests if input is an integer
    public static boolean isInt(String strNum) {
            if (strNum.equals("+") || strNum.equals("-") || strNum.equals("*") || strNum.equals("/") || strNum.equals("^") || strNum.equals("(") || strNum.equals(")")) {
                return false;
            }
            else {
                return true;
            } 
    }
   

}
