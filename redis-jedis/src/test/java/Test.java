import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {


        ArrayList<String> strings = new ArrayList<String>();
        strings.add("q231");

        strings.stream().map(item -> {

            System.out.println(Thread.currentThread().getName());
            return null;
        });

        System.out.println(Thread.currentThread().getName());


    }
}
