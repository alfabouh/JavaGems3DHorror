

package jgems_api.horror.resources;

import org.lwjgl.openal.AL10;
import javagems3d.engine.audio.sound.SoundBuffer;
import javagems3d.engine.system.resources.assets.loaders.base.IAssetsLoader;
import javagems3d.engine.system.resources.manager.GameResources;
import javagems3d.engine.system.service.path.JGemsPath;


public class HorrorSoundsLoader implements IAssetsLoader {
    public SoundBuffer[] slosh = new SoundBuffer[4];
    public SoundBuffer beer;
    public SoundBuffer gas;
    public SoundBuffer magic;

    public SoundBuffer noise;
    public SoundBuffer ghost;
    public SoundBuffer breath;

    public SoundBuffer en_steps;

    public SoundBuffer victory;
    public SoundBuffer meat;

    public SoundBuffer menu;

    @Override
    public void load(GameResources gameResources) {
        this.menu = gameResources.createSoundBuffer(new JGemsPath("/assets/horror/sounds/menu.ogg"), AL10.AL_FORMAT_STEREO16);
        this.victory = gameResources.createSoundBuffer(new JGemsPath("/assets/horror/sounds/victory.ogg"), AL10.AL_FORMAT_STEREO16);
        this.meat = gameResources.createSoundBuffer(new JGemsPath("/assets/horror/sounds/meat.ogg"), AL10.AL_FORMAT_STEREO16);

        this.breath = gameResources.createSoundBuffer(new JGemsPath("/assets/horror/sounds/breath.ogg"), AL10.AL_FORMAT_STEREO16);
        this.ghost = gameResources.createSoundBuffer(new JGemsPath("/assets/horror/sounds/ghost.ogg"), AL10.AL_FORMAT_MONO16);
        this.en_steps = gameResources.createSoundBuffer(new JGemsPath("/assets/horror/sounds/en_steps.ogg"), AL10.AL_FORMAT_MONO16);
        this.noise = gameResources.createSoundBuffer(new JGemsPath("/assets/horror/sounds/noise.ogg"), AL10.AL_FORMAT_STEREO16);

        this.magic = gameResources.createSoundBuffer(new JGemsPath("/assets/horror/sounds/magic.ogg"), AL10.AL_FORMAT_STEREO16);
        this.beer = gameResources.createSoundBuffer(new JGemsPath("/assets/horror/sounds/beer.ogg"), AL10.AL_FORMAT_STEREO16);
        this.gas = gameResources.createSoundBuffer(new JGemsPath("/assets/horror/sounds/gas.ogg"), AL10.AL_FORMAT_STEREO16);
        for (int i = 0; i < 4; i++) {
            this.slosh[i] = gameResources.createSoundBuffer(new JGemsPath("/assets/horror/sounds/pl_slosh" + (i + 1) + ".ogg"), AL10.AL_FORMAT_STEREO16);
        }
    }

    @Override
    public LoadMode loadMode() {
        return LoadMode.PARALLEL;
    }

    @Override
    public LoadPriority loadPriority() {
        return LoadPriority.LOW;
    }
}

