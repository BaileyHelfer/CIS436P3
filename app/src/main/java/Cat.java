public class Cat {
    String breed;
    String temperament;
    String imageURL;
    String origin;
    String lifeSpan;
    int rare;

    public Cat(String breed, String temperament, String imageURL, String origin, String lifeSpan, int rare)
{
    this.breed = breed;
    this.temperament = temperament;
    this.imageURL = imageURL;
    this.origin = origin;
    this.lifeSpan = lifeSpan;
    this.rare = rare;
}

public String getBreed(){
    return this.breed;
}

public void setBreed(String breed) {
    this.breed = breed;
}

public String getTemperament() {
    return temperament;
}

public void setTemperament(String temperament) {
    this.temperament = temperament;
}

public String getImageURL() {
    return imageURL;
}

public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
}

public String getOrigin() {
    return origin;
}

public void setOrigin(String origin) {
    this.origin = origin;
}

public int getRare() {
    return rare;
}

public void setRare(int rare) {
    this.rare = rare;
}

public String getLifeSpan() {
    return lifeSpan;
}

public void setLifeSpan(String lifeSpan) {
    this.lifeSpan = lifeSpan;
}


}
