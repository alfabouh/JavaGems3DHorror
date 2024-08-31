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

package jgems_api.horror.resources;

import jgems_api.horror.HorrorGame;
import jgems_api.horror.inventory.CommonItemHorror;
import jgems_api.horror.inventory.ZippoHorror;
import jgems_api.horror.items.ItemBeer;
import jgems_api.horror.items.ItemCross;
import jgems_api.horror.items.ItemZippoModded;
import jgems_api.horror.render.RenderHorrorPlayer;
import org.joml.Vector3f;
import javagems3d.engine.graphics.opengl.rendering.fabric.inventory.data.InventoryItemRenderData;
import javagems3d.engine.graphics.opengl.rendering.fabric.inventory.render.InventoryCommon;
import javagems3d.engine.graphics.opengl.rendering.fabric.objects.data.RenderEntityData;
import javagems3d.engine.graphics.opengl.rendering.fabric.objects.render.RenderEntity2D3D;
import javagems3d.engine.graphics.opengl.rendering.items.objects.PlayerSPObject;
import javagems3d.engine.graphics.opengl.rendering.items.objects.WorldEntity;
import javagems3d.engine.physics.world.basic.WorldItem;
import javagems3d.engine.system.resources.assets.loaders.base.IAssetsLoader;
import javagems3d.engine.system.resources.assets.material.Material;
import javagems3d.engine.system.resources.assets.models.helper.MeshHelper;
import javagems3d.engine.system.resources.assets.models.helper.constructor.IEntityModelConstructor;
import javagems3d.engine.system.resources.assets.models.mesh.MeshDataGroup;
import javagems3d.engine.system.resources.manager.GameResources;
import javagems3d.engine.system.resources.manager.JGemsResourceManager;


public class HorrorRenderDataLoader implements IAssetsLoader {
    public RenderEntityData player;
    public RenderEntityData gas_world;
    public RenderEntityData cross_world;
    public RenderEntityData brains_world;
    public RenderEntityData beer_world;

    public RenderEntityData ghost;

    @Override
    public void load(GameResources gameResources) {
        this.player = new RenderEntityData(new RenderHorrorPlayer(), PlayerSPObject.class, JGemsResourceManager.globalShaderAssets.world_gbuffer);

        IEntityModelConstructor<WorldItem> itemPickUpModelConstructor = e -> new MeshDataGroup(MeshHelper.generateSimplePlane3DMesh(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.5f, -0.5f, 0.0f), new Vector3f(-0.5f, 0.5f, 0.0f), new Vector3f(0.5f, 0.5f, 0.0f)));
        IEntityModelConstructor<WorldItem> enemyModelConstructor = e -> new MeshDataGroup(MeshHelper.generateSimplePlane3DMesh(new Vector3f(-1.0f, -1.0f, 0.0f), new Vector3f(1.0f, -1.0f, 0.0f), new Vector3f(-1.0f, 1.0f, 0.0f), new Vector3f(1.0f, 1.0f, 0.0f)));

        Material mat0 = new Material();
        mat0.setDiffuse(HorrorGame.get().horrorTexturesLoader.ghost);
        this.ghost = new RenderEntityData(new RenderEntity2D3D(), WorldEntity.class, JGemsResourceManager.globalShaderAssets.world_pickable);
        this.ghost.setEntityModelConstructor(enemyModelConstructor);
        this.ghost.getMeshRenderData().allowMoveMeshesIntoTransparencyPass(false).getRenderAttributes().setAlphaDiscard(0.6f).setShadowCaster(false).setRenderDistance(64.0f);
        this.ghost.getMeshRenderData().setOverlappingMaterial(mat0);

        Material mat1 = new Material();
        mat1.setDiffuse(HorrorGame.get().horrorTexturesLoader.gas);
        this.gas_world = new RenderEntityData(new RenderEntity2D3D(), WorldEntity.class, JGemsResourceManager.globalShaderAssets.world_pickable);
        this.gas_world.setEntityModelConstructor(itemPickUpModelConstructor);
        this.gas_world.getMeshRenderData().allowMoveMeshesIntoTransparencyPass(false).getRenderAttributes().setAlphaDiscard(0.6f).setShadowCaster(false).setRenderDistance(64.0f);
        this.gas_world.getMeshRenderData().setOverlappingMaterial(mat1);

        Material mat2 = new Material();
        mat2.setDiffuse(HorrorGame.get().horrorTexturesLoader.cross);
        this.cross_world = new RenderEntityData(new RenderEntity2D3D(), WorldEntity.class, JGemsResourceManager.globalShaderAssets.world_pickable);
        this.cross_world.setEntityModelConstructor(itemPickUpModelConstructor);
        this.cross_world.getMeshRenderData().allowMoveMeshesIntoTransparencyPass(false).getRenderAttributes().setAlphaDiscard(0.6f).setShadowCaster(false).setRenderDistance(64.0f);
        this.cross_world.getMeshRenderData().setOverlappingMaterial(mat2);

        Material mat3 = new Material();
        mat3.setDiffuse(HorrorGame.get().horrorTexturesLoader.brains);
        this.brains_world = new RenderEntityData(new RenderEntity2D3D(), WorldEntity.class, JGemsResourceManager.globalShaderAssets.world_pickable);
        this.brains_world.setEntityModelConstructor(itemPickUpModelConstructor);
        this.brains_world.getMeshRenderData().allowMoveMeshesIntoTransparencyPass(false).getRenderAttributes().setAlphaDiscard(0.6f).setShadowCaster(false).setRenderDistance(64.0f);
        this.brains_world.getMeshRenderData().setOverlappingMaterial(mat3);

        Material mat4 = new Material();
        mat4.setDiffuse(HorrorGame.get().horrorTexturesLoader.beer);
        this.beer_world = new RenderEntityData(new RenderEntity2D3D(), WorldEntity.class, JGemsResourceManager.globalShaderAssets.world_pickable);
        this.beer_world.setEntityModelConstructor(itemPickUpModelConstructor);
        this.beer_world.getMeshRenderData().allowMoveMeshesIntoTransparencyPass(false).getRenderAttributes().setAlphaDiscard(0.6f).setShadowCaster(false).setRenderDistance(64.0f);
        this.beer_world.getMeshRenderData().setOverlappingMaterial(mat4);

        JGemsResourceManager.addInventoryItemRenderer(ItemZippoModded.class, new InventoryItemRenderData(JGemsResourceManager.globalShaderAssets.inventory_common_item, new ZippoHorror(), JGemsResourceManager.globalTextureAssets.zippo1));
        JGemsResourceManager.addInventoryItemRenderer(ItemCross.class, new InventoryItemRenderData(JGemsResourceManager.globalShaderAssets.inventory_common_item, new CommonItemHorror(HorrorGame.get().horrorTexturesLoader.cross), HorrorGame.get().horrorTexturesLoader.cross));
        JGemsResourceManager.addInventoryItemRenderer(ItemBeer.class, new InventoryItemRenderData(JGemsResourceManager.globalShaderAssets.inventory_common_item, new CommonItemHorror(HorrorGame.get().horrorTexturesLoader.beer), HorrorGame.get().horrorTexturesLoader.beer));
    }

    @Override
    public LoadMode loadMode() {
        return LoadMode.NORMAL;
    }

    @Override
    public LoadPriority loadPriority() {
        return LoadPriority.LOW;
    }
}

