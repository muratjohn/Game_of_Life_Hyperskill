package life;

public class Main {

    public static void main(String[] args) {

        // currently field dimension is hard coded;
        // it will be divided by the array size and
        // rounded up to be used in rendering loops,
        // so it works for any number,
        // but displayed "correctly" for only some numbers.
        // eg for 29, it will calculate correctly but render only 28 x 28 part
        // for 31, renders only 30 x 30 part and so on.

        int size = 100;

        // Assemble all the pieces of the MVC
        Universe m = new Universe(size);
        GameOfLife v = new GameOfLife();
        Controller c = new Controller(m, v);
        c.init();

    }

}