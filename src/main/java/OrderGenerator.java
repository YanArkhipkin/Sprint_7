import java.util.List;

public class OrderGenerator {

    public static Order getDefaultOrder() {
        return new Order ("Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                "4",
                "+7 800 355 35 35",
                "5",
                "2020-06-06",
                "Saske, come back to Konoha",
                List.of("BLACK")
        );

    }

    public static Order getOrderColorWithoutColor(){
        Order order = OrderGenerator.getDefaultOrder();
        order.setColor(List.of());
        return order;
    }

    public static Order getOrderColorGrey(){
        Order order = OrderGenerator.getDefaultOrder();
        order.setColor(List.of("GREY"));
        return order;
    }

    public static Order getOrderColorBlackAndGrey(){
        Order order = OrderGenerator.getDefaultOrder();
        order.setColor(List.of("GREY", "BLACK"));
        return order;
    }

}