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

package jgems_api.horror.gui;

import jgems_api.horror.HorrorGame;
import jgems_api.horror.HorrorGamePlayerState;
import org.joml.Vector2i;
import javagems3d.engine.graphics.opengl.rendering.imgui.ImmediateUI;
import javagems3d.engine.graphics.opengl.rendering.imgui.panels.base.PanelUI;
import javagems3d.engine.graphics.opengl.rendering.imgui.panels.default_panels.DefaultGamePanel;
import javagems3d.engine.graphics.opengl.rendering.imgui.panels.default_panels.DefaultPausePanel;
import javagems3d.engine.graphics.opengl.screen.window.Window;
import javagems3d.engine.system.resources.assets.loaders.TextureAssetsLoader;
import javagems3d.engine.system.resources.assets.material.samples.TextureSample;
import javagems3d.engine.system.resources.manager.JGemsResourceManager;

public class HorrorPausePanel extends DefaultPausePanel {
    public HorrorPausePanel(PanelUI prevPanel) {
        super(prevPanel);
    }

    protected void openGamePanel(ImmediateUI immediateUI) {
        immediateUI.setPanel(new HorrorGamePanel(null));
    }
}
