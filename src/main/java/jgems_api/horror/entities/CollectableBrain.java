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

package jgems_api.horror.entities;

import jgems_api.horror.HorrorGamePlayerState;
import org.joml.Vector3f;
import javagems3d.engine.JGemsHelper;
import javagems3d.engine.audio.sound.data.SoundType;
import javagems3d.engine.physics.entities.collectabes.EntityCollectableItem;
import javagems3d.engine.physics.entities.player.Player;
import javagems3d.engine.physics.world.PhysicsWorld;
import javagems3d.engine.physics.world.triggers.ITriggerAction;
import javagems3d.engine.system.inventory.IInventoryOwner;
import javagems3d.engine.system.resources.manager.JGemsResourceManager;

public class CollectableBrain extends EntityCollectableItem {
    public CollectableBrain(PhysicsWorld world, Vector3f pos, String itemName) {
        super(world, null, pos, itemName);
    }

    protected ITriggerAction action() {
        return (e) -> {
            if (e instanceof IInventoryOwner) {
                if (e instanceof Player) {
                    HorrorGamePlayerState.brainsCollected += 1;
                    JGemsHelper.getSoundManager().playSoundAt(JGemsResourceManager.globalSoundAssets.pick, SoundType.WORLD_SOUND, 1.5f, 1.0f, 1.0f, this.getPosition());
                    JGemsHelper.getLogger().log("Collected brain!");
                    this.setDead();
                }
            }
        };
    }
}