package SixthEx;

public class Notebook {
        private int capacity;
        private Note[] notes;
        private int indexForAddNote;

        public Notebook(int capacity) {
                this.capacity = capacity;
                notes = new Note[capacity];
                indexForAddNote = 0;
        }

        public int getCapacity() {
                return capacity;
        }

        public int getIndexForAddNote() {
                return indexForAddNote;
        }

        public void addNote(String text) {
                if (indexForAddNote < capacity) {
                        add(text, indexForAddNote);
                } else {
                        System.out.printf("Add note \"%s\" is impossible.\n", text);
                }
        }

        public void addNote(String text, int index) {
                if (indexForAddNote < capacity && index < capacity && index >= 0) {
                        System.arraycopy(notes, index, notes, index+1, capacity-index-1);
                        add(text, index);
                } else {
                        System.out.printf("Add note \"%s\" is impossible.\n", text);
                }
        }

        public void deleteLast() {
                if (indexForAddNote == 0) {
                        System.out.println("Notebook is empty.");
                        return;
                }
                indexForAddNote--;
        }

        // remove first occasion
        public void deleteNote(String text) {
                for (int i = 0; i < indexForAddNote; i++) {
                        Note temp = notes[i];
                        if (temp.getText().equals(text)) {
                                del(i);
                                return;
                        }
                }
                 System.out.printf("Note \"%s\" doesn't found.\n", text);
        }

        public void deleteNote(int index) {
                if (index < capacity && index >= 0) {
                        del(index);
                        return;
                }
                System.out.printf("Note[%d] doesn't found.\n", index);
        }

        public void edit(String newText, int index) {
                if (index < capacity && index >= 0) {
                        notes[index].setText(newText);
                        return;
                }
                System.out.printf("Note[%d] doesn't found.\n", index);
        }

        public void show() {
                int i=0;
                for (; i < indexForAddNote; i++) {
                        System.out.printf("#%d: \"%s\"\n", i+1, notes[i].getText());
                }
                System.out.printf("Free notes: %d\n", capacity - i );
        }


        public static void main(String... args) {
                // Notebook notebook = new Notebook(5);
        }

        private void add(String text, int index) {
                notes[index] = new Note(text);
                indexForAddNote++;
        }

        private void del(int index) {
                System.arraycopy(notes, index + 1, notes, index, capacity-index-1);
                indexForAddNote--;
        }
}
