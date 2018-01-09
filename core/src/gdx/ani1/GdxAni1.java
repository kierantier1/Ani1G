package gdx.ani1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Input;

public class GdxAni1 extends ApplicationAdapter implements InputProcessor {

    SpriteBatch batch;
    Sprite sprVlad;
    Texture txSheet, txTemp, txOne;
    Animation araniVlad[];
    TextureRegion trTemp; // a single temporary texture region
    int fW, fH, fSx, fSy; // height and width of SpriteSheet image - and the starting upper coordinates on the Sprite Sheet
    int nFrame, nPos;
    int nX = 100, nY = 100;

    @Override
    public void create() {
        Gdx.input.setInputProcessor((this));
        nFrame = 0;
        nPos = 0; // the position in the SpriteSheet - 0 to 7
        araniVlad = new Animation[8];
        batch = new SpriteBatch();
        txSheet = new Texture("Vlad.png");
        fW = txSheet.getWidth() / 8;
        fH = txSheet.getHeight() / 8;
        System.out.println(fW + " " + fH);
        for (int i = 0; i < 8; i++) {
            Sprite[] arSprVlad = new Sprite[8];
            for (int j = 0; j < 8; j++) {
                fSx = j * fW;
                fSy = i * fH;
                sprVlad = new Sprite(txSheet, fSx, fSy, fW, fH);
                arSprVlad[j] = new Sprite(sprVlad);
            }
            araniVlad[i] = new Animation(5.2f, arSprVlad);

        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        nFrame++;
        if (nFrame > 7) {
            nFrame = 0;
        }
        System.out.println(nPos + " " + nFrame);
        
        trTemp = araniVlad[nPos].getKeyFrame(nFrame, true);
        /* Spritesheet Cheat Sheet
        * 0 - Facing East
        * 1 - Facing North
        * 2 - Facing North-East
        * 3 - Facing North-West
        * 4 - Facing South
        * 5 - Facing South-East
        * 6 - Facing South-West
        * 7 - Facing West
        */
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.UP)){
            nPos = 3;
        } if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            //nX = nX-=5;
            nPos = 7;
        } if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            //nX = nX+=5;
            nPos = 0;
        } if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            //nY = nY+=5;
            nPos = 1;
        } if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            //nY = nY-=5;
            nPos = 4;
        }
        
        batch.begin();
        batch.draw(trTemp, nX, nY);
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        //Keypad 0-9 is Keycode 96 to 105 in unicode, keycode 0 is 144
        System.out.println("keydown " + keycode);
        if (keycode - 144 < 8 && keycode - 144 > 0) {
            nPos = keycode - 144;
            System.out.println(nPos);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
