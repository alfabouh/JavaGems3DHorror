

package jgems_api.horror.events;

import imgui.ImGui;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiWindowFlags;
import jgems_api.horror.HorrorGame;
import jgems_api.horror.HorrorGamePlayerState;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL30;
import javagems3d.engine.JGemsHelper;
import javagems3d.engine.graphics.opengl.rendering.JGemsSceneUtils;
import javagems3d.engine.system.resources.assets.models.Model;
import javagems3d.engine.system.resources.assets.models.formats.Format2D;
import javagems3d.engine.system.resources.assets.models.helper.MeshHelper;
import javagems3d.engine.system.resources.assets.shaders.UniformString;
import javagems3d.engine_api.events.SubscribeEvent;
import javagems3d.engine_api.events.bus.Events;

public class HorrorEvents {
    @SubscribeEvent
    public static void postProcess(Events.RenderPostProcessing event) {
        if (HorrorGame.POST_PROCESSING) {
            event.setCancelled(true);
            try (Model<Format2D> screenModel = MeshHelper.generatePlane2DModelInverted(new Vector2f(0.0f), new Vector2f(event.windowSize), 0.5f)) {
                HorrorGame.get().horrorShaderLoader.post_sh1.bind();
                HorrorGame.get().horrorShaderLoader.post_sh1.getUtils().performOrthographicMatrix(screenModel);
                HorrorGame.get().horrorShaderLoader.post_sh1.performUniform(new UniformString("panic"), HorrorGamePlayerState.madness);
                HorrorGame.get().horrorShaderLoader.post_sh1.performUniformTexture(new UniformString("texture_sampler"), event.sceneBufferTextureID, GL30.GL_TEXTURE_2D);
                HorrorGame.get().horrorShaderLoader.post_sh1.performUniform(new UniformString("won"), HorrorGamePlayerState.won);
                HorrorGame.get().horrorShaderLoader.post_sh1.performUniform(new UniformString("lose"), HorrorGamePlayerState.lose);
                JGemsSceneUtils.renderModel(screenModel, GL30.GL_TRIANGLES);
                HorrorGame.get().horrorShaderLoader.post_sh1.unBind();
            }
        }
    }

    @SubscribeEvent
    public static void worldEvent(Events.PhysWorldTickPre event) {
        if (HorrorGamePlayerState.won || HorrorGamePlayerState.lose) {
            if (HorrorGamePlayerState.closeTick++ > 240) {
                JGemsHelper.GAME.destroyMap();
            }
        }
    }

    @SubscribeEvent
    public static void imgui(Events.DearIMGUIRender event) {
        float logX = (float) event.windowSize.x / 3;

        ImGui.setNextWindowPos(logX, 0, ImGuiCond.Once);
        ImGui.setNextWindowSize(200, 200, ImGuiCond.Once);
        ImGui.begin("HorrorGame Debug");
        if (ImGui.checkbox("PostProcessing", HorrorGame.POST_PROCESSING)) {
            HorrorGame.POST_PROCESSING = !HorrorGame.POST_PROCESSING;
        }
        ImGui.end();
    }
}
