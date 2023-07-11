package linux.java.algo.후위표기식2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * packageName    : linux.java.algo.후위표기식2
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-05-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-05-15        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
            int n = Integer.parseInt(br.readLine());
            
            String expressionStr = br.readLine();
            Operand[] operands = new Operand[26];
            for (int i = 0; i < n; i++) {
                operands[i] = Operand.from(br.readLine());
            }
            
            Expression expression = Expression.from(expressionStr);
            Calc calc = new Calc(operands, expression);
            calc.calculate();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * DTO 격
     */
    static class Calc {
        private final Operand[] operands;
        private final Expression expression;
        
        public Calc(Operand[] operands, Expression expression) {
            if (operands == null || expression == null) {
                throw new IllegalArgumentException("null 은 안돼");
            }
            
            this.operands = operands;
            this.expression = expression;
        }
        
        public void calculate() {
            System.out.println("operands = " + Arrays.toString(operands));
            System.out.println("expression = " + expression);
        }
    }
    
    static class Expression {
        private String expression;
        private final int MAX_LENGTH = 100;
        
        public Expression(String expression) {
            if (expression.length() > MAX_LENGTH) {
                String errMsg = MessageFormat.format("후위표현식의 길이가 {0}을 초과합니다.", MAX_LENGTH);
                throw new IllegalArgumentException(errMsg);
            }
            this.expression = expression;
        }
        
        public static Expression from(String s) {
            return new Expression(s);
        }
        
        @Override
        public String toString() {
            return new StringJoiner(", ", Expression.class.getSimpleName() + "[", "]")
                    .add("expression='" + expression + "'")
                    .add("MAX_LENGTH=" + MAX_LENGTH)
                    .toString();
        }
    }
    
    /**
     * 피연산자
     */
    static class Operand {
        private int value;
        
        public Operand(int value) {
            this.value = value;
        }
        
        public static Operand from(String s) {
            int num = Integer.parseInt(s);
            return new Operand(num);
        }
        
        @Override
        public String toString() {
            return new StringJoiner(", ", Operand.class.getSimpleName() + "[", "]")
                    .add("value=" + value)
                    .toString();
        }
    }
}

