

package jgems_api.horror.resources;

import javagems3d.engine.JGems3D;
import javagems3d.engine.system.resources.assets.loaders.base.IAssetsLoader;
import javagems3d.engine.system.resources.assets.models.mesh.MeshDataGroup;
import javagems3d.engine.system.resources.manager.GameResources;
import javagems3d.engine.system.service.path.JGemsPath;


public class HorrorModelLoader implements IAssetsLoader {

    @Override
    public void load(GameResources gameResources) {
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

