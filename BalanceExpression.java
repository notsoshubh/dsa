import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BalanceExpression {

    private List<Character> leftBrackets = Arrays.asList('(','{','[','<');
    private List<Character> rightBrackets = Arrays.asList(')','}',']','>');
    
    public boolean check(String exp){

        Stack<Character> stack = new Stack<>();
        for (char ch:exp.toCharArray()){
            if (isLeftBracket(ch))
            stack.push(ch);
            
            if (isRightBracket(ch)){
                if (stack.isEmpty()) return false;

                var top = stack.pop();
                if (!bracketsMatch(top, ch)) return false;
            }
        }
        return stack.isEmpty();
    }

    private boolean isLeftBracket(char ch){
        return leftBrackets.contains(ch);
    }

    private boolean isRightBracket(char ch){
        return rightBrackets.contains(ch);
    }

    private boolean bracketsMatch(char left, char right){
        return leftBrackets.indexOf(left) == rightBrackets.indexOf(right);
    }
}
