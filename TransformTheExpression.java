import java.io.*;
import java.util.*;

public class InfixToPrefix{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i = 0; i < T ; i++){
			String str = sc.next();
			String postfix = infixToPostfix(str);
			System.out.println(postfix);
		}
	}
	
	public static String infixToPostfix(String str){
		String postfix = "";
		Stack<Character> stack = new Stack<Character>();
		for(int i = 0 ; i < str.length(); i++){
			char expression = str.charAt(i);
			if(expression == ' ' || expression == ',')
					continue;
			else if(isOperator(expression)){
				while (!stack.isEmpty() && stack.peek() != '(' && highPrecedence(expression, stack.peek())){
					postfix = postfix + stack.peek();
					stack.pop();
				}
				stack.push(expression);
			}
			else if(isOperand(expression)){
				postfix = postfix + expression;
			}
			else if(expression == '('){
				stack.push(expression);
			}
			else if(expression == ')'){
				while (!stack.isEmpty() && stack.peek() != '('){
					postfix = postfix + stack.peek();
					stack.pop();
				}
				stack.pop();
			}
		}
		while(!stack.isEmpty()){
			postfix = postfix + stack.peek();
			stack.pop();
		}
		return postfix;
	}
	
	public static boolean isOperator(char ch){
		if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^'){
			return true;
		}
		else 
			return false;
	}
	public static boolean isOperand(char ch){
		if(ch >= 'A' && ch <= 'Z') return true;
		else if (ch >= 'a' && ch <= 'z') return true;
		else if (ch >= '0' && ch <= '9') return true;
		return false;
	}
	public static  boolean highPrecedence(char op1 , char op2){
		int ope1 = getWeightForOperator(op1);
		int ope2 = getWeightForOperator(op2);
		
		if(ope1 == ope2){
			if(isRightAssociaty(op1)){
				return false;
			}
			else 
				return true;
		}
		return (ope1 < ope2) ? true : false;
	}
	public static int getWeightForOperator(char op){
		int weight = -1;
		switch(op){
			case '+':
			case '-':
				weight = 1;
				break;
			case '*':
			case '/':
				weight = 2;
				break;
			case '^':
				weight = 3;
				break;
		}
		return weight;
	}
	public static boolean isRightAssociaty(char op1){
		if(op1 == '^'){
			return true;
		}
		return false;
	}
}
