public class Animal {
    public String name;
    public String color;
    public String area;
    public String size;

    public Animal(String iName, String iColor, String iArea, String iSize) {
        name = iName;
        color = iColor;
        area = iArea;
        size = iSize;
    }

    public boolean isSimilar(Animal animal2) {
        if (animal2 != null) {
            return this.color.equals(animal2.color) && this.area.equals(animal2.area) && this.size.equals(animal2.size);
        }
        return false;
    }
}
