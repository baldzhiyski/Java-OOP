package football.entities.field;

import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.player.Player;
import football.entities.supplement.Supplement;


import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseField implements Field{
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    public BaseField(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public void setName(String name) {
        if(name.isBlank()){
            throw new NullPointerException(ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int sumEnergy() {
        return supplements.stream().mapToInt(Supplement::getEnergy).sum();
    }

    @Override
    public void addPlayer(Player player) {
        if(players.size()==capacity){
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
        players.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void drag() {
        players.forEach(Player::stimulation);
    }

    @Override
    public String getInfo() {
        StringBuilder result = new StringBuilder();
        result.append(name).append(" ").append("(").append(getClass().getSimpleName()).append(")").append(":").append(System.lineSeparator());
        result.append("Player: ");
        String toAdd = players.isEmpty() ? "none":players.stream().map(Player::getName)
                .collect(Collectors.joining(" "));
        result.append(toAdd).append(System.lineSeparator());
        result.append("Supplement: ").append(supplements.size()).append(System.lineSeparator());
        result.append("Energy: ").append(sumEnergy());
        return result.toString();
    }

    @Override
    public Collection<Player> getPlayers() {
        return players;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public String getName() {
        return name;
    }
}
