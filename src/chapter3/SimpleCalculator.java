package chapter3;

import java.util.Scanner;

/**
 * @author Mr.X
 * @version 1.0.0
 * @since 2019/9/3 19:38
 * 采用后缀或逆波兰计法，实现一个简单的计算器
 */
public class SimpleCalculator {

    /**
     * 算符
     */
    private static final String ADD_STR = "+";
    private static final String MINUS_STR = "-";
    private static final String MUTIPLE_STR = "*";
    private static final String DIVIDE_STR = "/";

    /**
     * 栈，用于存储操作数
     */
    private static MyStack<Double> stack = new MyStack(50);


    /**
     * 计算器入口
     */
    public static void calculator() {
        System.out.printf("\t%s \n", "请逐个算符输入算式：");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String inputStr = scanner.next();
            if (inputStr.equals("=")) {
                System.out.printf("\t 算式计算结果是：%.4f \n", stack.pop());
                break;
            } else if (inputStr.matches("([1-9][0-9]*|[0-9])(\\.[0-9]+)?")) {
                stack.push(Double.valueOf(inputStr));
            } else {
                double num_1 = stack.pop();
                double num_2 = stack.pop();
                double result = Operator.calculate(inputStr, num_1, num_2);
                stack.push(result);
            }
        }
        scanner.close();
    }


    public static void main(String[] args) {
        calculator();
    }


    /**
     * 操作符，暂定 + - * /
     */
    private enum Operator {

        ADD("加法", ADD_STR), MINUS("减法", MINUS_STR), MUTIPLE("乘法", MUTIPLE_STR), DIVIDE("除法", DIVIDE_STR);

        /**
         * 描述信息
         */
        private String desc;
        /**
         * 计算符号
         */
        private String operatorStr;

        /**
         * 构造器
         *
         * @param desc 操作符描述信息
         */
        Operator(String desc, String operatorStr) {
            this.desc = desc;
            this.operatorStr = operatorStr;
        }

        /**
         * 根据传入的操作符和操作数进行相应的计算
         *
         * @param operatorStr 操作符
         * @param num_1       操作数1
         * @param num_2       操作数2
         * @return 计算结果
         */
        public static double calculate(String operatorStr, double num_1, double num_2) {
            if (null == operatorStr || operatorStr.trim().length() <= 0)
                throw new IllegalArgumentException("operatorString can't be null.");
            switch (operatorStr) {
                case ADD_STR:
                    return num_1 + num_2;
                case MINUS_STR:
                    return num_1 - num_2;
                case MUTIPLE_STR:
                    return num_1 * num_2;
                case DIVIDE_STR:
                    return num_1 / num_2;
                default:
                    throw new IllegalArgumentException("operator can't be handled.");
            }
        }
    }

}
