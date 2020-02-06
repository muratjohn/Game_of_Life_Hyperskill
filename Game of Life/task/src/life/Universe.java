package life;

import java.util.Random;

public class Universe {
    //model
    private static Random random;
    private static boolean[][] world;
    private int currentGeneration;
    private int currentlyAlive;
    private static int size;

    public Universe(int size) {
        Universe.size = size;
        random = new Random();
        initializeWorld(size);
    }

    public static void setSize(int size) {
        Universe.size = size;
    }

    public void reset() {
        initializeWorld(size);
        currentGeneration = 0;
    }

    public int getCurrentGeneration() {
        return currentGeneration;
    }

    public int getCurrentlyAlive() {
        return currentlyAlive;
    }

    public static boolean[][] getWorld() {
        return world;
    }

    public static int getSize() {
        return size;
    }

    private void initializeWorld(int size) {
        world = new boolean[size][size];
        currentlyAlive = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                world[i][j] = random.nextBoolean();
                if (world[i][j]) {
                    currentlyAlive++;
                }
            }
        }
        //  showWorld();
    }

    public void nextGeneration() {
        boolean[][] state = new boolean[size][size];
        currentlyAlive = 0;
        currentGeneration++;
        //calculate next Generation universe
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                boolean isAlive = world[j][k];
                state[j][k] = (isAlive && canSurvive(j, k));
                if (!isAlive && canRevive(j, k)) {
                    state[j][k] = true;
                }
                if (state[j][k]) {
                    currentlyAlive++;
                }
            }
        }
        //update world

        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                world[j][k] = state[j][k];
            }
        }
        //showWorld();
    }

    public void showWorld() {
        try {
            Thread.sleep(15);
        } catch (InterruptedException ignored) {
        }
        System.out.println("Generation #" + currentGeneration);
        System.out.println("Alive: " + currentlyAlive);
        for (boolean[] rows : world) {
            for (boolean column : rows) {
                System.out.print(column ? 'O' : '.');
//                System.out.print(column ? 'O' : ' ');
            }
            System.out.println();
        }
        System.out.println("----------------");
    }

    private boolean canSurvive(int row, int column) {
        int count = countAlive(row, column);
        return count == 2 || count == 3;
    }

    private boolean canRevive(int row, int column) {
        int count = countAlive(row, column);
        return count == 3;
    }

    private int countAlive(int row, int column) {
        int count = 0;
        for (int rows = -1; rows < 2; rows++) {
            for (int columns = -1; columns < 2; columns++) {
                int currentCol = column + columns;
                int currentRow = row + rows;

                // if mid
                if (rows == 0 && columns == 0)
                    continue;

                // if col underflow
                if (currentCol == -1)
                    currentCol = world.length - 1;
                // if col overflow
                if (currentCol == world.length)
                    currentCol = 0;

                // if row underflow
                if (currentRow == -1) {
                    currentRow = world.length - 1;
                }
                // if row overflow
                if (currentRow == world.length)
                    currentRow = 0;


                if (world[currentRow][currentCol]) {
                    count++;
                }
            }
        }
        return count;
    }

}