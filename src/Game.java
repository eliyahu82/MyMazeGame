import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    private Cube cube = new Cube(Def.CUBE_START_X, Def.CUBE_START_Y, Def.CUBE_WIDTH, Def.CUBE_HEIGHT);
    private Rectangle[] obstacles = new Rectangle[Def.SIZE_ARRAY];

    private boolean gameOver;
    private boolean wellDone;

    public static void main(String[] args) {
        Game g1 = new Game();
    }

    public Game() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Def.GAME_WIDTH, Def.GAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("Maze Game");
        this.setResizable(false);

        this.gameOver = false;
        this.wellDone = false;

        JLabel textExplainGame1 = new JLabel ();
        JLabel textExplainGame2 = new JLabel ();
        textExplainGame1.setText("Take the blue cube with the keyboard`s arrows to the green oval on the top ");
        textExplainGame1.setBounds(Def.TEXT_EXPLAIN_GAME_X,Def.TEXT_EXPLAIN_GAME_Y,Def.TEXT_EXPLAIN_GAME_WIDTH,Def.TEXT_EXPLAIN_GAME_HEIGHT);
        textExplainGame2.setText("of the screen. If the blue cube touches any rectangle you will be disqualified");
        textExplainGame2.setBounds(Def.TEXT_EXPLAIN_GAME_X,Def.TEXT_EXPLAIN_GAME_Y_2,Def.TEXT_EXPLAIN_GAME_WIDTH,Def.TEXT_EXPLAIN_GAME_HEIGHT);
        this.add(textExplainGame1);
        this.add(textExplainGame2);


        PlayerMovement playerMovement = new PlayerMovement(this.cube);
        this.addKeyListener(playerMovement);
        GameScene gameScene = new GameScene();
        gameScene.setBounds(Def.ZERO,Def.ZERO,Def.GAME_WIDTH,Def.GAME_HEIGHT);
        this.add(gameScene);
        mainGameLoop();

    }


    public void mainGameLoop() {
        new Thread(() -> {
            while (true) {
                try {
                    checkCollision();
                    checkIfEndOfStage();
                        repaint();
                    Thread.sleep(Def.CUBE_SPEED);
                    if(wellDone){
                        Thread.sleep(Def.TIME_TEXT_MESSAGE);
                        System.out.println("Well done!!\n");
                        System.out.println("For another stage you can click on play");
                        this.setVisible(false); break;}
                    if(gameOver){
                        Thread.sleep(Def.TIME_TEXT_MESSAGE);
                        System.out.println("Game over!!\n");
                        System.out.println("For another stage you can click on play");
                        this.setVisible(false); break;}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void paint(Graphics g) {
        super.paint(g);
        this.cube.paint(g);


            if (!gameOver) {
//print MY_FIRST_GAME
                g.setColor(Color.GREEN);
                g.setFont(new Font("Font", Font.PLAIN, Def.FONT_SIZE));
                g.drawString(Def.MY_FIRST_GAME, Def.MY_FIRST_GAME_X, Def.MY_FIRST_GAME_Y);

                if (wellDone) {
//print wellDone
                    g.setColor(Color.magenta);
                    g.setFont(new Font("Font", Font.PLAIN, Def.FONT_SIZE));
                    g.drawString(Def.WELL_DONE, Def.WELL_DONE_POSITION_X, Def.WELL_DONE_POSITION_Y);

                }

            }


            if (gameOver) {

//print MY_FIRST_GAME

                g.setColor(Color.GREEN);
                g.setFont(new Font("Font", Font.PLAIN, Def.FONT_SIZE));
                g.drawString(Def.MY_FIRST_GAME, Def.MY_FIRST_GAME_X , Def.MY_FIRST_GAME_Y);

//print gameOver

                g.setColor(Color.RED);
                g.setFont(new Font("Font", Font.PLAIN, Def.FONT_SIZE));
                g.drawString(Def.GAME_OVER, Def.DRAW_GAME_OVER_X, Def.DRAW_GAME_OVER_Y);

            }


//rectangle well done

            g.setColor(Color.GREEN);
            g.fillOval(Def.RECTANGLE_WELL_DONE_X,
                    Def.RECTANGLE_WELL_DONE_Y,
                    Def.RECTANGLE_WELL_DONE_WIDTH,
                    Def.RECTANGLE_WELL_DONE_HEIGHT);

//rectangle wall right

            g.setColor(Color.BLACK);
            g.fillRect(Def.GAME_WIDTH - Def.RECTANGLE_WIDTH,
                    Def.Y,
                    Def.RECTANGLE_WIDTH,
                    Def.RECTANGLE_LONG_HEIGHT );

//8
            g.setColor(Color.orange);
            g.fillRect(Def.X,
                    Def.AIR,
                    Def.RECTANGLE_WIDTH_PART8_1,
                    Def.RECTANGLE_HEIGHT);

            g.setColor(Color.black);
            g.fillRect(Def.RECTANGLE_WIDTH_PART8_1 + Def.RECTANGLE_HALL,
                    Def.AIR,
                    Def.RECTANGLE_WIDTH_PART8_2,
                    Def.RECTANGLE_HEIGHT);
//7
            g.setColor(Color.orange);
            g.fillRect(Def.X,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 2,
                    Def.RECTANGLE_WIDTH_PART7_1,
                    Def.RECTANGLE_HEIGHT);

            g.setColor(Color.black);
            g.fillRect(Def.RECTANGLE_WIDTH_PART7_1 + Def.RECTANGLE_HALL,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 2,
                    Def.RECTANGLE_WIDTH_PART7_2,
                    Def.RECTANGLE_HEIGHT);
//6
            g.setColor(Color.orange);
            g.fillRect(Def.X,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 3,
                    Def.RECTANGLE_WIDTH_PART6_1,
                    Def.RECTANGLE_HEIGHT);

            g.setColor(Color.black);
            g.fillRect(Def.RECTANGLE_WIDTH_PART6_1 + Def.RECTANGLE_HALL,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 3,
                    Def.RECTANGLE_WIDTH_PART6_2,
                    Def.RECTANGLE_HEIGHT);
//5
            g.setColor(Color.orange);
            g.fillRect(Def.X,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 4,
                    Def.RECTANGLE_WIDTH_PART5_1,
                    Def.RECTANGLE_HEIGHT);

            g.setColor(Color.black);
            g.fillRect(Def.RECTANGLE_WIDTH_PART5_1 + Def.RECTANGLE_HALL,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 4,
                    Def.RECTANGLE_WIDTH_PART5_2,
                    Def.RECTANGLE_HEIGHT);
//4
            g.setColor(Color.orange);
            g.fillRect(Def.X,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 5,
                    Def.RECTANGLE_WIDTH_PART4_1,
                    Def.RECTANGLE_HEIGHT);

//3
            g.setColor(Color.orange);
            g.fillRect(Def.X,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 6,
                    Def.RECTANGLE_WIDTH_PART3_1,
                    Def.RECTANGLE_HEIGHT);

            g.setColor(Color.black);
            g.fillRect(Def.RECTANGLE_WIDTH_PART3_1 + Def.RECTANGLE_HALL,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 6,
                    Def.RECTANGLE_WIDTH_PART3_2,
                    Def.RECTANGLE_HEIGHT);
//2
            g.setColor(Color.orange);
            g.fillRect(Def.X,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 7,
                    Def.RECTANGLE_WIDTH_PART2_1,
                    Def.RECTANGLE_HEIGHT);

            g.setColor(Color.black);
            g.fillRect(Def.RECTANGLE_WIDTH_PART2_1 + Def.RECTANGLE_HALL,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 7,
                    Def.RECTANGLE_WIDTH_PART2_2,
                    Def.RECTANGLE_HEIGHT);
//1
            g.setColor(Color.orange);
            g.fillRect(Def.X,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 8,
                    Def.RECTANGLE_WIDTH_PART1_1,
                    Def.RECTANGLE_HEIGHT);

            g.setColor(Color.black);
            g.fillRect(Def.RECTANGLE_WIDTH_PART1_1 + Def.RECTANGLE_HALL,
                    Def.RECTANGLE_HEIGHT + Def.AIR * 8,
                    Def.RECTANGLE_WIDTH_PART1_2,
                    Def.RECTANGLE_HEIGHT);



    }


    public void checkIfEndOfStage() {

        Rectangle obstacle = new Rectangle(Def.RECTANGLE_WELL_DONE_X,
                Def.RECTANGLE_WELL_DONE_Y,
                Def.RECTANGLE_WELL_DONE_WIDTH,
                Def.RECTANGLE_WELL_DONE_HEIGHT);
        if (obstacle.intersects(cube.getX(), cube.getY(), cube.getWidth(), cube.getHeight())) {
            this.wellDone = true;
        }



    }
    public void checkCollision () {

        Rectangle obstacle1 = new Rectangle(Def.X,
                Def.RECTANGLE_HEIGHT + Def.AIR * 8,
                Def.RECTANGLE_WIDTH_PART1_1,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle2 = new Rectangle(Def.RECTANGLE_WIDTH_PART1_1 + Def.RECTANGLE_HALL,
                Def.RECTANGLE_HEIGHT + Def.AIR * 8,
                Def.RECTANGLE_WIDTH_PART1_2,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle3 = new Rectangle(Def.X,
                Def.RECTANGLE_HEIGHT + Def.AIR * 7,
                Def.RECTANGLE_WIDTH_PART2_1,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle4 = new Rectangle(Def.RECTANGLE_WIDTH_PART2_1 + Def.RECTANGLE_HALL,
                Def.RECTANGLE_HEIGHT + Def.AIR * 7,
                Def.RECTANGLE_WIDTH_PART2_2,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle5 = new Rectangle(Def.X,
                Def.RECTANGLE_HEIGHT + Def.AIR * 6,
                Def.RECTANGLE_WIDTH_PART3_1,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle6 = new Rectangle(Def.RECTANGLE_WIDTH_PART3_1 + Def.RECTANGLE_HALL,
                Def.RECTANGLE_HEIGHT + Def.AIR * 6,
                Def.RECTANGLE_WIDTH_PART3_2,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle7 = new Rectangle(Def.X,
                Def.RECTANGLE_HEIGHT + Def.AIR * 5,
                Def.RECTANGLE_WIDTH_PART4_1,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle8 = new Rectangle(Def.X,
                Def.RECTANGLE_HEIGHT + Def.AIR * 4,
                Def.RECTANGLE_WIDTH_PART5_1,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle9 = new Rectangle(Def.RECTANGLE_WIDTH_PART5_1 + Def.RECTANGLE_HALL,
                Def.RECTANGLE_HEIGHT + Def.AIR * 4,
                Def.RECTANGLE_WIDTH_PART5_2,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle10 = new Rectangle(Def.X,
                Def.RECTANGLE_HEIGHT + Def.AIR * 3,
                Def.RECTANGLE_WIDTH_PART6_1,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle11 = new Rectangle(Def.RECTANGLE_WIDTH_PART6_1 + Def.RECTANGLE_HALL,
                Def.RECTANGLE_HEIGHT + Def.AIR * 3,
                Def.RECTANGLE_WIDTH_PART6_2,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle12 = new Rectangle(Def.X,
                Def.RECTANGLE_HEIGHT + Def.AIR * 2,
                Def.RECTANGLE_WIDTH_PART7_1,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle13 = new Rectangle(Def.RECTANGLE_WIDTH_PART7_1 + Def.RECTANGLE_HALL,
                Def.RECTANGLE_HEIGHT + Def.AIR * 2,
                Def.RECTANGLE_WIDTH_PART7_2,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle14 = new Rectangle(Def.X,
                Def.AIR,
                Def.RECTANGLE_WIDTH_PART8_1,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle15 = new Rectangle(Def.RECTANGLE_WIDTH_PART8_1 + Def.RECTANGLE_HALL,
                Def.AIR,
                Def.RECTANGLE_WIDTH_PART8_2,
                Def.RECTANGLE_HEIGHT);

        Rectangle obstacle16 = new Rectangle( Def.GAME_WIDTH - Def.RECTANGLE_WIDTH,
                Def.Y,
                Def.RECTANGLE_WIDTH,
                Def.RECTANGLE_LONG_HEIGHT);


        obstacles[0]  = obstacle1;
        obstacles[1]  = obstacle2;
        obstacles[2]  = obstacle3;
        obstacles[3]  = obstacle4;
        obstacles[4]  = obstacle5;
        obstacles[5]  = obstacle6;
        obstacles[6]  = obstacle7;
        obstacles[7]  = obstacle8;
        obstacles[8]  = obstacle9;
        obstacles[9]  = obstacle10;
        obstacles[10] = obstacle11;
        obstacles[11] = obstacle12;
        obstacles[12] = obstacle13;
        obstacles[13] = obstacle14;
        obstacles[14] = obstacle15;
        obstacles[15] = obstacle16;

        for (int i=0; i<obstacles.length; i++)
        if (obstacles[i].intersects(cube.getX(), cube.getY(), cube.getWidth(), cube.getHeight())) {
            this.gameOver = true;

        }

    }

}

