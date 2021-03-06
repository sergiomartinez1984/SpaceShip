package actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

abstract public class Ship {

    //caracteristicas de la nave
    //utilizamos medidas del mundo * segundo,en metros,asi no tenemos que preocuparnos por el tamaño de la pantalla para los calculos
    //tambien utilizo una varieable int escudo para saber cuanta cantidad de escudo le  queda a la nave

    public float movementSpeed;
    public int shield;

    //posicion y dimension de la nave
    //ancho y alto de la nave, y la posicion de la nave con las coordenadas X e Y
    public Rectangle boundingBox;

    //informacion del Laser
    float laserWidth, laserHeight;
    float laserMovementSpeed;
    float timeBetweenShot;
    float timeSinceLastShop = 0;


    //graficos
    //necesitamos dos regiones de textura,una para la nave y otra para el escudo
    TextureRegion shipTextureRegion, shieldTextureRegion, laserTextureRegion;

    public Ship(float xCentre, float yCentre,
                float width, float height,
                float movementSpeed, int shield,
                float laserWidth,float laserHeight,float laserMovementSpeed,
                float timeBetweenShot,
                TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion, TextureRegion laserTextureRegion) {
        this.movementSpeed = movementSpeed;
        this.shield = shield;

        this.boundingBox = new Rectangle(xCentre - width/2,yCentre - height/2,width,height);
        this.laserWidth = laserWidth;
        this.laserHeight = laserHeight;
        this.laserMovementSpeed = laserMovementSpeed;
        this.timeBetweenShot = timeBetweenShot;
        this.shipTextureRegion = shipTextureRegion;
        this.shieldTextureRegion = shieldTextureRegion;
        this.laserTextureRegion = laserTextureRegion;
    }

    public void update(float deltaTime){
        timeSinceLastShop += deltaTime;
    }

    public boolean canFireLaser(){
      return  (timeSinceLastShop - timeBetweenShot >=0);
    }

    public abstract Laser[]fireLaser();

    public Boolean intersects(Rectangle otherRectangle){

        return boundingBox.overlaps(otherRectangle);
    }

    //metodo que se encarga de verificar si un laser golpeo a la nave y ya no le queda escudo,
    // la nave es desturida y por lo tanto salta la animacion de explosion
    //este metodo al estar en la clase nave,sirve tanto par la clase nave jugador como para las naves enemigas,que son subclases
    public boolean hitDetectAndDestroy(Laser laser){
        if (shield > 0){
            shield --;
            return false;
        }
        return true;
    }

    public void translate(float xChange,float yChange){
        boundingBox.setPosition(boundingBox.x + xChange,boundingBox.y + yChange);
    }

    public void draw(Batch batch){
        batch.draw(shipTextureRegion,boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
        if (shield > 0){
            batch.draw(shieldTextureRegion,boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
        }
    }

}
