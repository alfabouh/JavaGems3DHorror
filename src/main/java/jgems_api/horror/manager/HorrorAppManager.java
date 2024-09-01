

package jgems_api.horror.manager;

import jgems_api.horror.HorrorGame;
import jgems_api.horror.entities.HorrorSimplePlayer;
import jgems_api.horror.items.ItemCross;
import jgems_api.horror.items.ItemZippoModded;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import javagems3d.engine.JGemsHelper;
import javagems3d.engine.graphics.opengl.rendering.imgui.panels.base.PanelUI;
import javagems3d.engine.graphics.opengl.world.SceneWorld;
import javagems3d.engine.physics.entities.collectabes.EntityCollectableItem;
import javagems3d.engine.physics.world.PhysicsWorld;
import javagems3d.engine.system.controller.binding.BindingManager;
import javagems3d.engine.system.core.player.IPlayerConstructor;
import javagems3d.engine.system.map.loaders.IMapLoader;
import javagems3d.engine.system.map.loaders.tbox.placers.TBoxMapDefaultObjectsPlacer;
import javagems3d.engine.system.resources.manager.GameResources;
import javagems3d.engine.system.resources.manager.JGemsResourceManager;
import javagems3d.engine.system.service.collections.Pair;
import javagems3d.engine_api.app.tbox.containers.TUserData;
import javagems3d.engine_api.configuration.AppConfiguration;
import javagems3d.engine_api.manager.AppManager;
import javagems3d.toolbox.map_sys.save.objects.object_attributes.AttributeID;
import javagems3d.toolbox.map_sys.save.objects.object_attributes.AttributesContainer;
import jgems_api.horror.gui.HorrorMainMenuPanel;
import jgems_api.horror.manager.bindings.HorrorBindings;

public class HorrorAppManager extends AppManager {
    public HorrorAppManager(@Nullable AppConfiguration appConfiguration) {
        super(appConfiguration);
    }

    @Override
    public @NotNull BindingManager createBindingManager() {
        return new HorrorBindings();
    }

    @Override
    public @NotNull IPlayerConstructor createPlayer(IMapLoader mapLoader) {
        return (w, p, r) -> new Pair<>(new HorrorSimplePlayer(w, p, r), HorrorGame.get().horrorRenderDataLoader.player);
    }

    public @NotNull PanelUI gameMainMenuPanel() {
        return new HorrorMainMenuPanel(null);
    }
}