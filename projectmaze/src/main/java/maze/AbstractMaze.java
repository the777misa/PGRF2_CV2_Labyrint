package maze;

import blocks.AbstractBlock;
import blocks.Teleporter;
import player.Player;
import transforms.Point2D;
import transforms.Point3D;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AbstractMaze implements Serializable {

    public final static int COLLISION_SIZE = 5;
    public final static Point3D PLAYER_OFFSET = new Point3D(0.5, 0.5, 0.5);
    private int squareSize = 40;
    private int heightBetweenLevels = 100;
    private Point3D startPosition;
    private List<AbstractBlock[][]> levels = new ArrayList<>();
    private List<String> textureUls = new ArrayList<>();
    private Player player = new Player();

    public void detectCollision(Point2D p) {
        double x = p.getX();
        double z = p.getY();
        double posX = x / squareSize;
        double posZ = z / squareSize;
        double curPosX = player.getPos().getX() / squareSize;
        double curPosZ = player.getPos().getZ() / squareSize;

        if (posZ - curPosZ > 0) {
            posZ = (z + COLLISION_SIZE) / squareSize;
        } else posZ = (z - COLLISION_SIZE) / squareSize;
        if (!levels.get(player.getCurLev())[(int) posZ][(int) posX].hasCollision()) player.getPos().setZ(z);

        posZ = z / squareSize;
        if (posX - curPosX > 0) {
            posX = (x + COLLISION_SIZE) / squareSize;
        } else posX = (x - COLLISION_SIZE) / squareSize;
        if (!levels.get(player.getCurLev())[(int) posZ][(int) posX].hasCollision()) player.getPos().setX(x);


        System.out.println("-----");
        System.out.println("posX - curPosX = " + (posX - curPosX));
        System.out.println("posZ - curPosZ = " + (posZ - curPosZ));
        System.out.println("posX = " + posX);
        System.out.println("posZ = " + posZ);
        System.out.println("curPosX = " + curPosX);
        System.out.println("curPosZ = " + curPosZ);
        System.out.println("curMaze.getLevels().get(0)[posZ][posX] = " + levels.get(0)[(int) posZ][(int) posX].getClass().getName());
        System.out.println("player.getCurLev() = " + player.getCurLev());
    }

    /**
     * @return Returns Block at player if is inside the maze, else returns null
     */
    public AbstractBlock getCurrentBlockAtPlayerLocation() {
        if (player.getPX() < 0 || player.getPZ() < 0 || player.getPX() > levels.get(player.getCurLev()).length * squareSize || player.getPZ() > levels.get(player.getCurLev()).length * squareSize)
            return null;
        else
            return levels.get(player.getCurLev())[(int) player.getPZ() / squareSize][(int) player.getPX() / squareSize];
    }

    /**
     * Resets Player to start Position
     */
    public void resetPlayer() {
        player.setCurrentLevel((int) getStartPosition().getY());

        player.setPos(calcPos(getStartPosition()));
    }

    public void movePlayer(Point3D pos) {
        player.setCurrentLevel((int)pos.getY());
        player.setPos(calcPos(pos));
    }

    public Point3D calcPos(Point3D pos) {
        double px = squareSize * pos.getX() + PLAYER_OFFSET.getX() * squareSize;
        double py = (int) pos.getY() * heightBetweenLevels + PLAYER_OFFSET.getY() * squareSize;
        double pz = squareSize * pos.getZ() + PLAYER_OFFSET.getZ() * squareSize;
        return new Point3D(px, py, pz);
    }


    public List<AbstractBlock[][]> getLevels() {
        return levels;
    }

    public int getSquareSize() {
        return squareSize;
    }

    public int getHeightBetweenLevels() {
        return heightBetweenLevels;
    }


    public Point3D getStartPosition(double offset) {
        return startPosition.addOffset(offset).mul(squareSize);
    }

    public List<String> getTextureUls() {
        return textureUls;
    }


    public Point3D getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Point3D startPosition) {
        this.startPosition = startPosition;
    }

    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
    }

    public void setHeightBetweenLevels(int heightBetweenLevels) {
        this.heightBetweenLevels = heightBetweenLevels;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void checkForTeleport() {
        if (getCurrentBlockAtPlayerLocation() != null) {
            if ((getCurrentBlockAtPlayerLocation() instanceof Teleporter)) {
                movePlayer(((Teleporter) getCurrentBlockAtPlayerLocation()).getTeleportTo());
            }
        }
    }
}
