import javax.swing.*;

public class Fibonacci {

    public static void main(String args[]) {
        Fibonacci f = new Fibonacci();
        int a = f.fibonacci(3);

        JOptionPane.showMessageDialog(null,Integer.toString(a));
    }


    public int fibonacci(int n) {
        int fib;
        if (n == 1) {
            fib = 1;
        } else {
            if (n == 2) {
                fib = 1;
            } else {

                fib = fibonacci(n - 1) + fibonacci(n - 2);
                // 3-1 =2
                //3-2 = 1
            }
        }
        return fib;
    }
}

