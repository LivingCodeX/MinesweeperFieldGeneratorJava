package me.livingcodex.minesweepgenerator;

public class MinesweeperField {

    private final int width, height;
    private Bomb[] bombLocations;
    private final int[][] field;

    public MinesweeperField(int width, int height) {
        this.width = width;
        this.height = height;
        this.field = new int[width][height];
    }

    public void generateField(Bomb[] bombLocations) {
        this.bombLocations = bombLocations;
        clearField();

        for (Bomb bomb : bombLocations) {
            int x = bomb.getX();
            int y = bomb.getY();
            field[x][y] = -1;
        }

        for (Bomb bomb : bombLocations) {
            int x = bomb.getX();
            int y = bomb.getY();

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int offsetX = x + i;
                    int offsetY = y + j;

                    if (offsetX < 0 || offsetX >= width) continue;
                    if (offsetY < 0 || offsetY >= height) continue;

                    int n = field[x + i][y + j];
                    if (n == -1) continue;

                    field[x + i][y + j]++;
                }
            }
        }
    }

    public String[] field2Text() {
        int[][] yx = new int[height][width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                yx[y][x] = field[x][y];
            }
        }

        String[] lines = new String[height];
        for (int i = 0; i < height; i++) {
            int[] intLine = yx[i];
            StringBuilder line = new StringBuilder(width);
            for (int n : intLine) {
                if (n == -1) {
                    line.append("█");
                } else if (n == 0) {
                    line.append(" ");
                }else{
                    line.append(n);
                }
            }
            lines[i] = line.toString();
        }

        return lines;
    }

    private void clearField() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field[x][y] = 0;
            }
        }
    }

}