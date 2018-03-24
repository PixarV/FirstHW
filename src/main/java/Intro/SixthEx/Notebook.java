package Intro.SixthEx;

/**
 * @author ritt
 */
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

        /**
         * Add one note, that contains specified text
         * @param text - content for new note
         */
        public void addNote(String text) {
                if (indexForAddNote < capacity) {
                        add(text, indexForAddNote);
                } else {
                        System.out.printf("Add note \"%s\" is impossible.\n", text);
                }
        }

        /**
         * Add one note, that contains specified text
         * @param text - content for new note
         * @param index - position in the notebook for new note
         */
        public void addNote(String text, int index) {
                if (indexForAddNote < capacity && index < capacity && index >= 0) {
                        System.arraycopy(notes, index, notes, index+1, capacity-index-1);
                        add(text, index);
                } else {
                        System.out.printf("Add note \"%s\" is impossible.\n", text);
                }
        }

        /**
         * Delete one last note in the notebook
         */
        public void deleteLast() {
                if (indexForAddNote == 0) {
                        System.out.println("Notebook is empty.");
                        return;
                }
                indexForAddNote--;
        }

        // remove first occasion

        /**
         * Delete first note that contains same string like your's
         * @param text - note's content
         */
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

        /**
         * Delete note that located by specified index
         * @param index - note's index, starts with 0
         */
        public void deleteNote(int index) {
                if (index < capacity && index >= 0) {
                        del(index);
                        return;
                }
                System.out.printf("Note[%d] doesn't found.\n", index);
        }

        /**
         * Edit note's content in notebook that located by specified index
         * @param newText - new content for the note
         * @param index  - note's index, start with 0
         */
        public void edit(String newText, int index) {
                if (index < capacity && index >= 0) {
                        notes[index].setText(newText);
                        return;
                }
                System.out.printf("Note[%d] doesn't found.\n", index);
        }

        /**
         * Show all notes from the notebook
         */
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

        /**
         * private local function for 'add'
         * @param text - note's content
         * @param index - note's index
         */
        private void add(String text, int index) {
                notes[index] = new Note(text);
                indexForAddNote++;
        }

        /**
         *  private local function for 'delete'
         * @param index - note's index
         */
        private void del(int index) {
                System.arraycopy(notes, index + 1, notes, index, capacity-index-1);
                indexForAddNote--;
        }
}
