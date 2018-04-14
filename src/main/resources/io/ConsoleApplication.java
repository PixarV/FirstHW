import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Collectors;
import com.google.gson.*;

/**
 * Реализует набор статических методов для управления коллекцией (списком пакетов, хранящихся в выбранном устройстве) в интерактивном режиме.
 * @author ritt
 */
public class ConsoleApplication {
    private ConsoleApplication(){}

    enum Operations {
        LOAD,
        ADD_PACKET,
        UPDATE,
        SHOW_DEVICES,
        SHOW_PACKETS,
        CHOOSE_DEVICE,
        REMOVE_FIRST,
        REMOVE_LAST,
        REMOVE_LOWER,
        SORT,
        INFO
    }

    /**
     * Загрузка данных для ракеты из файла Examples/filename.
     * @param filename  имя файла (например: first.csv)
     * @param rocket ссылка на ракету, для которой загружаются данные.
     */
    public static void load(String filename, Rocket rocket) {
        ArrayList<String> lines = IOOperations.read(filename);
        int numberOfPackets = lines.size();

        if (numberOfPackets == 0) {
            System.out.println("Packets weren't updated.");
            return;
        }
        rocket.clean(); // очень плохая функция

        while (lines.size() != 0) {
            int index = new Random().nextInt(lines.size());
            String line = lines.get(index);
            String[] packetDescription = line.split(",");

            try {
                addPacket(packetDescription , rocket);
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e)  {
                System.out.printf("Error. Line %s is incorrect.\n", line);
                numberOfPackets--;
            }
            lines.remove(index);
        }
        System.out.printf("%d packets were added. Data in the rocket was updated. \n", numberOfPackets);
    }

    private static void addPacket(String[] mass, Rocket rocket) {
        Part part = rocket.getPart(Part.NamePart.valueOf(mass[0].trim()));
        Section section = part.getSection(Section.NameSection.valueOf(mass[1].trim()));
        Packet packet = new Packet(Packet.NamePacket.valueOf(mass[3].trim()), Integer.valueOf(mass[4].trim()));
        int deviceIndex = Integer.valueOf(mass[2].trim());
        section.addPacketToDevice(deviceIndex, packet);
    }

    /**
     * Добавление пакета
     * @param device устройство, в которое пакет будет добавлен
     * @param jsonPacket пакет в формате json
     */
    public static void addPacket(Device device, String jsonPacket) {
        Packet packet = fromJSONToPacket(jsonPacket);
        System.out.printf("Добавлен пакет типа %s, заполненный на %d ед.\n", packet.getName(), packet.getFullness());
        device.addContentToLab5LinkedList(packet);
    }

    /**
     * выгрузка данных ракеты в файл examples/filename.
     * @param filename  имя файла (например: first.csv)
     * @param rocket ссылка на ракету, данные которой загружаются..
     */
    public static void update(String filename, Rocket rocket) {
        ArrayList<String> lines = new InformationAboutRocket(rocket).getInformation();
        if (IOOperations.cleanFile(filename)) {
            IOOperations.write(filename, lines);
            System.out.printf("Data was updated and wrote to file %s.\n", filename);
        }
    }

    /**
     * Выбор нужного устройства в конкретной части ракеты, в конкретном отсеке.
     * @param path путь к устройству, формат: NPart,NSection,NDevice, где префикс N означает, что используются индексы
     *              (например: 1,0,1).
     * @param rocket ссылка на ракету, в которой находится устройство.
     * @return Device device ссылка на найденное устройство.
     */
    public static Device chooseDevice(ArrayList<Integer> path, Rocket rocket) {
        if (path.get(0) < 3 && path.get(0) >= 0) {
            Part part = rocket.getPart(Part.NamePart.values()[path.get(0)]);
            if (path.get(1) < part.getContent().size() && path.get(1) >= 0) {
                Section section = part.getContent().elementAt(path.get(1));
                if (path.get(2) < section.getContent().size() && path.get(2) >= 0) {
                    Device device = section.getContent().elementAt(path.get(2));
                    System.out.printf("В %s части (%s, %s)\n", part.getName(), section.getName(), device.getName());
                    return device;
                }
            }
        }
        System.out.println("Device is incorrect. Use choose_device NPart,NSection,NDevice without spaces between indexes.");
        return null;
    }

    private static void setCountOfPacketsForDevice(Device device, Packet packet, boolean isInc) {
        int i = packet.getName().ordinal();
        device.setCountOfPackets(i, isInc);
    }


    /**
     * Удаление первого элемента из коллекции, которая хранится в устройстве.
     * @param device ссылка на устройство.
     */
    public static void removeFirst(Device device) {
        LinkedList<Packet> packets = device.getLab5LinkedList();
        Packet packet = packets.getFirst();
        System.out.printf("Удален пакет типа: %s, заполненный на %d ед.\n",
                packet.getName(), packet.getFullness());
        setCountOfPacketsForDevice(device, packet, false);
        packets.removeFirst();
    }

    /**
     * Удаление последнего элемента из коллекции, которая хранится в устройстве.
     * @param device ссылка на устройство.
     */
    public static void removeLast(Device device) {
        LinkedList<Packet> packets = device.getLab5LinkedList();
        Packet packet = packets.getLast();
        System.out.printf("Удален пакет типа: %s, заполненный на %d ед.\n",
                packet.getName(), packet.getFullness());
        setCountOfPacketsForDevice(device, packet, false);
        packets.removeLast();
    }

    /**
     * Удаление всех элементов из коллекции, чье значение наполненности (fullness) меньше чем у заданного.
     * @param device ссылка на устройство.
     * @param packetDescription заданный элемент в формате json. Общий вид: {name: CELLOPHANE_TUBULE | CHLOROVINYL_TUBULE | TUBE | POUCH | SACHET | def,fullness: 0...100}, например: {name:TUBE,fullness:42}.
     */
    public static void removeLower(Device device, String packetDescription) {
        Packet lowerPacket = fromJSONToPacket(packetDescription);
        LinkedList<Packet> packets = device.getLab5LinkedList();
        int rootFullness = lowerPacket.getFullness();

        packets.stream()
                .filter(packet -> packet.getFullness() < rootFullness)
                .forEach(packet -> setCountOfPacketsForDevice(device, packet, false));

        LinkedList<Packet> temp = packets.stream()
                .filter(packet -> packet.getFullness() >= rootFullness)
                .collect(Collectors.toCollection(LinkedList::new));

        int deleted = packets.size() - temp.size();
        System.out.printf("%d packets were deleted.\n", deleted);
        packets.clear();
        packets.addAll(temp);
    }

    private static Packet fromJSONToPacket(String jsonPacket) {
        // нет проверки на количество полей, их имена
        try {
            Gson gson = new Gson();
            Packet temp = gson.fromJson(jsonPacket, Packet.class);
            return new Packet(temp.getName(), temp.getFullness());
        } catch (NumberFormatException | JsonSyntaxException e) {
            System.out.println("Something wrong with \"json\"-object.");
            return new Packet(Packet.NamePacket.DEFAULT, 0);
        }
    }

    /**
     * Сортировка по возрастанию по параметру fullness
     * @param packets коллекция
     */
    public static void sort(LinkedList<Packet> packets) {
        packets.sort(new PacketComporator());
    }

    /**
     * Своя сортировка для коллекции.
     * @param packets коллекция.
     * @param start индекс первого элемента коллекции.
     * @param end индекс последнего элемента коллекции.
     */
    public static void sort(LinkedList<Packet> packets, int start, int end) {
        if (end - start <= 1) {
            if (packets.get(start).getFullness() > packets.get(end).getFullness()) {
                swap(packets, start, end);
            }
            return;
        }

        int mid = start + (end - start) / 2;
        sort(packets, start, mid);
        sort(packets, mid+1, end);
        merge(packets, start, end);
    }

    private static void swap(LinkedList<Packet> packets, int first, int second) {
        Packet old = packets.set(first, packets.get(second));
        packets.set(second, old);
    }

    private static void merge(LinkedList<Packet> packets, int start, int end) {
        int mid = start + (end - start) / 2;
        int leftStart = start;
        int rightStart = mid+1;

        LinkedList<Packet> temp = new LinkedList<>();

        while (rightStart <= end && leftStart <= mid) {
            if (packets.get(rightStart).getFullness() < packets.get(leftStart).getFullness()) {
                temp.add(packets.get(rightStart));
                rightStart++;
            } else {
                temp.add(packets.get(leftStart));
                leftStart++;
            }
        }

        // и вместо этого
        if (leftStart <= mid) {
            while (leftStart <= mid) {
                temp.add(packets.get(leftStart));
                leftStart++;
            }
        } else {
            while (rightStart <= end) {
                temp.add(packets.get(rightStart));
                rightStart++;
            }
        }

        // вместо этого надо что-то копирующее
        int i = 0;
        while (start <= end) {
            packets.set(start, temp.get(i));
            start++;
            i++;
        }
    }


    /**
     * Вывод информации о коллекции для конкретного устройства.
     * @param device ссылка на устройство.
     */
    public static void info(Device device) {
        LinkedList<Packet> packets = device.getLab5LinkedList();
        System.out.printf("Коллекция типа: %s\n", packets.getClass());
        System.out.printf("Количество элементов: %d.\n", packets.size());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.printf("Дата последнего обновления/создания: %s.\n", device.getDateOfCreation().format(formatter));

        Vector<Integer> countOfPackets = device.getCountOfPackets();
        System.out.println("Содержание коллекции:");
        for (int i = 0; i < 6; i++) {
            System.out.printf("Пакетов типа %s: %d.\n", Packet.NamePacket.values()[i], countOfPackets.elementAt(i));
        }
    }
}
