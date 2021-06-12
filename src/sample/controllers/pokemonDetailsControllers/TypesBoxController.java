package sample.controllers.pokemonDetailsControllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.model.datamodels.Result;
import sample.model.datamodels.Type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TypesBoxController {
    //private final List<String> allTypes = Arrays.asList("normal", "fighting", "flying", "poison", "ground", "rock", "bug", "ghost", "steel", "fire", "water", "grass", "electric", "psychic", "ice", "dragon", "dark", "fairy");

    @FXML
    Pane iconsPane;

    @FXML
    Pane mainContainer;

    public void setIcons(Type[] types) {
        HashSet<Type> activeTypes = new HashSet<>(Arrays.asList(types));

        for (int i = 0; i < Type.values().length; i++) {
            Type currentType = Type.values()[i];

            ImageView imageView = new ImageView();
            imageView.setImage(new Image("file:resources/icons/types/" + currentType.getName() + ".png"));

            imageView.setFitHeight(70);
            imageView.setFitWidth(70);

            int x = (i/6) * 85;
            int y = (i%6) * 112;
            imageView.relocate(y, x);

            if (!activeTypes.contains(currentType))
                imageView.setOpacity(0.3);
            iconsPane.getChildren().add(imageView);
        }
    }

    public void setBackgroundColor(Type type) {
        mainContainer.getStyleClass().add(type.getName());
    }
}
