package OOP.ThirdEx;

import java.util.Optional;

public class NoviceSet {
    private WritingChanceryItem[] forWrite;
    private BindingChanceryItem[] forBind;
    private WritingSurfaceChanceryItem[] forWriteOn;

    private int indexForWrite = 0;
    private int indexForBind = 0;
    private int indexForWriteOn = 0;

    /**
     * Checked parameters before create new NoviceSet and create it.
     * @param sizeForWrite array size for "WritingChancery" items
     * @param sizeForBind array size for "BindingChancery" items
     * @param sizeForWriteOn array size for "WritingSurfaceChancery" items
     * @return if one or more parameters are incorrect - return Optional<null>
     */
    public static Optional<NoviceSet> create(int sizeForWrite,
                                            int sizeForBind, int sizeForWriteOn) {
        if (sizeForWrite < 0 || sizeForBind < 0 || sizeForWriteOn < 0)
            return Optional.empty();
        return Optional.of(new NoviceSet(sizeForWrite, sizeForBind, sizeForWriteOn));
    }


    private NoviceSet(int sizeForWrite, int sizeForBind, int sizeForWriteOn) {
        forWrite = new WritingChanceryItem[sizeForWrite];
        forBind = new BindingChanceryItem[sizeForBind];
        forWriteOn = new WritingSurfaceChanceryItem[sizeForWriteOn];
    }

    /**
     * add item to set
     * @param item item will cast to concrete type or won't add
     */
    public void addItem(ChanceryItem item) {
        if (item instanceof BindingChanceryItem
                && indexForBind < forBind.length) {
            forBind[indexForBind] = (BindingChanceryItem) item;
            indexForBind++;
        } else if (item instanceof WritingChanceryItem
                && indexForWrite < forWrite.length) {
            forWrite[indexForWrite] = (WritingChanceryItem) item;
            indexForWrite++;
        } else if (item instanceof WritingSurfaceChanceryItem
                && indexForWriteOn < forWriteOn.length) {
            forWriteOn[indexForWriteOn] = (WritingSurfaceChanceryItem) item;
            indexForWriteOn++;
        }
    }

    public static void main(String[] args) {
        NoviceSet set = NoviceSet.create(1, 2, 3)
                .orElse(new NoviceSet(1, 1, 1));

        set.addItem(new Clip());
        set.addItem(new Clip());
        set.addItem(new Clip());


    }
}

