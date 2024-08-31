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

import jgems_api.horror.events.HorrorEvents;
import jgems_api.horror.resources.*;
import org.jetbrains.annotations.NotNull;
import javagems3d.engine.JGemsHelper;
import javagems3d.engine.audio.sound.data.SoundType;
import javagems3d.engine.graphics.opengl.rendering.JGemsDebugGlobalConstants;
import javagems3d.engine.system.core.EngineSystem;
import javagems3d.engine.system.service.path.JGemsPath;
import javagems3d.engine_api.app.JGemsGameApplication;
import javagems3d.engine_api.app.JGemsGameEntry;
import javagems3d.engine_api.app.JGemsGameInstance;
import javagems3d.engine_api.configuration.AppConfiguration;
import javagems3d.engine_api.events.IAppEventSubscriber;
import javagems3d.engine_api.manager.AppManager;
import javagems3d.engine_api.resources.IAppResourceLoader;
import jgems_api.horror.manager.HorrorAppManager;

@JGemsGameEntry(gameTitle = "An Episode Of One Scary Horror", gameVersion = "0.1a", devStage = JGemsGameEntry.DevStage.PRE_ALPHA)
public class HorrorGame implements JGemsGameApplication {
    @JGemsGameInstance
    private static HorrorGame horrorGame;

    public static boolean POST_PROCESSING = true;

    public HorrorModelLoader horrorModelLoader;
    public HorrorRenderDataLoader horrorRenderDataLoader;
    public HorrorShaderLoader horrorShaderLoader;
    public HorrorSoundsLoader horrorSoundsLoader;
    public HorrorTexturesLoader horrorTexturesLoader;

    public HorrorGame() {
        this.horrorModelLoader = new HorrorModelLoader();
        this.horrorShaderLoader = new HorrorShaderLoader();
        this.horrorRenderDataLoader = new HorrorRenderDataLoader();
        this.horrorSoundsLoader = new HorrorSoundsLoader();
        this.horrorTexturesLoader = new HorrorTexturesLoader();
    }

    @Override
    public void loadResources(IAppResourceLoader appResourceLoader) {
        appResourceLoader.addAssetsLoader(this.horrorModelLoader);
        appResourceLoader.addAssetsLoader(this.horrorRenderDataLoader);
        appResourceLoader.addAssetsLoader(this.horrorSoundsLoader);
        appResourceLoader.addAssetsLoader(this.horrorTexturesLoader);
        appResourceLoader.addShadersLoader(this.horrorShaderLoader);
    }

    @Override
    public void subscribeEvents(IAppEventSubscriber appEventSubscriber) {
        appEventSubscriber.addClassWithEvents(HorrorEvents.class);
    }

    @Override
    public void preInitEvent(EngineSystem engineSystem) {
        JGemsHelper.LOCALISATION.createLocalisation("English", new JGemsPath("/assets/horror/lang/"));
        JGemsHelper.LOCALISATION.createLocalisation("Russian", new JGemsPath("/assets/horror/lang/"));

        JGemsDebugGlobalConstants.PATH_GEN_GRAPH_GAP = 0.6f;
    }

    @Override
    public void postInitEvent(EngineSystem engineSystem) {
        HorrorGamePlayerState.noiseSound = JGemsHelper.getSoundManager().createSound(HorrorGame.get().horrorSoundsLoader.noise, SoundType.BACKGROUND_LOOP_SOUND, 2.0f, 0.5f, 1.0f);
        HorrorGamePlayerState.breathSound = JGemsHelper.getSoundManager().createSound(HorrorGame.get().horrorSoundsLoader.breath, SoundType.BACKGROUND_SOUND, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public @NotNull AppManager createAppManager() {
        return new HorrorAppManager(AppConfiguration.createDefaultAppConfiguration(new HorrorGameMapLoader()));
    }

    public static HorrorGame get() {
        return HorrorGame.horrorGame;
    }
}
