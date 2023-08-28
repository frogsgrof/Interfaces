import java.util.ArrayList;

public class Collector {

    public static ArrayList<Object> collectAll(Filter filter, ArrayList<Object> objects) {
        ArrayList<Object> filtered = new ArrayList<>();
        for (Object object : objects)
            if (filter.accept(object))
                filtered.add(object);
        return filtered;
    }
}
