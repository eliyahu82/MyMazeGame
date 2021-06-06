
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerMovement implements KeyListener {
    private Cube cube;

    public PlayerMovement(Cube cube) {
        this.cube = cube;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                if (cube.getX() < Def.GAME_WIDTH - Def.CUBE_WIDTH - Def.SCREEN_BOUND)
                this.cube.move(Def.DIRECTION_RIGHT);
                break;

            case KeyEvent.VK_LEFT:
                if (cube.getX() > Def.X)
                    this.cube.move(Def.DIRECTION_LEFT);
                break;

            case KeyEvent.VK_UP:
                if (cube.getY() > Def.CUBE_HEIGHT + Def.SCREEN_BOUND)
                    this.cube.move(Def.DIRECTION_UP);
                break;

            case KeyEvent.VK_DOWN:
                if (cube.getY() < Def.GAME_HEIGHT - Def.CUBE_HEIGHT - Def.SCREEN_BOUND )
                    this.cube.move(Def.DIRECTION_DOWN);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


