package me.livingcodex.minesweepgenerator;

public class MinesweeperField {

    private final int width, height;
    private Bomb[] bombLocations;
    private final int[][] field;

    public MinesweeperField(int width, int height) {
        this.width = width;
        this.height = height;
        this.field = new int[height][width];
    }

    public void generateField(Bomb[] bombLocations) {
        this.bombLocations = bombLocations;
        clearField();

        for (Bomb bomb : bombLocations) {
            int x = bomb.getX();
            int y = bomb.getY();
            field[y][x] = -1;
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

                    int n = field[y + j][x + i];
                    if (n == -1) continue;

                    field[y + j][x + i]++;
                }
            }
        }
    }

    public String[] field2Text() {
        String[] lines = new String[height];
        for (int i = 0; i < height; i++) {
            int[] intLine = field[i];
            StringBuilder line = new StringBuilder(width);
            for (int n : intLine) {
                if (n == -1) {
                    line.append("█");
                } else if (n == 0) {
                    line.append(" ");
                } else {
                    line.append(n);
                }
            }
            lines[i] = line.toString();
        }

        return lines;
    }

    public String[] createDiscordField() {
        String[] lines = new String[height];
        for (int i = 0; i < height; i++) {
            int[] intLine = field[i];
            StringBuilder line = new StringBuilder(width * 5);
            for (int n : intLine) {
                line.append("||");
                String symbol = switch (n) {
                    case -1 -> ":fire:";
                    case 0 -> ":nix:";
                    case 1 -> ":one:";
                    case 2 -> ":two:";
                    case 3 -> ":three:";
                    case 4 -> ":four:";
                    case 5 -> ":five:";
                    case 6 -> ":six:";
                    case 7 -> ":seven:";
                    case 8 -> ":eight:";

                    default -> "ERROR";
                };

                line.append(symbol);
                line.append("||");
            }
            lines[i] = line.toString();
        }

        return lines;
    }

    private void clearField() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field[y][x] = 0;
            }
        }
    }

}