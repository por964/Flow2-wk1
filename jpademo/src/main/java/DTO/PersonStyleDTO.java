package DTO;

public class PersonStyleDTO {
    
    private String name;
    private int born;
    private String swimStyle;

    public PersonStyleDTO(String name, int born, String swimStyle) {
        this.name = name;
        this.born = born;
        this.swimStyle = swimStyle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBorn() {
        return born;
    }

    public void setBorn(int born) {
        this.born = born;
    }

    public String getSwimStyle() {
        return swimStyle;
    }

    public void setSwimStyle(String swimStyle) {
        this.swimStyle = swimStyle;
    }
    
    
    
}
