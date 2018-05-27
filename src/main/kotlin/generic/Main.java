package generic;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // List<String> list = Lists.newArrayList();
    public static void main(String[] args) {
        List<String> list3 = createList();
        List<Integer> list4 = createList();
        list4.add(1);
        list3.add("1");
        System.out.println(list3);

    }

    public static <T> List<T> createList() {
        return new ArrayList<>();
    }
}
