package dk.sdu.cbse.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);
        
        // Prints all Spring beans for debugging
        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        Game game = ctx.getBean(Game.class);
        game.start(window);
        game.render();
    }
}