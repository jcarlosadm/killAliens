package br.com.killaliens.ship.enemy.factory;

import br.com.killaliens.ship.ShipProperties;
import br.com.killaliens.ship.ShipPropertiesBuilder;
import br.com.killaliens.ship.enemy.EnemyShip;
import br.com.killaliens.ship.enemy.types.EnemyTypes;
import br.com.killaliens.ship.enemy.types.ufo.UFO;
import br.com.killaliens.ship.enemy.types.ufo.UFOPropertiesBuilder;

public abstract class EnemyFactory {
    
    public static EnemyShip getEnemyInstance(EnemyTypes type){
        //TODO implement
        ShipPropertiesBuilder builder = null;
        ShipProperties properties = null;
        
        if (type.equals(EnemyTypes.UFO)) {
            builder = new UFOPropertiesBuilder();
            properties = builder.getShipProperties();
            return new UFO(properties);
        }
        
        return null;
    }
}
