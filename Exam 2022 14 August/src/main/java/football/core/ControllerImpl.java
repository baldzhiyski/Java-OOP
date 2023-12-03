package football.core;


import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private SupplementRepository supplement;
    private Map<String,Field> fields;

    public ControllerImpl() {
        this.supplement = new SupplementRepositoryImpl();
        this.fields= new LinkedHashMap<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field;
        switch (fieldType){
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                break;
            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                break;
            default:throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }
        fields.put(fieldName,field);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE,fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supplement1;
        switch (type){
            case "Liquid":
                supplement1 = new Liquid();
                break;
            case "Powdered":
                supplement1 = new Powdered();
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }
        supplement.add(supplement1);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE,type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Supplement supplement1;
        switch (supplementType){
            case "Liquid":
                supplement1 = new Liquid();
                break;
            case "Powdered":
                supplement1 = new Powdered();
                break;
            default:throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND,supplementType));
        }
        Field field = fields.get(fieldName);
        field.addSupplement(supplement1);
        supplement.remove(supplement1);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD,supplementType,fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player player;
        switch (playerType){
            case "Men":
                player = new Men(playerName,nationality,strength);
                break;
            case "Women":
                player = new Women(playerName,nationality,strength);
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }
        Field field = fields.get(fieldName);
        if((playerType.equals("Men") && field.getClass().getSimpleName().equals("NaturalGrass"))
        ||( playerType.equals("Women") && field.getClass().getSimpleName().equals("ArtificialTurf"))){
            field.addPlayer(player);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD,playerType,fieldName);
        }
        return ConstantMessages.FIELD_NOT_SUITABLE;
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = fields.get(fieldName);
        field.drag();
        return String.format(ConstantMessages.PLAYER_DRAG,field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = fields.get(fieldName);
        int sum = field.getPlayers().stream().mapToInt(Player::getStrength).sum();
        return String.format(ConstantMessages.STRENGTH_FIELD,fieldName,sum);
    }

    @Override
    public String getStatistics() {
        return fields.values()
                .stream().map(Field::getInfo)
                .collect(Collectors.joining(System.lineSeparator())).trim();
    }
}
