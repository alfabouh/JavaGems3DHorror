/*
 * *
 *  * @author alfabouh
 *  * @since 2024
 *  * @link https://github.com/alfabouh/JavaGems3D
 *  *
 *  * This software is provided 'as-is', without any express or implied warranty.
 *  * In no event will the authors be held liable for any damages arising from the use of this software.
 *
 */

package jgems_api.horror;

import jgems_api.horror.entities.CollectableBrain;
import jgems_api.horror.entities.CollectableGas;
import jgems_api.horror.entities.GhostEnemy;
import jgems_api.horror.items.ItemBeer;
import jgems_api.horror.items.ItemCross;
import jgems_api.horror.items.ItemZippoModded;
import org.joml.Vector3f;
import javagems3d.engine.JGemsHelper;
import javagems3d.engine.graphics.opengl.world.SceneWorld;
import javagems3d.engine.physics.entities.collectabes.EntityCollectableItem;
import javagems3d.engine.physics.world.PhysicsWorld;
import javagems3d.engine.system.map.loaders.tbox.placers.TBoxMapDefaultObjectsPlacer;
import javagems3d.engine.system.resources.manager.GameResources;
import javagems3d.engine.system.resources.manager.JGemsResourceManager;
import javagems3d.engine_api.app.tbox.containers.TUserData;
import javagems3d.engine_api.manager.ITBoxMapLoaderManager;
import javagems3d.toolbox.map_sys.save.objects.object_attributes.AttributeID;
import javagems3d.toolbox.map_sys.save.objects.object_attributes.AttributesContainer;

import java.util.*;

public class HorrorGameMapLoader implements ITBoxMapLoaderManager {
    private final List<Vector3f> spawnPoints;
    private GhostEnemy ghostEnemy;

    public HorrorGameMapLoader() {
        this.spawnPoints = new ArrayList<>();
    }

    @Override
    public void placeTBoxEntityOnMap(SceneWorld sceneWorld, PhysicsWorld physicsWorld, GameResources globalGameResources, GameResources localGameResources, String id, AttributesContainer attributesContainer, TUserData userData) {
        TBoxMapDefaultObjectsPlacer.placeTBoxEntityOnMap(sceneWorld, physicsWorld, globalGameResources, localGameResources, id, attributesContainer, userData);
    }

    @Override
    public void placeTBoxTriggerZoneOnMap(PhysicsWorld physicsWorld, Vector3f position, Vector3f size, String id, AttributesContainer attributesContainer, TUserData userData) {
        TBoxMapDefaultObjectsPlacer.placeTBoxTriggerZoneOnMap(physicsWorld, position, size, id, attributesContainer, userData);
    }

    @Override
    public void handleTBoxMarker(SceneWorld sceneWorld, PhysicsWorld physicsWorld, GameResources globalGameResources, GameResources localGameResources, String id, AttributesContainer attributesContainer, TUserData userData) {
        String name = attributesContainer.getValueFromAttributeByID(AttributeID.NAME, String.class);
        Vector3f pos = attributesContainer.getValueFromAttributeByID(AttributeID.POSITION_XYZ, Vector3f.class);
        if (name.equals("zippo_item")) {
            JGemsHelper.WORLD.addItemInWorld(new EntityCollectableItem(physicsWorld, new ItemZippoModded(), new Vector3f(pos).add(0.0f, 0.5f, 0.0f), name), JGemsResourceManager.globalRenderDataAssets.zippo_world);
        }
        if (name.equals("spawnp")) {
            this.spawnPoints.add(pos);
        }
        if (name.equals("en_spawn")) {
            this.ghostEnemy = new GhostEnemy(physicsWorld, new Vector3f(pos), name);
            JGemsHelper.WORLD.addItemInWorld(this.ghostEnemy, HorrorGame.get().horrorRenderDataLoader.ghost);
        }
    }

    @Override
    public void mapPreLoad(PhysicsWorld physicsWorld, SceneWorld sceneWorld) {
        HorrorGamePlayerState.reset();
        this.spawnPoints.clear();
    }

    @Override
    public void mapPostLoad(PhysicsWorld physicsWorld, SceneWorld sceneWorld) {
        Collections.shuffle(this.spawnPoints);

        for (int i = 0; i < HorrorGamePlayerState.MAX_BRAINS; i++) {
            Optional<Vector3f> posOptional = this.spawnPoints.stream().findAny();
            if (posOptional.isPresent()) {
                Vector3f vector3f1 = posOptional.get();
                JGemsHelper.WORLD.addItemInWorld(new CollectableBrain(physicsWorld, new Vector3f(vector3f1).add(0.0f, 0.5f, 0.0f), "brains"), HorrorGame.get().horrorRenderDataLoader.brains_world);
                this.spawnPoints.remove(vector3f1);
            }
        }

        for (int i = 0; i < 7; i++) {
            Optional<Vector3f> posOptional = this.spawnPoints.stream().findAny();
            if (posOptional.isPresent()) {
                Vector3f vector3f1 = posOptional.get();
                JGemsHelper.WORLD.addItemInWorld(new CollectableGas(physicsWorld, new Vector3f(vector3f1).add(0.0f, 0.5f, 0.0f), "gas"), HorrorGame.get().horrorRenderDataLoader.gas_world);
                this.spawnPoints.remove(vector3f1);
            }
        }

        for (int i = 0; i < 2; i++) {
            Optional<Vector3f> posOptional = this.spawnPoints.stream().findAny();
            if (posOptional.isPresent()) {
                Vector3f vector3f1 = posOptional.get();
                JGemsHelper.WORLD.addItemInWorld(new EntityCollectableItem(physicsWorld, new ItemBeer(), new Vector3f(vector3f1).add(0.0f, 0.5f, 0.0f), "beer"), HorrorGame.get().horrorRenderDataLoader.beer_world);
                this.spawnPoints.remove(vector3f1);
            }
        }

        Optional<Vector3f> posOptional = this.spawnPoints.stream().findAny();
        if (posOptional.isPresent()) {
            Vector3f vector3f1 = posOptional.get();
            JGemsHelper.WORLD.addItemInWorld(new EntityCollectableItem(physicsWorld, new ItemCross(), new Vector3f(vector3f1).add(0.0f, 0.5f, 0.0f), "cross"), HorrorGame.get().horrorRenderDataLoader.cross_world);
            this.spawnPoints.remove(vector3f1);
        }

        this.ghostEnemy.getAi().setFollow(JGemsHelper.getCurrentPlayer());
    }
}
