

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

import org.joml.Vector3f;
import javagems3d.engine.JGems3D;
import javagems3d.engine.graphics.opengl.rendering.fabric.objects.render.RenderEntity;
import javagems3d.engine.graphics.opengl.rendering.items.objects.EntityObject;
import javagems3d.engine.system.map.loaders.tbox.placers.TDefaultRenderContainer;
import javagems3d.engine.system.resources.assets.models.mesh.data.render.MeshRenderAttributes;
import javagems3d.engine.system.resources.manager.JGemsResourceManager;
import javagems3d.engine.system.service.path.JGemsPath;
import javagems3d.engine_api.app.JGemsTBoxApplication;
import javagems3d.engine_api.app.JGemsTBoxEntry;
import javagems3d.engine_api.app.tbox.ITBoxEntitiesObjectData;
import javagems3d.engine_api.app.tbox.TBoxEntitiesUserData;
import javagems3d.engine_api.app.tbox.containers.TObjectData;
import javagems3d.engine_api.app.tbox.containers.TUserData;
import javagems3d.toolbox.map_sys.save.objects.object_attributes.Attribute;
import javagems3d.toolbox.map_sys.save.objects.object_attributes.AttributeID;
import javagems3d.toolbox.map_sys.save.objects.object_attributes.AttributeTarget;
import javagems3d.toolbox.map_sys.save.objects.object_attributes.AttributesContainer;
import javagems3d.toolbox.map_table.object.ModeledObjectData;
import javagems3d.toolbox.map_table.object.ObjectCategory;
import javagems3d.toolbox.resources.TBoxResourceManager;

@JGemsTBoxEntry
public class HorrorTBoxApp implements JGemsTBoxApplication {
    public static String terrain_map = "/assets/horror/models/italy/italy.obj";
    public static ObjectCategory TERRAIN = new ObjectCategory("Terrains");
    public static ObjectCategory PHYSICS_OBJECT = new ObjectCategory("Entities");
    public static ObjectCategory PROP_OBJECT = new ObjectCategory("Props");
    public static ObjectCategory MARKER_OBJECT = new ObjectCategory("Markers");
    public static ObjectCategory LIQUID_OBJECT = new ObjectCategory("Liquids");

    @Override
    public void initEntitiesObjectData(TBoxResourceManager tBoxResourceManager, ITBoxEntitiesObjectData tBoxEntitiesObjectData) {
        Attribute<Vector3f> transformPosXYZ = new Attribute<>(AttributeTarget.POSITION_XYZ, AttributeID.POSITION_XYZ, new Vector3f(0.0f));
        Attribute<Vector3f> transformRotXYZ = new Attribute<>(AttributeTarget.ROTATION_XYZ, AttributeID.ROTATION_XYZ, new Vector3f(0.0f));
        Attribute<Vector3f> transformScaleXYZ = new Attribute<>(AttributeTarget.SCALING_XYZ, AttributeID.SCALING_XYZ, new Vector3f(1.0f));
        tBoxEntitiesObjectData.add("map_terrain", new TObjectData(new ModeledObjectData(new AttributesContainer(transformPosXYZ, transformRotXYZ, transformScaleXYZ), tBoxResourceManager.getShaderAssets().world_object, tBoxResourceManager.createModel(new JGemsPath(HorrorTBoxApp.terrain_map)), HorrorTBoxApp.TERRAIN)));
    }

    @Override
    public void initEntitiesUserData(JGemsResourceManager jGemsResourceManager, TBoxEntitiesUserData tBoxEntitiesUserData) {
        tBoxEntitiesUserData.add("map_terrain", new TUserData(new TDefaultRenderContainer(new RenderEntity(), EntityObject.class, new JGemsPath(JGems3D.Paths.SHADERS, "world/world_gbuffer"), new JGemsPath(HorrorTBoxApp.terrain_map), new MeshRenderAttributes().setAlphaDiscard(0.5f))));
    }
}