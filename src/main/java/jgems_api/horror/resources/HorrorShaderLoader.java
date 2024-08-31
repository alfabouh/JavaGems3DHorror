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

import javagems3d.engine.JGems3D;
import javagems3d.engine.graphics.opengl.rendering.JGemsSceneGlobalConstants;
import javagems3d.engine.system.resources.assets.loaders.base.IAssetsLoader;
import javagems3d.engine.system.resources.assets.loaders.base.ShadersLoader;
import javagems3d.engine.system.resources.assets.shaders.RenderPass;
import javagems3d.engine.system.resources.assets.shaders.ShaderContainer;
import javagems3d.engine.system.resources.assets.shaders.manager.JGemsShaderManager;
import javagems3d.engine.system.resources.cache.ResourceCache;
import javagems3d.engine.system.resources.manager.GameResources;
import javagems3d.engine.system.service.path.JGemsPath;


public class HorrorShaderLoader extends ShadersLoader<JGemsShaderManager> {

    public JGemsShaderManager post_sh1;

    protected void initObjects(ResourceCache resourceCache) {
        this.post_sh1 = this.createShaderManager(resourceCache, new JGemsPath("/assets/horror/shaders/post_sh1"));
    }

    @Override
    protected JGemsShaderManager createShaderObject(JGemsPath shaderPath) {
        return new JGemsShaderManager(new ShaderContainer(shaderPath));
    }
}

