package OOP.ThirdEx;

public class Clip extends BindingChanceryItem {
    private WritingSurfaceChanceryItem bindSurface;

    public WritingSurfaceChanceryItem getBindSurface() {
        return bindSurface;
    }

    @Override
    public void bind(WritingSurfaceChanceryItem surface) {
        if (bindSurface != null) {
            new Pen(0x0).write(bindSurface.getOwner(), bindSurface);
            if (surface instanceof Paper)
                ((Paper) surface).makeCrumple();
        }
        this.bindSurface = surface;
    }
}
