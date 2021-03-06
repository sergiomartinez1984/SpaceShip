package actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerShip extends Ship{

    public int lives;

    public PlayerShip(float xCentre, float yCentre, float width,
                      float height, float movementSpeed, int shield, float laserWidth,
                      float laserHeight, float laserMovementSpeed, float timeBetweenShot,
                      TextureRegion shipTextureRegion,
                      TextureRegion shieldTextureRegion,
                      TextureRegion laserTextureRegion) {
        super(xCentre, yCentre, width, height, movementSpeed, shield, laserWidth, laserHeight, laserMovementSpeed, timeBetweenShot, shipTextureRegion, shieldTextureRegion, laserTextureRegion);
        lives = 3;
    }

    @Override
    public Laser[] fireLaser() {
        Laser[]laser = new Laser[2];
        laser[0] = new Laser(boundingBox.x + boundingBox.width * 0.07f,boundingBox.y + boundingBox.height * 0.45f,
                laserWidth,laserHeight,
                laserMovementSpeed, laserTextureRegion);
        laser[1] = new Laser(boundingBox.x + boundingBox.width * 0.93f,boundingBox.y + boundingBox.height * 0.45f,
                laserWidth,laserHeight,
                laserMovementSpeed, laserTextureRegion);

        timeSinceLastShop = 0;

        return laser;
    }
}
