package bigbank.dragonsOfMugloar.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reputation {
    private int people;
    private int state;
    private int underworld;

    public Reputation() {
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUnderworld() {
        return underworld;
    }

    public void setUnderworld(int underworld) {
        this.underworld = underworld;
    }

    @Override
    public String toString() {
        return "Reputation{" +
                "people=" + people +
                ", state=" + state +
                ", underworld=" + underworld +
                '}';
    }
}
