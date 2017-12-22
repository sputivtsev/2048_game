package game2048;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Sergey on 10.05.2017.
 */
public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private static final int WINNING_TILE=2048;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }

    public Tile[][] getGameTiles(){
       return model.getGameTiles();
    }

    public int getScore(){
        return model.score;
    }

    public void resetGame(){
        model.score=0;
        view.isGameLost=false;
        view.isGameWon=false;
        model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            resetGame();
        if(!model.canMove()) view.isGameLost=true;
        if (!view.isGameLost && !view.isGameWon){
            if(e.getKeyCode() == KeyEvent.VK_LEFT) model.left();
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) model.right();
            if(e.getKeyCode() == KeyEvent.VK_UP) model.up();
            if(e.getKeyCode() == KeyEvent.VK_DOWN) model.down();
            if(e.getKeyCode() == KeyEvent.VK_Z) model.rollback();//отмена хода
            if(e.getKeyCode() == KeyEvent.VK_R) model.randomMove();//рандомный ход
            if(e.getKeyCode() == KeyEvent.VK_A) model.autoMove();//автоход, наиболее выгодный
        }
        if(model.maxTile==WINNING_TILE) view.isGameWon=true;
        view.repaint();
    }

    public View getView() {
        return view;
    }
}
