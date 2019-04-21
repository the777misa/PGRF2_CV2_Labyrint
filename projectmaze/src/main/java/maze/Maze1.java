package maze;

import transforms.Point3D;
import utils.LoadTexture;

public class Maze1 extends AbstractMaze {
    int[][] level0 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };
    int[][] level1 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    public Maze1() {
        getLevels().add(level0);
        getLevels().add(level1);
        startPosition = new Point3D(1.5,0.5,1.5);
//        textures.add(LoadTexture.load("/test_texture.jpg"));
//        textures.add(LoadTexture.load("/test_texture.jpg"));
    }
}
