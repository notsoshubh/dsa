public class Practice {

    public static void main (String[] args) {
        
        TwoStacks stack = new TwoStacks();
        stack.push1(10);
        stack.push2(20);
        stack.push1(30);
        System.out.println(stack);
        System.out.println(stack.pop1());
        System.out.println(stack);
        System.out.println(stack.isEmpty2());
        System.out.println(stack.pop2());
        System.out.println(stack.isEmpty2());
        System.out.println(stack.pop2());

    }
}