public class Animal {
    String name;
    String color;
    String area;
    String size;

    public Animal(String iName, String iColor, String iArea, String iSize) {
        name = iName;
        color = iColor;
        area = iArea;
        size = iSize;
    }

    public static boolean equals(Animal animal1, Animal animal2){
        return animal1.color.equals(animal2.color) && animal1.area.equals(animal2.area) && animal1.size.equals(animal2.size);
    }
}
