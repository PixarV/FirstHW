package IO.FourthEx;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.*;
import java.util.ArrayList;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
public class Film implements Serializable {
    String title;
    String mainActor;
    static File file;

    static {
        file = new File("filmData");
        deserialize();
    }

    @SneakyThrows
    public static void serialize(ArrayList<Film> films) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(films);
        }
    }

    @SneakyThrows
    public static ArrayList<Film> deserialize() {
        if (!file.exists())
            return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Film>) ois.readObject();
        }
    }

    public static void methodThatMustBeCalledBeforeThisApplicationCloses(ArrayList<Film> films) {
        serialize(films);
    }
}


